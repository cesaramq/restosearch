package dominio.mi.restaurant.ui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import dominio.mi.restaurant.R;
import dominio.mi.restaurant.models.RestaurantIntermedyModel;

/**
 * Created by Dumevi Cruces on 06/07/17.
 */

public class RestaurantsViewHolder extends RecyclerView.ViewHolder {
    private TextView restaurantName;
    private ImageView restaurantImage;

    public RestaurantsViewHolder(View itemView) {
        super(itemView);
        restaurantName = (TextView) itemView.findViewById(R.id.restaurant_name);
        restaurantImage = (ImageView) itemView.findViewById(R.id.restaurant_image);
    }

    public TextView getRestaurantName() {
        return restaurantName;
    }

    public ImageView getRestaurantImage() {
        return restaurantImage;
    }

    public void setData(RestaurantIntermedyModel restaurant) {
        restaurantName.setText(restaurant.getRestaurantModel().getName());
    }
}
