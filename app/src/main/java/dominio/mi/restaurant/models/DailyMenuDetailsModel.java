package dominio.mi.restaurant.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Dumevi Cruces on 20/07/17.
 */

public class DailyMenuDetailsModel {
    @SerializedName("start_date")
    private String startDate;
    @SerializedName("end_date")
    private String endDate;
    private List<DishesModel> dishes;

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public List<DishesModel> getDishes() {
        return dishes;
    }
}
