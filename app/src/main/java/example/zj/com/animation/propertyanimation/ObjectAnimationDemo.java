package example.zj.com.animation.propertyanimation;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import example.zj.com.animation.R;

/**
 * Created by zhengjiong on 15/8/9.
 */
public class ObjectAnimationDemo extends AppCompatActivity {

    @Bind(R.id.imageview)
    ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.objectanimation_demo_layout);
        ButterKnife.bind(this);

    }

    /**
     * 平移动画
     */
    void translationAnimation() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageview, "translationX", 0, 200);
        objectAnimator.setDuration(1000);
        objectAnimator.setRepeatMode(ObjectAnimator.REVERSE);
        objectAnimator.setRepeatCount(1);
        objectAnimator.start();
    }

    /**
     * 旋转动画
     */
    void rotateAnimation() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageview, "rotation", 0, 315);
        objectAnimator.setDuration(1500);
        objectAnimator.setRepeatCount(1);
        objectAnimator.setRepeatMode(ObjectAnimator.REVERSE);
        objectAnimator.start();
    }

    /**
     * 缩放动画(需要x和y轴同时缩放)
     * 方法1:使用AnimatorUpdateListener
     */
    void scaleAnimation1() {
        //setScaleX
        //属性名随便写一个,在onAnimationUpdate中去改变xy的缩放值
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageview, "zj", 1, 0.2f);
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = Float.valueOf(animation.getAnimatedValue().toString());
                imageview.setScaleX(value);
                imageview.setScaleY(value);
            }
        });
        objectAnimator.setDuration(1500);
        objectAnimator.setRepeatMode(ObjectAnimator.REVERSE);
        objectAnimator.setRepeatCount(1);
        objectAnimator.start();
    }

    /**
     * 缩放动画(需要x和y轴同时缩放)
     * 方法2:使用PropertyValuesHolder
     */
    void scaleAnimation2() {
        PropertyValuesHolder propertyValuesHolderX = PropertyValuesHolder.ofFloat("scaleX", 1, 0.3f);
        PropertyValuesHolder propertyValuesHolderY = PropertyValuesHolder.ofFloat("scaleY", 1, 0.3f);

        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(imageview, propertyValuesHolderX, propertyValuesHolderY);
        objectAnimator.setDuration(1400);
        objectAnimator.setRepeatCount(1);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimator.start();
    }

    void alphaAnimation() {
        //setAlpha
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageview, "alpha", 1, 0.2f);
        objectAnimator.setDuration(1200);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimator.setRepeatCount(1);
        objectAnimator.start();
    }

    @OnClick({R.id.btn_margin_animation, R.id.btn_rotate_animation, R.id.btn_scale_animation1, R.id.btn_scale_animation2, R.id.btn_alpha_animation})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_margin_animation:
                translationAnimation();
                break;
            case R.id.btn_rotate_animation:
                rotateAnimation();
                break;
            case R.id.btn_scale_animation1:
                scaleAnimation1();
                break;
            case R.id.btn_scale_animation2:
                scaleAnimation2();
                break;
            case R.id.btn_alpha_animation:
               alphaAnimation();
                break;
        }
    }
}