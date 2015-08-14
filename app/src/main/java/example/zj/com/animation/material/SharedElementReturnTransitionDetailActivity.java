package example.zj.com.animation.material;

import android.animation.Animator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import example.zj.com.animation.R;

/**
 * https://github.com/zhengjiong/Material-Animations
 *
 * Created by zhengjiong on 15/8/14.
 */
public class SharedElementReturnTransitionDetailActivity extends AppCompatActivity {

    @Bind(R.id.wrapper)
    RelativeLayout wrapper;

    @Bind(R.id.imageview)
    ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shared_element_return_transition_detail_layout);
        ButterKnife.bind(this);

        setEnterTransition();
        setExitTransition();
    }

    private void setExitTransition() {
        //设置SharedElementReturn动画延迟1秒执行
        getWindow().getSharedElementReturnTransition().setStartDelay(1000);

        //设置退出动画时间,并再动画开始时执行circularReveal动画
        Transition returnTransition = getWindow().getReturnTransition();
        returnTransition.setDuration(1000);
        returnTransition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                Animator hideAnimator = ViewAnimationUtils.createCircularReveal(
                        wrapper,
                        wrapper.getRight() / 2,
                        wrapper.getBottom() / 2,
                        wrapper.getHeight(),
                        0);

                hideAnimator.setDuration(1000);
                hideAnimator.start();
            }

            @Override
            public void onTransitionEnd(Transition transition) {
            }

            @Override
            public void onTransitionCancel(Transition transition) {
            }

            @Override
            public void onTransitionPause(Transition transition) {
            }

            @Override
            public void onTransitionResume(Transition transition) {
            }
        });

    }

    private void setEnterTransition() {
        Transition enterSharedElementTransition = getWindow().getSharedElementEnterTransition();
        enterSharedElementTransition.setStartDelay(1000);

        enterSharedElementTransition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                int finalRadius = Math.max(wrapper.getWidth(), wrapper.getHeight());

                /**
                 * @param view The View will be clipped to the animating circle.
                 * @param centerX The x coordinate of the center of the animating circle.
                 * @param centerY The y coordinate of the center of the animating circle.
                 * @param startRadius The starting radius of the animating circle.
                 * @param endRadius The ending radius of the animating circle.
                 */
                Animator animator = ViewAnimationUtils.createCircularReveal(
                        wrapper,
                        (wrapper.getLeft() + wrapper.getRight()) / 2,
                        (wrapper.getTop() + wrapper.getBottom()) / 2,
                        0,
                        finalRadius
                );

                wrapper.setVisibility(View.VISIBLE);

                animator.setDuration(1000);
                animator.start();
            }

            @Override
            public void onTransitionCancel(Transition transition) {
            }

            @Override
            public void onTransitionPause(Transition transition) {
            }

            @Override
            public void onTransitionResume(Transition transition) {
            }
        });
    }
}