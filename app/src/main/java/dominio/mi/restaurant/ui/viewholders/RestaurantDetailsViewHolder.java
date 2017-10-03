package dominio.mi.restaurant.ui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import dominio.mi.restaurant.R;
import dominio.mi.restaurant.models.RestaurantDetailsModel;

/**
 * Created by Dumevi Cruces on 19/07/17.
 */

public class RestaurantDetailsViewHolder extends RecyclerView.ViewHolder {
    private TextView restaurantLabel;
    private TextView restaurantText;
    private ImageView icon;
    private ImageView iconDetails;

    public RestaurantDetailsViewHolder(View itemView) {
        super(itemView);
        restaurantLabel = (TextView) itemView.findViewById(R.id.restaurant_label);
        restaurantText = (TextView) itemView.findViewById(R.id.restaurant_text);
        icon = (ImageView) itemView.findViewById(R.id.ic_action_view_location);
        iconDetails = (ImageView) itemView.findViewById(R.id.icon_details);
    }

    public TextView getRestaurantLabel() {
        return restaurantLabel;
    }

    public TextView getRestaurantText() {
        return restaurantText;
    }

    public void setData(RestaurantDetailsModel restaurantModel) {
        restaurantLabel.setText(restaurantModel.getRestaurantTitle());
        restaurantText.setText(restaurantModel.getGetRestaurantDescription());
        if(restaurantModel.getType() != 1) {
            icon.setVisibility(View.GONE);
        }

        if(restaurantModel.getType() == 1) {
            iconDetails.setImageResource(R.drawable.ic_action_location);
        } else if(restaurantModel.getType() == 2) {
            iconDetails.setImageResource(R.drawable.ic_action_phone);
        } else if(restaurantModel.getType() == 3) {
            iconDetails.setImageResource(R.drawable.ic_action_cuisine);
        }
    }
}
