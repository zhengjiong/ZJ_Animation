package example.zj.com.animation.material;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Transition;
import android.view.View;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import example.zj.com.animation.R;

/**
 * https://github.com/zhengjiong/Material-Animations
 *
 * Created by zhengjiong on 15/8/14.
 */
public class SharedElementReturnTransitionDemo extends AppCompatActivity {

    @Bind(R.id.imageview)
    ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shared_element_return_transition_layout);
        ButterKnife.bind(this);

        /**
         * Activity启动下一个Activity时,退出的动画
         */
        getWindow().setExitTransition(new Explode().setDuration(1000));
    }

    @OnClick(R.id.imageview)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageview:
                Intent intent = new Intent(this, SharedElementReturnTransitionDetailActivity.class);

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, imageview, "img_center");

                startActivity(intent, options.toBundle());
                break;
        }
    }
}