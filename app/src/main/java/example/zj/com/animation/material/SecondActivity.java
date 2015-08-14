package example.zj.com.animation.material;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import example.zj.com.animation.R;

/**
 * Created by zhengjiong on 15/8/10.
 */
public class SecondActivity extends AppCompatActivity {

    @Bind(R.id.btn_share)
    Button btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        setContentView(R.layout.second_activity_layout);
        ButterKnife.bind(this);

        //getWindow().setExitTransition(new Explode().setDuration(1000));
        //getWindow().getEnterTransition().setDuration(1500);

        int type = getIntent().getIntExtra("type", 0);
        switch (type) {
            case 0://explode
                //设置启动动画
                getWindow().setEnterTransition(new Explode().setDuration(500));

                /**
                 * activity退出时的动画,是设置ReturnTransition,而不是ExitTransition
                 *
                 * SecondActivity On Back Pressed Transition Framework executes Enter and Exit reverse animations
                 * respectively (because it cannot find returnTransition and reenterTransition)
                 *
                 * Activity 退出时的动画, 如果不设置将会反转进入动画(reverse exit transition)
                 */
                getWindow().setReturnTransition(new Fade().setDuration(500));
                break;
            case 1://slide
                getWindow().setEnterTransition(new Slide().setDuration(500));

                //activity退出时的动画
                getWindow().setReturnTransition(new Fade().setDuration(500));
                break;
            case 2://fade
                getWindow().setEnterTransition(new Fade().setDuration(500));

                //activity退出时的动画
                getWindow().setReturnTransition(new Fade().setDuration(500));
                break;
            case 3://shareElment
                btnShare.setTransitionName("share");
                break;
        }


        /**
         * 这里设置退出动画是没有作用的,退出动画会和启动动画完全相反来执行
         * 如果要设置退出动画需要设置setReturnTransition
         * getWindow().setExitTransition(new Slide().setDuration(900));
        **/


        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAfterTransition();
            }
        });
    }
}