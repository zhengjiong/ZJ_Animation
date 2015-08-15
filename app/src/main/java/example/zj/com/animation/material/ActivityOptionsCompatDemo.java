package example.zj.com.animation.material;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import example.zj.com.animation.R;

/**
 * Created by zhengjiong on 15/8/15.
 */
public class ActivityOptionsCompatDemo extends AppCompatActivity {


    @Bind(R.id.imageview)
    ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_compat_layout);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btn_make_custom_animation, R.id.btn_make_scale_up_animation})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_make_custom_animation:
                makeCustomAnimation();
                break;
            case R.id.btn_make_scale_up_animation:
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeScaleUpAnimation(
                        imageview,
                        (int) imageview.getX(),//拉伸开始的坐标
                        (int) imageview.getY(),

                        0,//拉伸开始的区域大小，这里用（0，0）表示从无到全屏
                        0
                );

                ActivityCompat.startActivity(
                        this,
                        new Intent(this, ActivityOptionsCompatDetail.class),
                        activityOptionsCompat.toBundle());

                break;
        }
    }

    /**
     * 普通动画,效果相当于overridePendingTransition
     */
    private void makeCustomAnimation() {
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeCustomAnimation(
                this,
                R.anim.translate_in_from_right,
                R.anim.translate_out_to_left
        );

        ActivityCompat.startActivity(
                this,
                new Intent(this, ActivityOptionsCompatDetail.class),
                optionsCompat.toBundle()
        );
    }
}