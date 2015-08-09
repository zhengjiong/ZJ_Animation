package example.zj.com.animation.propertyanimation;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import example.zj.com.animation.R;

/**
 * 实现抛物线的效果
 *
 * Created by zhengjiong on 15/8/9.
 */
public class CustomTypeEvalutorDemo extends AppCompatActivity {

    @Bind(R.id.imageview)
    ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_type_evalutor_layout);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.start)
    public void onClick(View view) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(3000);
        valueAnimator.setObjectValues(new PointF(0, 0));
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setEvaluator(new TypeEvaluator<PointF>() {

            /**
             * 只和时间有关系，但是根据时间的变化，横向和纵向的移动速率是不同的，我们该咋实现呢？此时就要重写TypeEvaluator,
             * 因为我们在时间变化的同时，需要返回给对象两个值，x当前位置，y当前位置
             *
             * 水平方向200px/s，垂直方向加速度200px/s*s
             * fraction = t / duration;  //当前时间已经执行了多少(比列,0-1)
             */
            @Override
            public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
                PointF point = new PointF();
                //x轴位移速度为200px,3秒后位移距离为600px
                point.x = 200 * fraction * 3;

                //y轴位移距离为0-900px,这里有点问题,y轴位移应该是第一秒200,第二秒400,第三秒600,一共应该是1200px
                point.y = 0.5f * 200 * (fraction * 3) * (fraction * 3);

                Log.i("zj", "fraction = " + fraction + " ,point.x = " + point.x + " ,point.y = " + point.y);

                return point;
            }

        });

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                /**
                 * 自定义了一个TypeEvaluator，每次根据当前时间返回一个PointF对象，（
                 * PointF和Point的区别就是x,y的单位一个是float,一个是int;
                 * RectF,Rect也是）PointF中包含了x,y的当前位置～然后我们在监听器中获取，动态设置属性
                 */
                PointF point = (PointF) animation.getAnimatedValue();
                imageview.setX(point.x);
                imageview.setY(point.y);

            }
        });
        valueAnimator.start();
    }
}