package example.zj.com.animation.material;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeImageTransform;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionSet;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import example.zj.com.animation.R;

/**
 * Created by zhengjiong on 15/8/10.
 */
public class ActivityTransitionDemo extends AppCompatActivity {

    @Bind(R.id.btn_explode)
    Button btnExplode;
    @Bind(R.id.btn_slide)
    Button btnSlide;
    @Bind(R.id.btn_fade)
    Button btnFade;
    @Bind(R.id.btn_share)
    Button btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_transition_layout);
        ButterKnife.bind(this);


        //getWindow().setEnterTransition(new Explode().setDuration(2000));

        //getWindow().setExitTransition(new Explode().setDuration(2000));
    }

    @OnClick({R.id.btn_explode, R.id.btn_slide, R.id.btn_fade, R.id.btn_share})
    public void onClick(View view) {
        Intent intent = new Intent(ActivityTransitionDemo.this, SecondActivity.class);

        switch (view.getId()) {
            case R.id.btn_explode:
                intent.putExtra("type", 0);

                //getWindow().setExitTransition(new Explode().setDuration(500));
                /**
                 * 设置启动SecondActivity时, 当前Activity退出的效果
                 */
                Transition explode = TransitionInflater.from(ActivityTransitionDemo.this).inflateTransition(R.transition.activity_explode);
                getWindow().setExitTransition(explode);
                /**
                 * 这里使用setEnterTransition设置重新进入效果是没有任何效果的,
                 * 如要设置SecondActivity返回时, 此Activity的重新进入效果,需要设置setReenterTranition
                 */
                //getWindow().setEnterTransition(new Explode().setDuration(2000));
                //重新进入的动画
                getWindow().setReenterTransition(new Explode().setDuration(2000));

                startActivity(
                        intent,
                        ActivityOptions.makeSceneTransitionAnimation(this).toBundle());

                break;
            case R.id.btn_slide:
                intent.putExtra("type", 1);

                getWindow().setExitTransition(new Slide().setDuration(500));

                startActivity(
                        intent,
                        ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
            case R.id.btn_fade:
                intent.putExtra("type", 2);

                getWindow().setExitTransition(new Fade().setDuration(500));

                startActivity(
                        intent,
                        ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
            case R.id.btn_share:
                intent.putExtra("type", 3);
                //getWindow().setSharedElementExitTransition(new ChangeImageTransform());
                //getWindow().setSharedElementEnterTransition(new ChangeImageTransform());

                //Transition transition = new ChangeImageTransform();
                //transition.setDuration(2000);
                //getWindow().setExitTransition(transition);

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                        this,
                        Pair.create((View) btnShare, "share")
                );

                startActivity(intent, options.toBundle());

                break;
        }


    }
}