package example.zj.com.animation.material;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import example.zj.com.animation.R;
import example.zj.com.animation.propertyanimation.AnimatorSetDemo;
import example.zj.com.animation.propertyanimation.CustomLayoutTransitionDemo;
import example.zj.com.animation.propertyanimation.CustomTypeEvalutorDemo;
import example.zj.com.animation.propertyanimation.LayoutTransitionDemo;
import example.zj.com.animation.propertyanimation.ObjectAnimationDemo;
import example.zj.com.animation.propertyanimation.ValueAnimationDemo;
import example.zj.com.animation.propertyanimation.ViewAnimateDemo;
import example.zj.com.animation.propertyanimation.XMLAnimatorDemo;

/**
 * Created by zhengjiong on 15/8/10.
 */
public class MaterialAnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_frame);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.place_holder, new MyListFragment()).commit();
        }
    }

    class MyListFragment extends ListFragment {
        String[] items = {
                "CircularReveal Animiation",
                "Activity Transition",
                "Activity Transition ShareElement Demo",
                "Activity Transition Compat (使用ActivityOptionsCompat兼容老板版本,不过老板版本是没有动画的,只有5.0以上才有)"
        };

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    getActivity(), android.R.layout.simple_list_item_1,
                    items
            );
            setListAdapter(arrayAdapter);
        }

        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
            switch (position) {
                case 0:
                    startActivity(new Intent(MaterialAnimationActivity.this, CircularRevealDemo.class));
                    break;
                case 1:
                    startActivity(new Intent(MaterialAnimationActivity.this, ActivityTransitionDemo.class));
                    break;
                case 2:
                    startActivity(new Intent(MaterialAnimationActivity.this, ActivityTransitionShareElementDemo.class));
                    break;
                case 3:
                    startActivity(new Intent(MaterialAnimationActivity.this, ActivityTransitionCompatDemo.class));
                    break;
            }

        }

    }
}