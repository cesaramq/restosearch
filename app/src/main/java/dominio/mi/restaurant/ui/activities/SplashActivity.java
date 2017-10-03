package dominio.mi.restaurant.ui.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import dominio.mi.restaurant.R;
import dominio.mi.restaurant.SharedPreferencesUtil;
import dominio.mi.restaurant.Utils;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TextView textLogo = (TextView) findViewById(R.id.logo_text_resto);
        textLogo.setTypeface(Utils.getFontsAcme(getAssets()));

        TextView textLogoSearch = (TextView) findViewById(R.id.logo_text_search);
        textLogoSearch.setTypeface(Utils.getFontShadow(getAssets()));

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil(SplashActivity.this);
                boolean userLogged = sharedPreferencesUtil.getUserRegistered();

                if (userLogged) {
                    Intent intent = new Intent(SplashActivity.this, CategoriesActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                }

            }
        }, 1500);
    }
}
