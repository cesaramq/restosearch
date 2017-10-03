package dominio.mi.restaurant.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import dominio.mi.restaurant.R;
import dominio.mi.restaurant.models.RestaurantIntermedyModel;
import dominio.mi.restaurant.ui.activities.RestaurantsActivity;
import dominio.mi.restaurant.ui.viewholders.RestaurantsViewHolder;

/**
 * Created by Dumevi Cruces on 06/07/17.
 */

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsViewHolder> {
    private List<RestaurantIntermedyModel> restaurantList;
    private Context context;

    public RestaurantsAdapter(List<RestaurantIntermedyModel> restaurantList, Context context) {
        this.restaurantList = restaurantList;
        this.context = context;
    }

    @Override
    public RestaurantsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_item, parent, false);
        return new RestaurantsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RestaurantsViewHolder holder, int position) {
        final RestaurantIntermedyModel model = restaurantList.get(position);
        String url = restaurantList.get(position).getRestaurantModel().getFeaturedImage();
        if (url != null && !url.isEmpty()) {
            Glide.with(context).load(url).into(holder.getRestaurantImage());
        } else {
            url = "https://b.zmtcdn.com/images/placeholder_200.png";
            Glide.with(context).load(url).into(holder.getRestaurantImage());
        }

        holder.setData(model);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RestaurantsActivity.class);
                intent.putExtra(RestaurantsActivity.RESTAURANT_ID, model.getRestaurantModel().getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public void loadList(List<RestaurantIntermedyModel> newList) {
        restaurantList.addAll(newList);
        notifyDataSetChanged();
    }
}
