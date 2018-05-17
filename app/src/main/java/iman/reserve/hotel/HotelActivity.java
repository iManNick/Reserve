package iman.reserve.hotel;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import iman.reserve.hotel.R;

public class HotelActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HotelAdapter adapter;
    private List<Hotel> hotelList;
    private String city;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, CityActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
        finish();
    }
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        city = intent.getStringExtra("CITYNAME");

        setContentView(R.layout.activity_hotelist);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        //setSupportActionBar(toolbar);
        toolbar.setTitle(city);

        initCollapsingToolbar();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_hotel);

        //Toast.makeText(this, city,Toast.LENGTH_SHORT);

        hotelList = new ArrayList<>();
        adapter = new HotelAdapter(this, hotelList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        try {
        switch (city)
        {
            case "تهران" :
                prepareTehHotels();
                Glide.with(this).load(R.drawable.teh).into((ImageView) findViewById(R.id.backdrop2));
                break;
            case "مشهد" :
                prepareMashHotels();
                Glide.with(this).load(R.drawable.mash).into((ImageView) findViewById(R.id.backdrop2));
                break;
        }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
*/
    private void initCollapsingToolbar() {
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
                    collapsingToolbar.setTitle("هتل های " + city);
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(city);
                    isShow = false;
                }
            }
        });
    }

    /**
     * Adding few albums for testing
     */
    private void prepareTehHotels() {
        int[] covers = new int[]{
                R.drawable.teh1,
                R.drawable.teh2,

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

        Hotel a = new Hotel("هتل استقلال تهران", 5, covers[0],"تقاطع بزرگراه شهید چمران و ولی عصر\nامکانات رفاهی: کافی شاپ - سالن بدن سازی - فروشگاه و غرفه - استخر - رستوران - کافی نت - پارکینگ رایگان با ظرفیت 1000 ماشین");
        hotelList.add(a);

        a = new Hotel("هتل آزادی تهران", 5, covers[1],"بزرگراه شهید چمران - تقاطع اوین - هتل ازادی\nامکانات رفاهی: کافی شاپ - سالن بدن سازی - فروشگاه و غرفه - استخر - رستوران - کافی نت - پارکینگ رایگان باسقف مسقف 50 و غیرمسقف 50ماشین");
        hotelList.add(a);

        a = new Hotel("هتل 3", 3, covers[2],"");
        hotelList.add(a);

        a = new Hotel("هتل 4", 1, covers[3],"");
        hotelList.add(a);

        a = new Hotel("هتل 5", 4, covers[4],"");
        hotelList.add(a);

        a = new Hotel("هتل 6", 2, covers[5],"");
        hotelList.add(a);

        a = new Hotel("هتل 7", 2, covers[6],"");
        hotelList.add(a);

        a = new Hotel("هتل 8", 4, covers[7],"");
        hotelList.add(a);

        a = new Hotel("هتل 9", 4, covers[8],"");
        hotelList.add(a);

        a = new Hotel("هتل 10", 2, covers[9],"");
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


        Hotel a = new Hotel("هتل مشد", 5, covers[0],"جای خوبیست بسیار عالی");
        hotelList.add(a);

        a = new Hotel("هتل 2", 2, covers[1],"");
        hotelList.add(a);

        a = new Hotel("هتل 3", 3, covers[2],"");
        hotelList.add(a);

        a = new Hotel("هتل 4", 1, covers[3],"");
        hotelList.add(a);

        a = new Hotel("هتل 5", 4, covers[4],"");
        hotelList.add(a);

        a = new Hotel("هتل 6", 2, covers[5],"");
        hotelList.add(a);

        a = new Hotel("هتل 7", 2, covers[6],"");
        hotelList.add(a);

        a = new Hotel("هتل 8", 4, covers[7],"");
        hotelList.add(a);
/*
        a = new Hotel("هتل 9", 4, covers[8],"");
        hotelList.add(a);

        a = new Hotel("هتل 10", 2, covers[9],"");
        hotelList.add(a);
*/
        adapter.notifyDataSetChanged();
    }


    /**
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

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}
