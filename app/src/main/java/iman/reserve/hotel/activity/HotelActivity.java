package iman.reserve.hotel.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mikepenz.materialdrawer.Drawer;

import java.util.ArrayList;
import java.util.List;

import iman.reserve.hotel.R;
import iman.reserve.hotel.adapter.HotelAdapter;
import iman.reserve.hotel.model.Hotel;
import iman.reserve.hotel.model.HotelList;
import iman.reserve.hotel.network.GetHotelDataService;
import iman.reserve.hotel.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelActivity extends AppCompatActivity {
    private Drawer drawer;
    private String city;
    private RecyclerView recyclerView;

    /*    @Override
        public void onBackPressed() {
            if(drawer.isDrawerOpen())
                drawer.closeDrawer();
            else {
                Intent intent = new Intent(this, CityActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                finish();
            }
        }*/
    @Override
    public void onBackPressed() {
    if (drawer.isDrawerOpen())
        drawer.closeDrawer();
    else
        finish();
    }


    protected void call(){
        /*Create handle for the RetrofitInstance interface*/
        GetHotelDataService service = RetrofitInstance.getRetrofitInstance().create(GetHotelDataService.class);

        /*Call the method with parameter in the interface to get the employee data*/
        Call<HotelList> call = service.getHotelData(city);

        prepareHotels(call,false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        city = intent.getStringExtra("CITYNAME");

        setContentView(R.layout.activity_hotelist);
        //setContentView(R.layout.activity_city);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        //setSupportActionBar(toolbar);
        toolbar.setTitle(city);

            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            drawer = DrawerUtil.getDrawer(this,toolbar,getApplicationContext());

            //drawer.getActionBarDrawerToggle().setDrawerIndicatorEnabled(false);
            //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initCollapsingToolbar("هتل های " + city, city);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_hotel);

        //Toast.makeText(this, city,Toast.LENGTH_SHORT);

        //hotelList = new ArrayList<>();
        //adapter = new HotelAdapter(this, hotelList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.setAdapter(adapter);
        call();



        //int cover = getResources().getIdentifier(city, "drawable", getPackageName());
            int cover = R.drawable.tehran;
        try {
        switch (city)
        {
            case "مشهد" :
                cover = R.drawable.mashhad;
                break;
            case "شیراز" :
                cover = R.drawable.shiraz;
                break;
            case "اصفهان" :
                cover = R.drawable.esfahan;
                break;
            case "یزد":
                cover = R.drawable.yazd;
                break;
            case "تبریز":
                cover = R.drawable.tabriz;
                break;
            case "رشت":
                cover = R.drawable.rasht;
                break;
            case "کیش":
                cover = R.drawable.kish;
                break;
            case "ساری":
                cover = R.drawable.sari;
                break;
            case "بابل":
                cover = R.drawable.babol;
                break;

        }
            Glide.with(this).load(cover).into((ImageView) findViewById(R.id.backdrop2));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
*/
    protected void initCollapsingToolbar(final String title1 ,final String title2 ) {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar2);
        collapsingToolbar.setTitle("");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar2);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(title1);
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(title2);
                    isShow = false;
                }
            }
        });
    }


    protected void prepareHotels(Call<HotelList> call, final Boolean isfav) {
        /*Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<HotelList>() {
            @Override
            public void onResponse(Call<HotelList> call, Response<HotelList> response) {

                ArrayList<Hotel> hotelList = response.body().getHotelArrayList();
                for(int i = 0 ; i< hotelList.size() ; i++ ) {
                    int hid = hotelList.get(i).getHotelID();

                    String str = "hotel" + hid;
                    int cover = getResources().getIdentifier(str, "drawable", getPackageName());
                    //int cover = R.drawable.hotel + hid;
                    hotelList.get(i).setThumbnail(cover);

                }
                HotelAdapter adapter = new HotelAdapter(getApplicationContext(), hotelList,isfav);
                recyclerView.setAdapter(adapter);
                //adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<HotelList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), R.string.no_internet, Toast.LENGTH_SHORT).show();
            }
        });



    }








    /**
     * Adding few albums for testing
     *
    private void prepareTehHotels(String hotel) {
        int[] covers = new int[]{
                R.drawable.teh0,
                R.drawable.teh1,
                R.drawable.teh0,
                R.drawable.teh0,
                R.drawable.teh0,
                R.drawable.teh0,
                R.drawable.teh0,
                R.drawable.teh0,
                R.drawable.teh0,
                R.drawable.teh0,
                R.drawable.teh0
        };

        Hotel a = new Hotel(0,"هتل استقلال تهران", 5, covers[0],"تقاطع بزرگراه شهید چمران و ولی عصر\nامکانات رفاهی: کافی شاپ - سالن بدن سازی - فروشگاه و غرفه - استخر - رستوران - کافی نت - پارکینگ رایگان با ظرفیت 1000 ماشین");
        hotelList.add(a);

        a = new Hotel(1,"هتل آزادی تهران", 5, covers[1],"بزرگراه شهید چمران - تقاطع اوین - هتل ازادی\nامکانات رفاهی: کافی شاپ - سالن بدن سازی - فروشگاه و غرفه - استخر - رستوران - کافی نت - پارکینگ رایگان باسقف مسقف 50 و غیرمسقف 50ماشین");
        hotelList.add(a);

        a = new Hotel(2,"هتل 3", 3, covers[2],"");
        hotelList.add(a);

        a = new Hotel(3,"هتل 4", 1, covers[3],"");
        hotelList.add(a);

        a = new Hotel(4,"هتل 5", 4, covers[4],"");
        hotelList.add(a);

        a = new Hotel(5,"هتل 6", 2, covers[5],"");
        hotelList.add(a);

        a = new Hotel(6,"هتل 7", 2, covers[6],"");
        hotelList.add(a);

        a = new Hotel(7,"هتل 8", 4, covers[7],"");
        hotelList.add(a);

        a = new Hotel(8,"هتل 9", 4, covers[8],"");
        hotelList.add(a);

        a = new Hotel(9,"هتل 10", 2, covers[9],"");
        hotelList.add(a);

        adapter.notifyDataSetChanged();
    }

    private void prepareMashHotels() {
        int[] covers = new int[]{
                R.drawable.teh2,
                R.drawable.teh1,
                R.drawable.teh1,
                R.drawable.teh1,
                R.drawable.teh1,
                R.drawable.teh1,
                R.drawable.teh1,
                R.drawable.teh1,
                R.drawable.teh1,
                R.drawable.teh1,
                R.drawable.teh1
                };


        Hotel a = new Hotel(10,"هتل مشد", 5, covers[0],"جای خوبیست بسیار عالی");
        hotelList.add(a);

        a = new Hotel(11,"هتل 2", 2, covers[1],"");
        hotelList.add(a);

        a = new Hotel(12,"هتل 3", 3, covers[2],"");
        hotelList.add(a);

        a = new Hotel(13,"هتل 4", 1, covers[3],"");
        hotelList.add(a);

        a = new Hotel(14,"هتل 5", 4, covers[4],"");
        hotelList.add(a);

        a = new Hotel(15,"هتل 6", 2, covers[5],"");
        hotelList.add(a);

        a = new Hotel(16,"هتل 7", 2, covers[6],"");
        hotelList.add(a);

        a = new Hotel(17,"هتل 8", 4, covers[7],"");
        hotelList.add(a);
/*
        a = new Hotel("هتل 9", 4, covers[8],"");
        hotelList.add(a);

        a = new Hotel("هتل 10", 2, covers[9],"");
        hotelList.add(a);

        adapter.notifyDataSetChanged();
    }


    **
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            outRect.right = spacing ;
            outRect.left = spacing ;

            if (includeEdge) {
//                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
//                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
//                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
//                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    protected int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}
