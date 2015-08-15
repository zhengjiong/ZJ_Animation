package example.zj.com.animation.material;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import example.zj.com.animation.R;

/**
 * Created by zhengjiong on 15/8/15.
 */
public class ActivityOptionsCompatDetail extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_compat_detail);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}