package example.zj.com.animation.propertyanimation;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import example.zj.com.animation.R;

/**
 * 使用4.0之后View的animate方法来执行动画
 * Created by zhengjiong on 15/8/10.
 */
public class ViewAnimateDemo extends AppCompatActivity {

    @Bind(R.id.imageview)
    ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_animate_layout);
        ButterKnife.bind(this);

    }

    /**
     * 使用4.0之后,View的animate方法来执行动画
     */
    void executeAnimateMethod(){
        imageview.animate()
                .translationX(400)
                .scaleX(2f)
                .scaleY(2f)
                .setDuration(1000)
                .start();
    }

    /**
     * 两种方式实现的效果一样
     */
    void propertyValuesHolder() {
        PropertyValuesHolder scaleXPropertyValuesHolder = PropertyValuesHolder.ofFloat("scaleX", 1, 2f);
        PropertyValuesHolder scaleYPropertyValuesHolder = PropertyValuesHolder.ofFloat("scaleY", 1, 2f);
        PropertyValuesHolder translationXPropertyValuesHolder = PropertyValuesHolder.ofFloat("translationX", 0, 400);

        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(imageview, scaleXPropertyValuesHolder, scaleYPropertyValuesHolder, translationXPropertyValuesHolder);
        objectAnimator.setDuration(1000);
        objectAnimator.start();
    }

    @OnClick({R.id.btn1, R.id.btn2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                executeAnimateMethod();
                break;
            case R.id.btn2:
                propertyValuesHolder();
                break;
        }
    }
}