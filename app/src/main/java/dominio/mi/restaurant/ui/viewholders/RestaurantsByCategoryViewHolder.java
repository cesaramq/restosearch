package dominio.mi.restaurant.ui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import dominio.mi.restaurant.R;
import dominio.mi.restaurant.models.RestaurantIntermedyModel;

/**
 * Created by Dumevi Cruces on 12/07/17.
 */

public class RestaurantsByCategoryViewHolder extends RecyclerView.ViewHolder {
    private TextView restaurantName;
    private ImageView restaurantImage;
    private TextView restaurantRating;
    private TextView restaurantAggregateRating;

    public RestaurantsByCategoryViewHolder(View itemView) {
        super(itemView);
        restaurantName = (TextView) itemView.findViewById(R.id.restaurant_name);
        restaurantImage = (ImageView) itemView.findViewById(R.id.restaurant_image);
        restaurantRating = (TextView) itemView.findViewById(R.id.restaurant_rating);
        restaurantAggregateRating = (TextView) itemView.findViewById(R.id.restaurant_aggregate_rating);
    }

    public TextView getRestaurantName() {
        return restaurantName;
    }

    public ImageView getRestaurantImage() {
        return restaurantImage;
    }

    public TextView getRestaurantRating() {
        return restaurantRating;
    }

    public TextView getRestaurantAggregateRating() {
        return restaurantAggregateRating;
    }

    public void setData(RestaurantIntermedyModel restaurant) {
        restaurantName.setText(restaurant.getRestaurantModel().getName());
        restaurantRating.setText(restaurant.getRestaurantModel().getUserRating().getRatingText());
        restaurantAggregateRating.setText(restaurant.getRestaurantModel().getUserRating().getAggregateRating());
    }
}
