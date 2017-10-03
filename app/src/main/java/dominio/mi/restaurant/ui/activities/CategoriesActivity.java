package dominio.mi.restaurant.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.Bundler;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import dominio.mi.restaurant.R;
import dominio.mi.restaurant.Utils;
import dominio.mi.restaurant.models.CategoryIntermedyModel;
import dominio.mi.restaurant.models.CategoryModel;
import dominio.mi.restaurant.models.RestaurantIntermedyModel;
import dominio.mi.restaurant.models.RestaurantListModel;
import dominio.mi.restaurant.network.RestaurantRequest;
import dominio.mi.restaurant.ui.MyActivity;
import dominio.mi.restaurant.ui.ProgressBarUtil;
import dominio.mi.restaurant.ui.adapters.CategoriesAdapter;
import dominio.mi.restaurant.ui.adapters.RestaurantsAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CategoriesActivity extends MyActivity {
    private RestaurantRequest restaurantRequest;
    private CategoriesAdapter categoriesAdapter;
    private RestaurantRequest restaurantListRequest;
    private RestaurantsAdapter restaurantsAdapter;
    private String categoryName;
    private boolean categoryNameReady = false;
    private boolean categoriesReady = false;
    private boolean restaurantsReady = false;
    private ProgressBarUtil utilProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar action = getSupportActionBar();
        action.setDisplayShowTitleEnabled(false);

        getTitleApp();

        initDrawerLayout(toolbar);

        initNavigationView();

        setTextFonts();

        setImageViews();

        setRecyclerViews();

        Retrofit retrofitRestautant = Utils.getRetrofit();

        restaurantRequest = retrofitRestautant.create(RestaurantRequest.class);
        restaurantListRequest = retrofitRestautant.create(RestaurantRequest.class);

        getCategoriesData();
        getRestautantsData();

        TextView buttonMore = (TextView) findViewById(R.id.button_more);

        buttonMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (categoryNameReady) {
                    Intent intent = new Intent(CategoriesActivity.this, RestaurantsByCategoryActivity.class);
                    intent.putExtra(RestaurantsByCategoryActivity.CATEGORY, 1);
                    intent.putExtra(RestaurantsByCategoryActivity.CATEGORY_NAME, categoryName);
                    startActivity(intent);
                }
            }
        });

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        View overlay = findViewById(R.id.overlay_view);
        utilProgressBar = new ProgressBarUtil(progressBar, overlay);

    }

    private void setTextFonts() {

        TextView textLogo = (TextView) findViewById(R.id.logo_text_resto);
        textLogo.setTypeface(Utils.getFontsAcme(getAssets()));

        TextView textLogoSearch = (TextView) findViewById(R.id.logo_text_search);
        textLogoSearch.setTypeface(Utils.getFontShadow(getAssets()));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return Utils.menu(this, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return menuCaseLogin(item);
    }

    private void initDrawerLayout(Toolbar toolbar) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.action_settings, R.string.action_favorites);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void initNavigationView() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_main);
        View navigationHeader = navigationView.getHeaderView(0);
        ImageView backgroundProfile = (ImageView) navigationHeader.findViewById(R.id.background_profile);
        CircleImageView imageProfile = (CircleImageView) navigationHeader.findViewById(R.id.image_profile);

        String url = "http://vida20.com/wp-content/uploads/2014/05/portadas-nuevo-twitter-1500x500-4.jpg";
        String urlProfile = "http://img.huffingtonpost.com/asset/,scalefit_950_800_noupscale/57c4a6ce180000dd10bcde68.jpeg";

        Glide.with(this).load(url).into(backgroundProfile);
        Glide.with(this).load(urlProfile).into(imageProfile);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.nav_settings:
                        Intent intent = new Intent(CategoriesActivity.this, UserActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_favorites:
                        break;

                    case R.id.nav_about:
                        Intent intentAbout = new Intent(CategoriesActivity.this, AboutActivity.class);
                        startActivity(intentAbout);
                        break;
                }
                return false;

            }
        });
    }

    private void getCategoriesData() {
        restaurantRequest.getCategories().enqueue(new Callback<CategoryModel>() {
            @Override
            public void onResponse(Call<CategoryModel> call, Response<CategoryModel> response) {
                if (response.body().getCategory() != null) {
                    categoriesAdapter.loadList(response.body().getCategory());
                    categoryName = response.body().getCategory().get(1).getCategories().getName();
                    if (categoryName != null && !categoryName.isEmpty()) {
                        categoryNameReady = true;
                    }
                    categoriesReady = true;
                    if (restaurantsReady) {
                        utilProgressBar.hideProgressBar();
                    }
                } else {
                    Toast.makeText(CategoriesActivity.this, "Sorry, an error occurred",
                            Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<CategoryModel> call, Throwable t) {
                Toast.makeText(CategoriesActivity.this, "Sorry, an error occurred",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getRestautantsData() {
        restaurantListRequest.getRestaurants(5, 0, 1).enqueue(new Callback<RestaurantListModel>() {
            @Override
            public void onResponse(Call<RestaurantListModel> call, Response<RestaurantListModel> response) {
                if (response.body().getRestaurantsModel() != null) {
                    restaurantsAdapter.loadList(response.body().getRestaurantsModel());
                    restaurantsReady = true;
                    if (categoriesReady) {
                        utilProgressBar.hideProgressBar();
                    }
                } else {
                    Toast.makeText(CategoriesActivity.this, "Sorry, an error occurred",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RestaurantListModel> call, Throwable t) {
                Toast.makeText(CategoriesActivity.this, "Sorry, an error occurred",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setRecyclerViews() {
        RecyclerView recyclerCategory = (RecyclerView) findViewById(R.id.recycler_categories);
        List<CategoryIntermedyModel> categoriesList = new ArrayList<>();
        categoriesAdapter = new CategoriesAdapter(categoriesList, this);

        LinearLayoutManager linearLayoutManagerCategories = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerCategory.setLayoutManager(linearLayoutManagerCategories);
        recyclerCategory.setAdapter(categoriesAdapter);

        RecyclerView recyclerRestaurant = (RecyclerView) findViewById(R.id.recycler_restaurants);
        List<RestaurantIntermedyModel> restaurantList = new ArrayList<>();
        restaurantsAdapter = new RestaurantsAdapter(restaurantList, this);

        LinearLayoutManager linearLayoutManagerRestaurant = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerRestaurant.setLayoutManager(linearLayoutManagerRestaurant);
        recyclerRestaurant.setAdapter(restaurantsAdapter);
    }

    private void setImageViews() {
        FragmentPagerAdapter pagerItem;
        ViewPager viewPagerHeader = (ViewPager) findViewById(R.id.slide_header_pager);
        pagerItem = new FragmentPagerItemAdapter(getSupportFragmentManager(),
                FragmentPagerItems.with(this)
                        .add("one", SlideHeaderFragment.class, new Bundler()
                                .putString("url", "http://www.southeastradio.ie/wp-content/uploads/2017/03/restaurant.jpg").get())
                        .add("two", SlideHeaderFragment.class, new Bundler()
                                .putString("url", "http://johnnytsbistroandblues.com/wp-content/uploads/2014/05/shrimp.jpg").get())
                        .add("three", SlideHeaderFragment.class, new Bundler()
                                .putString("url", "http://weknowyourdreams.com/images/restaurant/restaurant-05.jpg").get())
                        .create());

        viewPagerHeader.setAdapter(pagerItem);
        SmartTabLayout tabLayout = (SmartTabLayout) findViewById(R.id.slide_header_pager_tab);
        tabLayout.setViewPager(viewPagerHeader);
    }

}
