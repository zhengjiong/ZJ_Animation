package example.zj.com.animation.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import example.zj.com.animation.R;

/**
 * Fragment切换动画
 * Created by zhengjiong on 15/8/10.
 */
public class FragmentAnimActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_frame);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        /**
         * v4fragment 只能设置View Animation,
         *
         * 如果要用PropertyAnimation只能用4.0提供的Fragment, getFragmentManager.beginTransaction
         */
        fragmentTransaction.setCustomAnimations(R.anim.translate_in_from_right, R.anim.translate_out_to_left);

        fragmentTransaction
                .addToBackStack(null)
                .add(R.id.place_holder, new MyFragment())
                .commit();
    }

    public static class MyFragment extends Fragment{

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.my_fragment_layout, container, false);

            return view;
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            isDestroy = true;
        }
    }
    private static boolean isDestroy;
    @Override
    public void onBackPressed() {
        //if (getSupportFragmentManager().getFragments().size() == 0) {
        if(isDestroy){
            super.onBackPressed();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }
}