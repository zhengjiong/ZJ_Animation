package example.zj.com.animation.propertyanimation;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import example.zj.com.animation.R;

/**
 * Created by zhengjiong on 15/8/9.
 */
public class ValueAnimationDemo extends AppCompatActivity {

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
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 200f);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                String value =  animation.getAnimatedValue().toString();
                imageview.setTranslationX(Float.parseFloat(value));
            }
        });

        valueAnimator.setDuration(1000);
        valueAnimator.setRepeatMode(ObjectAnimator.REVERSE);
        valueAnimator.setRepeatCount(1);
        valueAnimator.start();
    }

    /**
     * 旋转动画
     */
    void rotateAnimation() {
        //rotation
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 315f);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                String value = animation.getAnimatedValue().toString();

                imageview.setRotation(Float.parseFloat(value));
            }
        });

        valueAnimator.setDuration(1500);
        valueAnimator.setRepeatCount(1);
        valueAnimator.setRepeatMode(ObjectAnimator.REVERSE);
        valueAnimator.start();
    }

    /**
     * 缩放动画(需要x和y轴同时缩放)
     * 方法1:使用AnimatorUpdateListener
     */
    void scaleAnimation1() {
        //setScaleX
        //属性名随便写一个,在onAnimationUpdate中去改变xy的缩放值
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1, 0.2f);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = Float.valueOf(animation.getAnimatedValue().toString());
                imageview.setScaleX(value);
                imageview.setScaleY(value);
            }
        });

        valueAnimator.setDuration(1500);
        valueAnimator.setRepeatMode(ObjectAnimator.REVERSE);
        valueAnimator.setRepeatCount(1);
        valueAnimator.start();
    }

    void alphaAnimation() {
        //setAlpha
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1, 0.2f);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = Float.valueOf(animation.getAnimatedValue().toString());
                imageview.setAlpha(value);
            }
        });

        valueAnimator.setDuration(1200);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setRepeatCount(1);
        valueAnimator.start();
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
                break;
            case R.id.btn_alpha_animation:
                alphaAnimation();
                break;
        }
    }
}