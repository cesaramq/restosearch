package dominio.mi.restaurant.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import dominio.mi.restaurant.R;
import dominio.mi.restaurant.models.DishesModel;
import dominio.mi.restaurant.ui.viewholders.RestaurantDishesViewHolder;

/**
 * Created by Dumevi Cruces on 22/07/17.
 */

public class RestaurantDishesAdapter extends RecyclerView.Adapter<RestaurantDishesViewHolder>{
    private List<DishesModel> dishes;

    public RestaurantDishesAdapter(List<DishesModel> dishes) {
        this.dishes = dishes;
    }

    @Override
    public RestaurantDishesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_menu_item, parent, false);
        return new RestaurantDishesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RestaurantDishesViewHolder holder, int position) {
        DishesModel model = dishes.get(position);
        holder.setData(model);
    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }

    public void loadList(List<DishesModel> dish) {
        dishes.addAll(dish);
        notifyDataSetChanged();
    }
}
