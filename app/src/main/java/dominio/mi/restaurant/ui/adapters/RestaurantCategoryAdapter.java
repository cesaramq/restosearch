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
import dominio.mi.restaurant.ui.viewholders.FooterProgressBarViewHolder;
import dominio.mi.restaurant.ui.viewholders.RestaurantsByCategoryViewHolder;

/**
 * Created by Dumevi Cruces on 06/07/17.
 */

public class RestaurantCategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<RestaurantIntermedyModel> restaurantList;
    private Context context;
    private int listItemType = 1;
    private int footerType = 2;
    private int sizeNewList;
    private FooterProgressBarViewHolder footerProgressBarViewHolder;

    public RestaurantCategoryAdapter(List<RestaurantIntermedyModel> restaurantList, Context context) {
        this.restaurantList = restaurantList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == listItemType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_grid_item,
                    parent, false);
            return new RestaurantsByCategoryViewHolder(view);
        } else if (viewType == footerType) {
            View footer = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_progressbar,
                    parent, false);
            footerProgressBarViewHolder = new FooterProgressBarViewHolder(footer);
            return footerProgressBarViewHolder;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == listItemType) {
            final RestaurantIntermedyModel model = restaurantList.get(position);
            String url = restaurantList.get(position).getRestaurantModel().getFeaturedImage();
            if (url != null && !url.isEmpty()) {
                Glide.with(context).load(url).into(((RestaurantsByCategoryViewHolder) holder).getRestaurantImage());
            } else {
                url = "https://b.zmtcdn.com/images/placeholder_200.png";
                Glide.with(context).load(url).into(((RestaurantsByCategoryViewHolder) holder).getRestaurantImage());
            }

            ((RestaurantsByCategoryViewHolder) holder).setData(model);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, RestaurantsActivity.class);
                    intent.putExtra(RestaurantsActivity.RESTAURANT_ID, model.getRestaurantModel().getId());
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return restaurantList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return footerType;
        } else {
            return listItemType;
        }
    }

    public void loadList(List<RestaurantIntermedyModel> newList) {
        sizeNewList = newList.size();
        footerProgressBarViewHolder.setFooter(sizeNewList);
        restaurantList.addAll(newList);
        notifyDataSetChanged();
    }

    public void loadListSearch(List<RestaurantIntermedyModel> newList, int numStart) {
        if (numStart == 0) {
            restaurantList.clear();
        }
        sizeNewList = newList.size();
        footerProgressBarViewHolder.setFooter(sizeNewList);
        restaurantList.addAll(newList);
        notifyDataSetChanged();
    }

    public void clearRestaurantList() {
        restaurantList.clear();
        sizeNewList = 0;
        notifyDataSetChanged();
    }
}
