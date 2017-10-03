package dominio.mi.restaurant;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Menu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import dominio.mi.restaurant.models.RestaurantListModel;
import dominio.mi.restaurant.network.deserializers.JsonObjectToArray;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dumevi Cruces on 09/07/17.
 */

public class Utils {
    public static final String URL = "https://developers.zomato.com/api/v2.1/";
    public static final String USER_KEY = "09a5ebbc6006fce8ebcf4fd3651c431c";

    public static void log(String str) {
        Log.d("cesar", str);
    }

    public static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Retrofit getRetrofitDeserializer() {
        Type myOtherClassListType = new TypeToken<List<RestaurantListModel>>() {
        }.getType();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(myOtherClassListType, new JsonObjectToArray())
                .create();
        return new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static Typeface getFontsAcme(AssetManager assetManager) {
        return Typeface.createFromAsset(assetManager, "fonts/Acme-Regular.ttf");
    }

    public static Typeface getFontShadow(AssetManager assetManager) {
        return Typeface.createFromAsset(assetManager, "fonts/ShadowsIntoLight.ttf");
    }

    public static Intent intentUserSharedPreferences(Context context, Class classes, Boolean status) {
        SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil(context);
        sharedPreferencesUtil.setUserRegistered(status);
        return new Intent(context, classes);
    }

    public static boolean menu(Activity activity, Menu menu) {
        activity.getMenuInflater().inflate(R.menu.main, menu);

        for (int i = 0; i < menu.size(); i++) {
            Drawable drawable = menu.getItem(i).getIcon();
            if (drawable != null) {
                drawable.mutate();
                drawable.setColorFilter(ContextCompat.getColor(activity, R.color.colorPrimaryText),
                        PorterDuff.Mode.SRC_ATOP);
            }
        }

        return true;
    }
}
