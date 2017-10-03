package dominio.mi.restaurant.models;

/**
 * Created by Dumevi Cruces on 30/08/17.
 */

public class RestaurantDetailsModel {
    public final static int TYPE_LOCATION = 1;
    public final static int TYPE_PHONE = 2;
    public final static int TYPE_CUISINE = 3;

    private String restaurantTitle;
    private String getRestaurantDescription;
    private int type;
    private int id;

    public RestaurantDetailsModel(String restaurantTitle, String getRestaurantDescription, int type, int id) {
        this.restaurantTitle = restaurantTitle;
        this.getRestaurantDescription = getRestaurantDescription;
        this.type = type;
        this.id = id;
    }

    public String getRestaurantTitle() {
        return restaurantTitle;
    }

    public String getGetRestaurantDescription() {
        return getRestaurantDescription;
    }

    public int getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public void setType(int type) {
        this.type = type;
    }
}
