package dominio.mi.restaurant.ui.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import dominio.mi.restaurant.R;
import dominio.mi.restaurant.Utils;
import dominio.mi.restaurant.ui.MyActivity;

public class UserActivity extends MyActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        getToolbar();

        ActionBar action = getSupportActionBar();
        action.setDisplayShowTitleEnabled(false);
        action.setDisplayHomeAsUpEnabled(true);
        
        getTitleApp();

        ImageView backgroundProfile = (ImageView) findViewById(R.id.background_profile);
        CircleImageView imageProfile = (CircleImageView) findViewById(R.id.image_profile);

        String url = "http://vida20.com/wp-content/uploads/2014/05/portadas-nuevo-twitter-1500x500-4.jpg";
        String urlProfile = "http://img.huffingtonpost.com/asset/,scalefit_950_800_noupscale/57c4a6ce180000dd10bcde68.jpeg";

        Glide.with(this).load(url).into(backgroundProfile);
        Glide.with(this).load(urlProfile).into(imageProfile);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return Utils.menu(this, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return menuCaseLogin(item);
    }
}
