package example.zj.com.animation.material;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.ChangeTransform;
import android.transition.Explode;
import android.transition.Slide;
import android.transition.Transition;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import example.zj.com.animation.R;

/**
 * Created by zhengjiong on 15/8/11.
 */
public class ActivityTransitionShareElementDemo extends Activity {

    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;

    @Bind(R.id.floating_circle_button)
    ImageView mAddButton;

    private MyRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //这里设置后就不需要在主题中设置
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setEnterTransition(new Explode().setDuration(1000));

        setContentView(R.layout.transition_share_element_layout);
        ButterKnife.bind(this);

        initRecyclerView();
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerview.setLayoutManager(linearLayoutManager);

        mAdapter = new MyRecyclerViewAdapter();
        recyclerview.setAdapter(mAdapter);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.img);
        }
    }

    class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(ActivityTransitionShareElementDemo.this).inflate(R.layout.recyclerview_item, viewGroup, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder myViewHolder, int i) {
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    /**
                     * 设置共享的元素(shared element)
                     */
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                            ActivityTransitionShareElementDemo.this,
                            Pair.create((View) myViewHolder.imageView, "pic"),
                            Pair.create((View) mAddButton, "floating_btn")
                    );

                    startActivity(
                            new Intent(ActivityTransitionShareElementDemo.this, ImageDetailActivity.class),
                            options.toBundle()
                    );
                }
            });
        }

        @Override
        public int getItemCount() {
            return 10;
        }
    }

}