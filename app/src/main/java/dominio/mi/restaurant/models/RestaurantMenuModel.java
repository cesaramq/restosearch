package dominio.mi.restaurant.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Dumevi Cruces on 20/07/17.
 */

public class RestaurantMenuModel {
    @SerializedName("daily_menus")
    private List<DailyMenusModel> dailyMenus;
    private String status;

    public List<DailyMenusModel> getDailyMenus() {
        return dailyMenus;
    }

    public String getStatus() {
        return status;
    }
}
