package example.zj.com.animation.propertyanimation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import example.zj.com.animation.R;

/**
 * Created by zhengjiong on 15/8/9.
 */
public class AnimatorSetDemo extends AppCompatActivity {

    @Bind(R.id.imageview)
    ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animatorset_layout);
        ButterKnife.bind(this);
    }

    /**
     * 两个动画同时执行
     */
    void togetherAnimation() {
        ObjectAnimator scaleAnimatorX = ObjectAnimator.ofFloat(imageview, "scaleX", 1f, 2f);
        ObjectAnimator scaleAnimatorY = ObjectAnimator.ofFloat(imageview, "scaleY", 1f, 2f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(2000);

        //同时执行两个ObjectAnimator
        animatorSet.playTogether(scaleAnimatorX, scaleAnimatorY);

        animatorSet.start();
    }

    /**
     * 动画按一定的顺序执行
     */
    void playWidthAfterAnimator(){
        ObjectAnimator scaleAnimatorX = ObjectAnimator.ofFloat(imageview, "scaleX", 1f, 0.5f);
        ObjectAnimator scaleAnimatorY = ObjectAnimator.ofFloat(imageview, "scaleY", 1f, 0.5f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(2000);

        /**
         * 先执行X缩放,完了之后再执行Y轴缩放
         */
        animatorSet.play(scaleAnimatorX);
        animatorSet.play(scaleAnimatorY).after(scaleAnimatorX);
        animatorSet.start();
    }

    @OnClick({R.id.btn1, R.id.btn2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                togetherAnimation();
                break;
            case R.id.btn2:
                playWidthAfterAnimator();
                break;
        }
    }
}