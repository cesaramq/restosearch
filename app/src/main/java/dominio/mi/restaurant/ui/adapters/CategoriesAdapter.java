package dominio.mi.restaurant.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import dominio.mi.restaurant.R;
import dominio.mi.restaurant.models.CategoryIntermedyModel;
import dominio.mi.restaurant.ui.activities.RestaurantsByCategoryActivity;
import dominio.mi.restaurant.ui.viewholders.CategoriesViewHolder;

/**
 * Created by Dumevi Cruces on 04/07/17.
 */

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesViewHolder> {
    private List<CategoryIntermedyModel> categoryList;
    private Context context;

    public CategoriesAdapter(List<CategoryIntermedyModel> categoryList, Context context) {
        this.categoryList = categoryList;
        this.context = context;
    }

    @Override
    public CategoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new CategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoriesViewHolder holder, final int position) {
        final CategoryIntermedyModel model = categoryList.get(position);
        holder.setData(model);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RestaurantsByCategoryActivity.class);
                intent.putExtra(RestaurantsByCategoryActivity.CATEGORY, model.getCategories().getId());
                intent.putExtra(RestaurantsByCategoryActivity.CATEGORY_NAME, model.getCategories().getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public void loadList(List<CategoryIntermedyModel> newList) {
        categoryList.addAll(newList);
        notifyDataSetChanged();
    }
}
