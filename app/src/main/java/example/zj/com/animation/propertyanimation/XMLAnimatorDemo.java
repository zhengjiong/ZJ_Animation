package example.zj.com.animation.propertyanimation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import example.zj.com.animation.R;

/**
 * Created by zhengjiong on 15/8/10.
 */
public class XMLAnimatorDemo extends AppCompatActivity {

    @Bind(R.id.imageview)
    ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xml_animator_layout);
        ButterKnife.bind(this);
    }

    void scaleXAnimation() {
        /**
         * property animator必须要放入animator文件夹,而不是anim文件夹
         */
        Animator scaleAnimator = AnimatorInflater.loadAnimator(this, R.animator.scalex_property_anim);
        scaleAnimator.setTarget(imageview);
        scaleAnimator.start();
    }

    /**
     * 两个动画同时执行
     */
    void scaleAlphaAnimation() {
        Animator scaleAlphaAnimator = AnimatorInflater.loadAnimator(this, R.animator.scalex_alph_property_anim);
        scaleAlphaAnimator.setTarget(imageview);
        scaleAlphaAnimator.start();
    }

    @OnClick({R.id.btn1, R.id.btn2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                scaleXAnimation();
                break;
            case R.id.btn2:
                scaleAlphaAnimation();
                break;
        }
    }
}