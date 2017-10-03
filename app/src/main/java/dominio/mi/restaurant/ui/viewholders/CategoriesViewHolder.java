package dominio.mi.restaurant.ui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import dominio.mi.restaurant.R;
import dominio.mi.restaurant.models.CategoryIntermedyModel;

/**
 * Created by Dumevi Cruces on 04/07/17.
 */

public class CategoriesViewHolder extends RecyclerView.ViewHolder {
    private TextView categoryName;

    public CategoriesViewHolder(View itemView) {
        super(itemView);

        categoryName = (TextView) itemView.findViewById(R.id.category_name);
    }

    public void setData(CategoryIntermedyModel category) {
        categoryName.setText(category.getCategories().getName());
    }

    public TextView getCategoryName() {
        return categoryName;
    }
}
