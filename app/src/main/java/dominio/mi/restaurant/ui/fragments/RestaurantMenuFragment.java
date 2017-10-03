package dominio.mi.restaurant.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dominio.mi.restaurant.R;
import dominio.mi.restaurant.Utils;
import dominio.mi.restaurant.models.DishesModel;
import dominio.mi.restaurant.models.RestaurantMenuModel;
import dominio.mi.restaurant.network.RestaurantRequest;
import dominio.mi.restaurant.ui.adapters.RestaurantDishesAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Dumevi Cruces on 21/07/17.
 */

public class RestaurantMenuFragment extends Fragment {
    private RestaurantRequest restaurantRequestMenu;
    private RestaurantDishesAdapter restaurantDishesAdapter;
    private List<DishesModel> dishesList;
    private OnLoadingMenu onLoadingMenu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_restaurant_menu, container, false);

        Retrofit retrofitRestautant = Utils.getRetrofit();
        restaurantRequestMenu = retrofitRestautant.create(RestaurantRequest.class);

        setRecyclerViews(viewGroup);

        getRestaurantMenu();

        return viewGroup;
    }

    private void getRestaurantMenu() {
        restaurantRequestMenu.getRestaurantMenu(16507624).enqueue(new Callback<RestaurantMenuModel>() {
            @Override
            public void onResponse(Call<RestaurantMenuModel> call, Response<RestaurantMenuModel> response) {
                if (response.body().getDailyMenus() != null) {
                    restaurantDishesAdapter.loadList(response.body().getDailyMenus().get(0).getDailyMenu().getDishes());
                    if (onLoadingMenu != null) {
                        onLoadingMenu.onLoadingReady();
                    }
                } else {
                    Toast.makeText(getActivity(), "Sorry, an error occurred",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RestaurantMenuModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Sorry, an error occurred",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setRecyclerViews(ViewGroup viewGroup) {
        RecyclerView recyclerDishes = (RecyclerView) viewGroup.findViewById(R.id.recycler_dishes);
        dishesList = new ArrayList<>();
        restaurantDishesAdapter = new RestaurantDishesAdapter(dishesList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerDishes.setLayoutManager(linearLayoutManager);
        recyclerDishes.setAdapter(restaurantDishesAdapter);
    }

    public void setOnLoadingMenu(OnLoadingMenu onLoadingMenu) {
        this.onLoadingMenu = onLoadingMenu;
    }

    public interface OnLoadingMenu {
        void onLoadingReady();
    }
}
