package example.zj.com.animation.material;

import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import example.zj.com.animation.R;

/**
 * Created by zhengjiong on 15/8/13.
 */
public class CompatImageDetail extends AppCompatActivity {

    @Bind(R.id.imageview_header)
    ImageView imageviewHeader;
    @Bind(R.id.textview_title)
    TextView textviewTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compat_image_detail);
        ButterKnife.bind(this);

        ViewCompat.setTransitionName(imageviewHeader, "header");
        ViewCompat.setTransitionName(textviewTitle, "title");

        String title = getIntent().getStringExtra("title");

        Picasso.with(imageviewHeader.getContext())
                .load("https://gju1.alicdn.com/bao/uploaded/i3/100000114274600328/TB24QOnepXXXXaRXpXXXXXXXXXX_!!0-0-juitemmedia.jpg_560x560Q90.jpg")
                .noFade()
                .into(imageviewHeader);

        textviewTitle.setText(title);
    }
}