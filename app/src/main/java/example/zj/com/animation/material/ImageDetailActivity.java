package example.zj.com.animation.material;

import android.animation.Animator;
import android.app.Activity;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import example.zj.com.animation.R;

/**
 * Created by zhengjiong on 15/8/11.
 */
public class ImageDetailActivity extends Activity {

    @Bind(R.id.imageview)
    ImageView imageview;

    @Bind(R.id.circle_button)
    ImageView floatingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setEnterTransition(new Explode().setDuration(500));
        //getWindow().setExitTransition(null);

        setContentView(R.layout.image_detail_layout);
        ButterKnife.bind(this);

        //getWindow().setEnterTransition(new Explode().setDuration(1000));


        imageview.setTransitionName("pic");
        floatingBtn.setTransitionName("floating_btn");
    }

    @OnClick(R.id.circle_button)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.circle_button:
                Animator animator = ViewAnimationUtils.createCircularReveal(
                        imageview,
                        imageview.getWidth() / 2,
                        imageview.getHeight() / 2,
                        imageview.getWidth(),
                        0
                );
                animator.setInterpolator(new DecelerateInterpolator());
                animator.setDuration(500);
                animator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Animator animator = ViewAnimationUtils.createCircularReveal(
                                imageview,
                                imageview.getWidth() / 2,
                                imageview.getHeight() / 2,
                                0,
                                imageview.getWidth()
                        );
                        animator.setInterpolator(new DecelerateInterpolator());
                        animator.setDuration(500);
                        animator.start();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                animator.start();
                break;
        }
    }
}