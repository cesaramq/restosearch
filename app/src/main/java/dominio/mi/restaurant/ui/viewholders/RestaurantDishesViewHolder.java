package dominio.mi.restaurant.ui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import dominio.mi.restaurant.R;
import dominio.mi.restaurant.models.DishesModel;

/**
 * Created by Dumevi Cruces on 22/07/17.
 */

public class RestaurantDishesViewHolder extends RecyclerView.ViewHolder {
    private TextView dishName;
    private TextView dishPrice;

    public RestaurantDishesViewHolder(View itemView) {
        super(itemView);
        dishName = (TextView) itemView.findViewById(R.id.dish_name);
        dishPrice = (TextView) itemView.findViewById(R.id.dish_price);
    }

    public TextView getDishName() {
        return dishName;
    }

    public TextView getDishPrice() {
        return dishPrice;
    }

    public void setData(DishesModel dishes) {
        dishName.setText(dishes.getDish().getName());
        dishPrice.setText(dishes.getDish().getPrice());
    }
}
