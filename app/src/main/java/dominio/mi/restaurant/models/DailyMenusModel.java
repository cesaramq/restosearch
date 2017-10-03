package dominio.mi.restaurant.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dumevi Cruces on 20/07/17.
 */

public class DailyMenusModel {
    @SerializedName("daily_menu")
    private DailyMenuDetailsModel dailyMenu;

    public DailyMenuDetailsModel getDailyMenu() {
        return dailyMenu;
    }
}
