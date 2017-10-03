package dominio.mi.restaurant.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import dominio.mi.restaurant.R;
import dominio.mi.restaurant.Utils;
import dominio.mi.restaurant.models.RestaurantDetailsModel;
import dominio.mi.restaurant.ui.activities.RestaurantLocationActivity;
import dominio.mi.restaurant.ui.viewholders.RestaurantDetailsViewHolder;

/**
 * Created by Dumevi Cruces on 19/07/17.
 */

public class RestaurantDetailsAdapter extends RecyclerView.Adapter<RestaurantDetailsViewHolder> {
    private List<RestaurantDetailsModel> restaurantModel;
    private Context context;

    public RestaurantDetailsAdapter(List<RestaurantDetailsModel> restaurantModel, Context context) {
        this.restaurantModel = restaurantModel;
        this.context = context;
    }

    @Override
    public RestaurantDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_details, parent, false);
        return new RestaurantDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RestaurantDetailsViewHolder holder, final int position) {
        final RestaurantDetailsModel model = restaurantModel.get(position);
        holder.setData(model);
        Utils.log("posicion en el adaptador" + position);
        if (model.getType() == 1) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, RestaurantLocationActivity.class);
                    intent.putExtra(RestaurantLocationActivity.RESTAURANT_ID, model.getId());
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return restaurantModel.size();
    }

    public void loadList(RestaurantDetailsModel newList) {
        restaurantModel.add(newList);
        notifyDataSetChanged();
    }
}
