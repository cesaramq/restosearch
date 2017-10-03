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
import dominio.mi.restaurant.models.RestaurantDetailsModel;
import dominio.mi.restaurant.models.RestaurantModel;
import dominio.mi.restaurant.network.RestaurantRequest;
import dominio.mi.restaurant.ui.adapters.RestaurantDetailsAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Dumevi Cruces on 21/07/17.
 */

public class RestaurantDetailsFragment extends Fragment {
    private RestaurantRequest restaurantRequest;
    private List<RestaurantDetailsModel> restaurantModels;
    private RestaurantDetailsAdapter restaurantDetailsAdapter;
    private OnLoadingActions onLoadingActions;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_restaurant_details, container, false);

        Retrofit retrofitRestautant = Utils.getRetrofit();
        restaurantRequest = retrofitRestautant.create(RestaurantRequest.class);

        setRecyclerViews(viewGroup);
        getRestaurantData();

        return viewGroup;
    }

    private void setRecyclerViews(ViewGroup viewGroup) {
        RecyclerView recyclerRestaurantDetails = (RecyclerView) viewGroup.findViewById(R.id.recycler_restaurant_details);
        restaurantModels = new ArrayList<>();
        restaurantDetailsAdapter = new RestaurantDetailsAdapter(restaurantModels, getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerRestaurantDetails.setLayoutManager(layoutManager);
        recyclerRestaurantDetails.setAdapter(restaurantDetailsAdapter);
    }

    private void getRestaurantData() {

        restaurantRequest.getRestaurant(getArguments().getInt("id")).enqueue(new Callback<RestaurantModel>() {
            @Override
            public void onResponse(Call<RestaurantModel> call, Response<RestaurantModel> response) {
                if (response.body() != null) {
                    restaurantModels.add(new RestaurantDetailsModel("Location",
                            response.body().getLocation().getAddress(), 1, response.body().getId()));
                    if (response.body().getPhoneNumber() != null && !response.body().getPhoneNumber().isEmpty()) {
                        restaurantModels.add(new RestaurantDetailsModel("Phone number", response.body().getPhoneNumber(), 2, response.body().getId()));
                    } else {
                        restaurantModels.add(new RestaurantDetailsModel("Phone number", "No phone number", 2, response.body().getId()));
                    }
                    restaurantModels.add(new RestaurantDetailsModel("Cuisine", response.body().getCuisines(), 3, response.body().getId()));
                    restaurantDetailsAdapter.notifyDataSetChanged();
                    if (onLoadingActions != null) {
                        onLoadingActions.onLoadingReady();
                    }
                } else {
                    Toast.makeText(getActivity(), "Sorry, an error occurred",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RestaurantModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Sorry, an error occurred",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void setOnLoadingActions(OnLoadingActions onLoadingActions) {
        this.onLoadingActions = onLoadingActions;
    }

    public interface OnLoadingActions {
        void onLoadingReady();
    }

}
