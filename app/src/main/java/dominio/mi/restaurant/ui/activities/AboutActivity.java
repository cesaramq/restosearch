package dominio.mi.restaurant.ui.activities;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import dominio.mi.restaurant.R;
import dominio.mi.restaurant.Utils;
import dominio.mi.restaurant.ui.MyActivity;

public class AboutActivity extends MyActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        getToolbar();
        ActionBar action = getSupportActionBar();
        action.setDisplayShowTitleEnabled(false);
        action.setDisplayHomeAsUpEnabled(true);

        getTitleApp();

        TextView textResto = (TextView) findViewById(R.id.logo_text_resto);
        textResto.setTypeface(Utils.getFontsAcme(getAssets()));

        TextView textSearch = (TextView) findViewById(R.id.logo_text_search);
        textSearch.setTypeface(Utils.getFontShadow(getAssets()));

        TextView zomato = (TextView) findViewById(R.id.zomato);
        zomato.setPaintFlags(zomato.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        zomato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String appPackageName = "com.application.zomato";
                Intent intent = getPackageManager().getLaunchIntentForPackage(appPackageName);
                if (intent != null) {
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setData(Uri.parse("market://details?id=" + appPackageName));
                    startActivity(intent);
                }
            }
        });
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
