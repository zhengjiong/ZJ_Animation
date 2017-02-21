package example.zj.com.animation;

import android.content.Intent;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import example.zj.com.animation.fragment.FragmentAnimActivity;
import example.zj.com.animation.material.MaterialAnimationActivity;
import example.zj.com.animation.propertyanimation.PropertyAnimationActivity;


/**
 * Created by zhengjiong on 15/8/8.
 */
public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_frame);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.place_holder, new MainFragment()).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class MainFragment extends ListFragment{
        String [] items = {"Propery Animation", "Fragment Switch Animation", "Material Animation"};

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    getActivity(),android.R.layout.simple_list_item_1,
                    items
            );
            setListAdapter(arrayAdapter);
        }

        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
            switch (position) {
                case 0:
                    startActivity(new Intent(getActivity(), PropertyAnimationActivity.class));
                    break;
                case 1:
                    startActivity(new Intent(getActivity(), FragmentAnimActivity.class));
                    break;
                case 2:
                    startActivity(new Intent(getActivity(), MaterialAnimationActivity.class));
                    break;
            }
        }
    }
}
