package example.zj.com.animation.propertyanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.Keyframe;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import example.zj.com.animation.R;

/**
 * @Author: zhengjiong
 * Date: 2014-10-07
 * Time: 21:32
 */
public class CustomLayoutTransitionDemo extends Activity {
    private LayoutTransition mLayoutTransition;
    private LinearLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_transition_layout2);


        mLayoutTransition = new LayoutTransition();
        /**
         * 經過測試:只能在這裡設置動畫時間,下面設置的沒有用
         */
        mLayoutTransition.setDuration(1000);

        mContainer = (LinearLayout) findViewById(R.id.parent);
        mContainer.setLayoutTransition(mLayoutTransition);

        setLayoutTraitionAnimation();
    }

    private void setLayoutTraitionAnimation() {
        /*ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(null, "rotationX", 0.0f, 180.0f);
        rotateAnimator.setDuration(1000);

        mLayoutTransition.setStagger(LayoutTransition.CHANGE_DISAPPEARING, 1000);
        mLayoutTransition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, rotateAnimator);
        mLayoutTransition.setDuration(LayoutTransition.CHANGE_DISAPPEARING, 1000);*/

        /**
         * LayoutTransition.DISAPPEARING
         * 当删除一个Button时，设置该Button的动画效果
         * 如果要同時設置x和y就要使用PropertyValuesHolder
         * 設置設置的duration是沒有作用的可以不設置
         */
        ObjectAnimator objectAnimatorDisAppearing = ObjectAnimator.ofFloat(null, "scaleX", 1.0f, 0.0f)
                .setDuration(1000);
        mLayoutTransition.setAnimator(LayoutTransition.DISAPPEARING, objectAnimatorDisAppearing);
        //mLayoutTransition.setStagger(LayoutTransition.DISAPPEARING, 1000);//这个不懂是什么意思

        /*ObjectAnimator objectAnimatorChangeDisAppearing = ObjectAnimator.ofFloat(null, "rotation", 180f, 360f)
                .setDuration(1000);
        mLayoutTransition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, objectAnimatorChangeDisAppearing);*/


        /**
         * Delete Button
         * LayoutTransition.CHANGE_DISAPPEARING
         * 当删除一个Button时，设置其它Button的动画效果
         * Keyframe 对象中包含了一个时间/属性值的键值对，用于定义某个时刻的动画状态。
         *
         * zj:定義CHANGE_DISAPPEARING和Change_APPEARING動畫好像只能用這種方式,其他試過了不行,
         * pvhLeft這些不知道有什麼用
         */
        PropertyValuesHolder pvhLeft =
                PropertyValuesHolder.ofInt("left", 0, 0);
        PropertyValuesHolder pvhTop =
                PropertyValuesHolder.ofInt("top", 0, 0);
        PropertyValuesHolder pvhRight =
                PropertyValuesHolder.ofInt("right", 0, 0);
        PropertyValuesHolder pvhBottom =
                PropertyValuesHolder.ofInt("bottom", 0, 0);

        Keyframe mKeyframeStart = Keyframe.ofFloat(0.0f, 0.0f);
        Keyframe mKeyframeMiddle = Keyframe.ofFloat(0.5f, 180.0f);
        Keyframe mKeyframeEndBefore = Keyframe.ofFloat(0.999f, 360.0f);
        Keyframe mKeyframeEnd = Keyframe.ofFloat(1.0f, 0f);

        PropertyValuesHolder mPropertyValuesHolder = PropertyValuesHolder.ofKeyframe("rotation",
                mKeyframeStart, mKeyframeMiddle, mKeyframeEndBefore, mKeyframeEnd);

        ObjectAnimator mObjectAnimatorChangeDisAppearing = ObjectAnimator.ofPropertyValuesHolder(
                this, pvhLeft, pvhTop, pvhRight, pvhBottom, mPropertyValuesHolder)
                .setDuration(1000);//這裡設置的duration是沒有作用的,可以不用設置

        mLayoutTransition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, mObjectAnimatorChangeDisAppearing);


        /**
         * LayoutTransition.CHANGE_APPEARING
         * 当增加一个Button时，设置其他Button的动画效果
         * zj:定義CHANGE_DISAPPEARING和Change_APPEARING動畫好像只能用這種方式,其他試過了不行,
         */
        PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofFloat(
                "scaleX",
                1f, 0f, 1f);
        PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofFloat(
                "scaleY",
                1f, 0f, 1f);

        final ObjectAnimator changeIn = ObjectAnimator.ofPropertyValuesHolder(
                this, pvhLeft, pvhTop, pvhRight, pvhBottom, pvhScaleX, pvhScaleY)
                .setDuration(
                        mLayoutTransition.getDuration(LayoutTransition.CHANGE_APPEARING));//這裡設置的duration是沒有作用的,可以不用設置
        mLayoutTransition.setAnimator(LayoutTransition.CHANGE_APPEARING, changeIn);
        changeIn.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator anim) {
                View view = (View) ((ObjectAnimator) anim).getTarget();
                view.setScaleX(1f);
                view.setScaleY(1f);
            }
        });
    }

    public void addChildView(View view) {

        final Button button = new Button(this);
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());

        button.setLayoutParams(new LinearLayout.LayoutParams(width, width));
        button.setText("A");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContainer.removeView(button);
            }
        });

        mContainer.addView(button, 0);
    }
}
