package dominio.mi.restaurant.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Dumevi Cruces on 28/06/17.
 */

public class RestaurantModel {

    private int id;
    private String name;

    public List<RestaurantDetailsModel> getRestaurantDetailsList() {
        return restaurantDetailsList;
    }

    private List<RestaurantDetailsModel> restaurantDetailsList;

    @SerializedName("featured_image")
    private String featuredImage;

    @SerializedName("user_rating")
    private UserRatingModel userRating;

    @SerializedName("phone_numbers")
    private String phoneNumber;

    private RestaurantLocationModel location;
    private String cuisines;

    @SerializedName("is_delivering_now")
    private String isDeliveringNow;

    public RestaurantModel(int id, String name, String featuredImage, UserRatingModel userRating,
                           String phoneNumber, RestaurantLocationModel location, String cuisines,
                           String isDeliveringNow) {
        this.id = id;
        this.name = name;
        this.featuredImage = featuredImage;
        this.userRating = userRating;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.cuisines = cuisines;
        this.isDeliveringNow = isDeliveringNow;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFeaturedImage() {
        return featuredImage;
    }

    public UserRatingModel getUserRating() {
        return userRating;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCuisines() {
        return cuisines;
    }

    public RestaurantLocationModel getLocation() {
        return location;
    }

    public String getIsDeliveringNow() {
        return isDeliveringNow;
    }

}
