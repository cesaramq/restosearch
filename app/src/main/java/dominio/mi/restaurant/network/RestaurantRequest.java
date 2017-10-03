package dominio.mi.restaurant.network;

import dominio.mi.restaurant.Utils;
import dominio.mi.restaurant.models.CategoryModel;
import dominio.mi.restaurant.models.RestaurantListModel;
import dominio.mi.restaurant.models.RestaurantMenuModel;
import dominio.mi.restaurant.models.RestaurantModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by Dumevi Cruces on 28/06/17.
 */

public interface RestaurantRequest {

    @Headers("user-key: " + Utils.USER_KEY)
    @GET("categories")
    Call<CategoryModel> getCategories();

    @Headers("user-key: " + Utils.USER_KEY)
    @GET("search")
    Call<RestaurantListModel> getRestaurants(@Query("count") int page, @Query("start") int start,
                                             @Query("category") int category);

    @Headers("user-key: " + Utils.USER_KEY)
    @GET("search")
    Call<RestaurantListModel> getRestaurantSearch(@Query("count") int page, @Query("start") int start,
                                                  @Query("category") int category, @Query("q") String q);

    @Headers("user-key: " + Utils.USER_KEY)
    @GET("restaurant")
    Call<RestaurantModel> getRestaurant(@Query("res_id") int resId);

    @Headers("user-key: " + Utils.USER_KEY)
    @GET("dailymenu")
    Call<RestaurantMenuModel> getRestaurantMenu(@Query("res_id") int resId);
}
