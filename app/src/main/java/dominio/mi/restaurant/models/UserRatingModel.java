package dominio.mi.restaurant.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dumevi Cruces on 12/07/17.
 */

public class UserRatingModel {
    @SerializedName("aggregate_rating")
    private String aggregateRating;

    @SerializedName("rating_text")
    private String ratingText;

    public String getAggregateRating() {
        return aggregateRating;
    }

    public String getRatingText() {
        return ratingText;
    }
}
