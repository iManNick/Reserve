package iman.reserve.hotel.activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mikepenz.materialdrawer.Drawer;

import java.util.ArrayList;
import java.util.ResourceBundle;

import iman.reserve.hotel.R;
import iman.reserve.hotel.adapter.HotelAdapter;
import iman.reserve.hotel.model.HotelList;
import iman.reserve.hotel.network.GetFavDataService;
import iman.reserve.hotel.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavActivity extends HotelActivity {
    private Drawer drawer;
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen())
            drawer.closeDrawer();
        else
        finish();
    }
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotelist);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        toolbar.setTitle("علاقه مندی ها");
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        drawer = DrawerUtil.getDrawer(this, toolbar,getApplicationContext());
        drawer.setSelection(2);
        initCollapsingToolbar("علاقه مندی ها", "علاقه مندی ها");

        RecyclerView recyclerView = findViewById(R.id.recycler_view_hotel);

        //Toast.makeText(this, city,Toast.LENGTH_SHORT);


        //adapter = new HotelAdapter(this, hotelList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.setAdapter(adapter);
        GetFavDataService service = RetrofitInstance.getRetrofitInstance().create(GetFavDataService.class);

        /*Call the method with parameter in the interface to get the employee data*/
        SharedPreferences prefs = getSharedPreferences("UserData", MODE_PRIVATE);
        String username = prefs.getString("username","");

        prepareHotels(service.getFavData(username),recyclerView,true);
        Glide.with(this).load(R.drawable.favcover).into((ImageView) findViewById(R.id.backdrop2));
    }
}



