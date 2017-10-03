package dominio.mi.restaurant.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import dominio.mi.restaurant.R;
import dominio.mi.restaurant.Utils;
import dominio.mi.restaurant.models.RestaurantModel;
import dominio.mi.restaurant.network.RestaurantRequest;
import dominio.mi.restaurant.ui.MyActivity;
import dominio.mi.restaurant.ui.ProgressBarUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RestaurantLocationActivity extends MyActivity implements OnMapReadyCallback {
    public static final String RESTAURANT_ID = "restaurantId";
    private RestaurantRequest restaurantRequest;
    private int idRestaurant;
    private GoogleMap restaurantMap;
    private double latitude;
    private double longitude;
    private boolean mapReady = false;
    private boolean requestReady = false;
    private ProgressBarUtil utilProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_location);

        getToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Retrofit retrofitRestautant = Utils.getRetrofit();
        restaurantRequest = retrofitRestautant.create(RestaurantRequest.class);

        idRestaurant = getIntent().getIntExtra(RESTAURANT_ID, 0);
        getRestaurantData();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        View overlay = findViewById(R.id.overlay_view);

        utilProgressBar = new ProgressBarUtil(progressBar, overlay);

    }

    private void getRestaurantData() {
        restaurantRequest.getRestaurant(idRestaurant).enqueue(new Callback<RestaurantModel>() {
            @Override
            public void onResponse(Call<RestaurantModel> call, Response<RestaurantModel> response) {
                if (response.body() != null) {
                    latitude = Double.parseDouble(response.body().getLocation().getLatitude());
                    longitude = Double.parseDouble(response.body().getLocation().getLongitude());
                    requestReady = true;
                    String name = response.body().getName();

                    if (mapReady && requestReady) {
                        moveMapCamera();
                        utilProgressBar.hideProgressBar();
                    }

                    if (name != null && !name.isEmpty()) {
                        getSupportActionBar().setTitle(name);
                    }
                } else {
                    Toast.makeText(RestaurantLocationActivity.this, "Sorry, an error occurred",
                            Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<RestaurantModel> call, Throwable t) {
                Toast.makeText(RestaurantLocationActivity.this, "Sorry, an error occurred",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        restaurantMap = googleMap;
        mapReady = true;

        if (mapReady && requestReady) {
            moveMapCamera();
            utilProgressBar.hideProgressBar();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return Utils.menu(this, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return menuCaseLogin(item);
    }

    private void moveMapCamera() {
        LatLng position = new LatLng(latitude, longitude);
        restaurantMap.addMarker(new MarkerOptions().position(position));
        restaurantMap.moveCamera(CameraUpdateFactory.newLatLng(position));
        restaurantMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));
    }

}
