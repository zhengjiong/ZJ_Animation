package example.zj.com.animation.material;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import example.zj.com.animation.R;

/**
 * Created by zhengjiong on 15/8/13.
 */
public class ActivityTransitionCompatDemo extends AppCompatActivity {


    @Bind(R.id.grid)
    GridView grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_compat_layout);
        ButterKnife.bind(this);

        grid.setAdapter(new GridAdapter());
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ActivityTransitionCompatDemo.this, CompatImageDetail.class);
                intent.putExtra("title", ((TextView) view.findViewById(R.id.textview_name)).getText());

                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        ActivityTransitionCompatDemo.this,
                        new android.support.v4.util.Pair<View, String>(view.findViewById(R.id.imageview_item), "header"),
                        new android.support.v4.util.Pair<View, String>(view.findViewById(R.id.textview_name), "title")
                );

                ActivityCompat.startActivity(ActivityTransitionCompatDemo.this, intent, optionsCompat.toBundle());
            }
        });
    }


    private class GridAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return Item.ITEMS.length;
        }

        @Override
        public Item getItem(int position) {
            return Item.ITEMS[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = getLayoutInflater().inflate(R.layout.grid_item_layout, viewGroup, false);
            }

            final Item item = getItem(position);

            ImageView image = (ImageView) view.findViewById(R.id.imageview_item);
            Picasso.with(image.getContext()).load("https://gju1.alicdn.com/bao/uploaded/i3/100000114274600328/TB24QOnepXXXXaRXpXXXXXXXXXX_!!0-0-juitemmedia.jpg_560x560Q90.jpg").into(image);

            TextView name = (TextView) view.findViewById(R.id.textview_name);
            name.setText(item.getName());

            return view;
        }
    }

    public static class Item {

        public static Item[] ITEMS = new Item[] {
                new Item("Flying in the Light"),
                new Item("Caterpillar"),
                new Item("Look Me in the Eye"),
                new Item("Flamingo"),
                new Item("Rainbow"),
                new Item("Over there"),
                new Item("Jelly Fish 2"),
                new Item("Lone Pine Sunset"),
                new Item("Flying in the Light"),
                new Item("Caterpillar"),
                new Item("Look Me in the Eye"),
                new Item("Flamingo"),
                new Item("Rainbow"),
                new Item("Over there"),
                new Item("Jelly Fish 2"),
                new Item("Lone Pine Sunset")
        };

        private final String mName;

        Item (String name) {
            mName = name;
        }

        public String getName() {
            return mName;
        }



    }
}