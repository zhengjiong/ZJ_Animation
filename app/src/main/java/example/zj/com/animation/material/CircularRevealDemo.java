package example.zj.com.animation.material;

import android.animation.Animator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import example.zj.com.animation.R;

/**
 * Created by zhengjiong on 15/8/10.
 */
public class CircularRevealDemo extends AppCompatActivity {

    @Bind(R.id.circle)
    ImageView circle;
    @Bind(R.id.rectangle)
    ImageView rectangle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circular_reveal_layout);
        ButterKnife.bind(this);
    }

    /**
     * view 操作的视图
     *
     * centerX 动画开始的中心点X
     * centerY 动画开始的中心点Y
     *
     * startRadius 动画开始半径
     * startRadius 动画结束半径
     *
     * @param centerX The x coordinate of the center of the animating circle.
     * @param centerY The y coordinate of the center of the animating circle.
     * @param startRadius The starting radius of the animating circle.
     * @param endRadius The ending radius of the animating circle.
     */
    @OnClick({R.id.circle, R.id.rectangle})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.circle:

                //需要api21
                Animator animator1 = ViewAnimationUtils.createCircularReveal(view, view.getWidth() / 2, view.getHeight() / 2, view.getWidth(), 0);
                animator1.setDuration(1000);
                animator1.start();
                break;
            case R.id.rectangle:
                //需要api21
                Animator animator2 = ViewAnimationUtils.createCircularReveal(
                        view, 0, 0, 0,
                        (float) Math.hypot(view.getWidth(), view.getHeight()));
                animator2.setDuration(1000);
                animator2.setInterpolator(new AccelerateInterpolator());
                animator2.start();
                break;
        }

    }
}