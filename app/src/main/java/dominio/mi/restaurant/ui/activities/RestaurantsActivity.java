package dominio.mi.restaurant.ui.activities;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.Bundler;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import dominio.mi.restaurant.R;
import dominio.mi.restaurant.Utils;
import dominio.mi.restaurant.models.RestaurantModel;
import dominio.mi.restaurant.network.RestaurantRequest;
import dominio.mi.restaurant.ui.MyActivity;
import dominio.mi.restaurant.ui.ProgressBarUtil;
import dominio.mi.restaurant.ui.fragments.RestaurantDetailsFragment;
import dominio.mi.restaurant.ui.fragments.RestaurantMenuFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RestaurantsActivity extends MyActivity {

    private int idRestaurant;
    public static final String RESTAURANT_ID = "restaurantId";
    private TextView restaurantLocationHeader;
    private ImageView restaurantImage;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private RestaurantRequest restaurantRequest;
    private Context context;
    private TextView restaurantDelivery;
    private ImageView restaurantDeliveryImage;
    private boolean starSort = false;
    private boolean restaurantsReady = false;
    private boolean fragmentDetailsReady = false;
    private boolean fragmentMenuReady = false;
    private ProgressBarUtil utilProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restautants);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);

        getToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context = this;

        Retrofit retrofitRestautant = Utils.getRetrofit();
        restaurantRequest = retrofitRestautant.create(RestaurantRequest.class);

        idRestaurant = getIntent().getIntExtra(RESTAURANT_ID, 0);
        restaurantImage = (ImageView) findViewById(R.id.restaurant_image);
        restaurantLocationHeader = (TextView) findViewById(R.id.restaurant_location_header);
        restaurantDeliveryImage = (ImageView) findViewById(R.id.ic_action_delivery);
        restaurantDelivery = (TextView) findViewById(R.id.restaurant_delivery_header);

        getRestaurantData();
        setImagenView();

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        View overlay = findViewById(R.id.overlay_view);

        utilProgressBar = new ProgressBarUtil(progressBar, overlay);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return Utils.menu(this, menu);
    }

    private void getRestaurantData() {

        restaurantRequest.getRestaurant(idRestaurant).enqueue(new Callback<RestaurantModel>() {
            @Override
            public void onResponse(Call<RestaurantModel> call, Response<RestaurantModel> response) {
                if (response.body() != null) {
                    String url = response.body().getFeaturedImage();
                    if (url != null && !url.isEmpty()) {
                        Glide.with(context).load(url).into(restaurantImage);
                    } else {
                        url = "https://b.zmtcdn.com/images/placeholder_200.png";
                        Glide.with(context).load(url).into(restaurantImage);
                    }

                    collapsingToolbarLayout.setTitle(response.body().getName());
                    restaurantLocationHeader.setText(response.body().getLocation().getCity());
                    if (response.body().getIsDeliveringNow() == "1") {
                        restaurantDelivery.setText("Delivering now");
                        restaurantDeliveryImage.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimaryText),
                                PorterDuff.Mode.SRC_ATOP);
                    } else {
                        restaurantDelivery.setText("Not delivering now");
                        restaurantDeliveryImage.setColorFilter(ContextCompat.getColor(context, R.color.red),
                                PorterDuff.Mode.SRC_ATOP);
                    }
                    restaurantsReady = true;
                    if (fragmentDetailsReady && fragmentMenuReady) {
                        utilProgressBar.hideProgressBar();
                    }
                } else {
                    Toast.makeText(RestaurantsActivity.this, "Sorry, an error occurred",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RestaurantModel> call, Throwable t) {
                Toast.makeText(RestaurantsActivity.this, "Sorry, an error occurred",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return menuCaseLogin(item);
    }

    private void setImagenView() {

        final FragmentPagerItemAdapter pagerAdapterMenu;
        final ViewPager viewPagerMenu = (ViewPager) findViewById(R.id.restaurant_pager);
        pagerAdapterMenu = new FragmentPagerItemAdapter(getSupportFragmentManager(),
                FragmentPagerItems.with(this)
                        .add("Details", RestaurantDetailsFragment.class, new Bundler().putInt("id", idRestaurant).get())
                        .add("Daily Menu", RestaurantMenuFragment.class).create());
        viewPagerMenu.setAdapter(pagerAdapterMenu);
        SmartTabLayout tabLayout = (SmartTabLayout) findViewById(R.id.restaurant_tab);
        tabLayout.setViewPager(viewPagerMenu);

        final ViewTreeObserver vto = viewPagerMenu.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //visible
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
                    viewPagerMenu.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    viewPagerMenu.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
                final RestaurantDetailsFragment fragmentPageDetails = (RestaurantDetailsFragment) pagerAdapterMenu.getPage(0);
                fragmentPageDetails.setOnLoadingActions(new RestaurantDetailsFragment.OnLoadingActions() {
                    @Override
                    public void onLoadingReady() {
                        fragmentDetailsReady = true;
                        if (fragmentMenuReady && restaurantsReady) {
                            utilProgressBar.hideProgressBar();
                        }
                    }
                });

                final RestaurantMenuFragment fragmentPageMenu = (RestaurantMenuFragment) pagerAdapterMenu.getPage(1);
                fragmentPageMenu.setOnLoadingMenu(new RestaurantMenuFragment.OnLoadingMenu() {
                    @Override
                    public void onLoadingReady() {
                        fragmentMenuReady = true;
                        if (fragmentDetailsReady && restaurantsReady) {
                            utilProgressBar.hideProgressBar();
                        }
                    }
                });
            }
        });

    }

}
