package example.zj.com.animation.propertyanimation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import example.zj.com.animation.R;

/**
 * 属性动画
 * Created by zhengjiong on 15/8/8.
 */
public class PropertyAnimationActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_frame);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.place_holder, new MainFragment()).commit();
        }
    }

    public static class MainFragment extends ListFragment {
        String[] items = {"ValueAnimation", "ObjectAnimation", "CustomTypeEvalutor", "AnimatorSet", "XMLAnimatorDemo", "LayoutTransition", "CustomLayoutTransition", "ViewAnimate Method"};

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    getActivity(),android.R.layout.simple_list_item_1,
                    items
            );
            setListAdapter(arrayAdapter);
        }

        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
            switch (position) {
                case 0:
                    startActivity(new Intent(getContext(), ValueAnimationDemo.class));
                    break;
                case 1:
                    startActivity(new Intent(getContext(), ObjectAnimationDemo.class));
                    break;
                case 2:
                    startActivity(new Intent(getContext(), CustomTypeEvalutorDemo.class));
                    break;
                case 3:
                    startActivity(new Intent(getContext(), AnimatorSetDemo.class));
                    break;
                case 4:
                    startActivity(new Intent(getContext(), XMLAnimatorDemo.class));
                    break;
                case 5:
                    startActivity(new Intent(getContext(), LayoutTransitionDemo.class));
                    break;
                case 6:
                    startActivity(new Intent(getContext(), CustomLayoutTransitionDemo.class));
                    break;
                case 7:
                    startActivity(new Intent(getContext(), ViewAnimateDemo.class));
                    break;
            }

        }
    }
}