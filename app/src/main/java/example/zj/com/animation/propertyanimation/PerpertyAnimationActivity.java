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
public class PerpertyAnimationActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_frame);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.place_holder, new MainFragment()).commit();
        }
    }

    class MainFragment extends ListFragment {
        String [] items = {"ValueAnimation", "ObjectAnimation", "CustomTypeEvalutor", "AnimatorSet"};

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
                    startActivity(new Intent(PerpertyAnimationActivity.this, ValueAnimationDemo.class));
                    break;
                case 1:
                    startActivity(new Intent(PerpertyAnimationActivity.this, ObjectAnimationDemo.class));
                    break;
                case 2:
                    startActivity(new Intent(PerpertyAnimationActivity.this, CustomTypeEvalutorDemo.class));
                    break;
                case 3:
                    startActivity(new Intent(PerpertyAnimationActivity.this, AnimatorSetDemo.class));
                    break;
            }

        }
    }
}