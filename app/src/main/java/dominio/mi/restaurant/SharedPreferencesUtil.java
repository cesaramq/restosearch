package dominio.mi.restaurant;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Dumevi Cruces on 17/09/17.
 */

public class SharedPreferencesUtil {
    private SharedPreferences sharedPreferences;

    public SharedPreferencesUtil(Context context) {
        sharedPreferences = context.getSharedPreferences("restaurant", Context.MODE_PRIVATE);
    }

    public void setUserRegistered(boolean userRegistered) {
        sharedPreferences.edit().putBoolean("userRegistered", userRegistered).apply();
    }

    public boolean getUserRegistered() {
        return sharedPreferences.getBoolean("userRegistered", false);
    }
}
