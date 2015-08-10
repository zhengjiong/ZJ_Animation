package example.zj.com.animation.propertyanimation;

import android.animation.Animator;
import android.animation.LayoutTransition;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import example.zj.com.animation.R;

/**
 * LayoutTransition.APPEARING 当一个View在ViewGroup中出现时，对此View设置的动画
 *
 * LayoutTransition.CHANGE_APPEARING 当一个View在ViewGroup中出现时，对此View对其他View位置造成影响，对其他View设置的动画
 *
 * LayoutTransition.DISAPPEARING  当一个View在ViewGroup中消失时，对此View设置的动画
 *
 * LayoutTransition.CHANGE_DISAPPEARING 当一个View在ViewGroup中消失时，对此View对其他View位置造成影响，对其他View设置的动画
 *
 * LayoutTransition.CHANGE 不是由于View出现或消失造成对其他View位置造成影响，然后对其他View设置的动画。
 *
 *
 * Created by zhengjiong on 15/8/10.
 */
public class LayoutTransitionDemo extends AppCompatActivity {

    @Bind(R.id.gridlayout)
    GridLayout gridlayout;

    int mIndex;
    @Bind(R.id.chk1)
    CheckBox chk1;
    @Bind(R.id.chk2)
    CheckBox chk2;
    @Bind(R.id.chk3)
    CheckBox chk3;
    @Bind(R.id.chk4)
    CheckBox chk4;

    private LayoutTransition mLayoutTransition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_transition_layout);
        ButterKnife.bind(this);

        //设置一列显示6个按钮
        gridlayout.setColumnCount(6);

        //添加默认的动画,默认动画会开启所有效果
        mLayoutTransition = new LayoutTransition();
        gridlayout.setLayoutTransition(mLayoutTransition);
    }


    @OnClick(R.id.btn_add)
    public void addItem() {

        final Button button = new Button(this);
        button.setText(String.valueOf(mIndex++));
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());
        gridlayout.addView(button, Math.min(1, gridlayout.getChildCount()), new ViewGroup.LayoutParams(width, width));

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                gridlayout.removeView(button);
            }
        });
    }

    @OnCheckedChanged({R.id.chk1, R.id.chk2, R.id.chk3, R.id.chk4})
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        /**
         * LayoutTransition.APPEARING 当一个View在ViewGroup中出现时，对此View设置的动画
         */
        Log.i("zj", "isChecked="+ isChecked);
        if (chk1.isChecked()) {
            mLayoutTransition.setAnimator(LayoutTransition.APPEARING, mLayoutTransition.getAnimator(LayoutTransition.APPEARING));
        } else {
            mLayoutTransition.setAnimator(LayoutTransition.APPEARING, null);
        }

        /**
         * LayoutTransition.CHANGE_APPEARING 当一个View在ViewGroup中出现时，对此View对其他View位置造成影响，对其他View设置的动画
         */
        if (chk2.isChecked()) {
            mLayoutTransition.setAnimator(LayoutTransition.CHANGE_APPEARING, mLayoutTransition.getAnimator(LayoutTransition.CHANGE_APPEARING));
        } else {
            mLayoutTransition.setAnimator(LayoutTransition.CHANGE_APPEARING, null);
        }

        /**
         * LayoutTransition.DISAPPEARING  当一个View在ViewGroup中消失时，对此View设置的动画
         */
        if (chk3.isChecked()) {
            mLayoutTransition.setAnimator(LayoutTransition.DISAPPEARING, mLayoutTransition.getAnimator(LayoutTransition.DISAPPEARING));
        } else {
            mLayoutTransition.setAnimator(LayoutTransition.DISAPPEARING, null);
        }

        /**
         * LayoutTransition.CHANGE_DISAPPEARING 当一个View在ViewGroup中消失时，对此View对其他View位置造成影响，对其他View设置的动画
         */
        if (chk4.isChecked()) {
            mLayoutTransition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, mLayoutTransition.getAnimator(LayoutTransition.CHANGE_DISAPPEARING));
        } else {
            mLayoutTransition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, null);
        }

        gridlayout.setLayoutTransition(mLayoutTransition);
    }
}