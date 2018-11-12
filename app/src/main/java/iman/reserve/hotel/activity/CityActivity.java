package iman.reserve.hotel.activity;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.mikepenz.materialdrawer.Drawer;

import java.util.ArrayList;
import java.util.List;

import iman.reserve.hotel.R;
import iman.reserve.hotel.adapter.CityAdapter;
import iman.reserve.hotel.model.City;

public class CityActivity extends  AppCompatActivity{

    private String un;
    private RecyclerView recyclerView;
    private CityAdapter adapter;
    private List<City> cityList;
    private Button signout;
    private long backPressedTime = 0;    // used by onBackPressed()
    private Drawer drawer;
/*    public static Intent createIntent(Context context, IdpResponse idpResponse) {
        return new Intent().setClass(context, CityActivity.class)
                .putExtra(ExtraConstants.IDP_RESPONSE, idpResponse);
    }
*/

    @Override
    public void onBackPressed() {        // to prevent irritating accidental logouts
        long t = System.currentTimeMillis();
        if(drawer.isDrawerOpen())
            drawer.closeDrawer();

        else if (t - backPressedTime > 2000) {    // 2 secs
            backPressedTime = t;
            Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content),"برای خروج مجددا دکمه بازگشت را لمس کنید.",Snackbar.LENGTH_LONG).show();

            //Toast.makeText(this, "برای خروج مجددا دکمه بازگشت را لمس کنید.", Toast.LENGTH_SHORT).show();
        } else {    // this guy is serious
            // clean up
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
            super.finish();
            // bye
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_city);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = DrawerUtil.getDrawer(this,toolbar,getApplicationContext());
        drawer.setSelection(1);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);


        /*currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) {
            startActivity(LoginActivity.createIntent(this));
            finish();
            return;
        }




        signout =  findViewById(R.id.signout);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthUI.getInstance()
                        .signOut(CityActivity.this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    startActivity(LoginActivity.createIntent(CityActivity.this));
                                    finish();
                                } else {
                                    Toast.makeText(CityActivity.this, "Sign out failed",
                                            Toast.LENGTH_SHORT).show();
                                    //Log.w(TAG, "signOut:failure", task.getException());

                                    //showSnackbar(R.string.sign_out_failed);
                                }
                            }
                        });


            }
        });
        */
        initCollapsingToolbar();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //RecyclerView.ViewHolder
        /*recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView , new RecyclerItemClickListener.ClickListener() {
           @Override
           public void onItemClick(View view, int position) {
               Snackbar.make( view ,Integer.toString(position), Snackbar.LENGTH_SHORT);
           }
           @Override
           public void onLongItemClick(View view, int position) {
               Snackbar.make( view ,"LONG",Snackbar.LENGTH_SHORT);
               //On Long press event here
           }
       }));
*/

        cityList = new ArrayList<>();
        adapter = new CityAdapter(this, cityList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAlbums();

        try {
            Glide.with(this).load(R.drawable.cover).into((ImageView) findViewById(R.id.backdrop));
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
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
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
                    collapsingToolbar.setTitle("  " + getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }
    /**
     * Adding few albums for testing
     */
    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.album1,
                R.drawable.album2,
                R.drawable.album3,
                R.drawable.album4,
                R.drawable.album5,
                R.drawable.album6,
                R.drawable.album7,
                R.drawable.album8,
                R.drawable.album9,
                R.drawable.album10,
                R.drawable.album11};

        City a = new City("تهران", 5, covers[0]);
        cityList.add(a);

        a = new City("مشهد", 2, covers[1]);
        cityList.add(a);

        a = new City("اصفهان", 1, covers[2]);
        cityList.add(a);

        a = new City("شیراز", 0, covers[3]);
        cityList.add(a);

        a = new City("یزد", 0, covers[4]);
        cityList.add(a);

        a = new City("تبریز", 0, covers[5]);
        cityList.add(a);

        a = new City("کیش", 0, covers[6]);
        cityList.add(a);

        a = new City("رشت", 0, covers[7]);
        cityList.add(a);

        a = new City("بابل", 0, covers[8]);
        cityList.add(a);

        a = new City("ساری", 0, covers[9]);
        cityList.add(a);

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
                outRect.right = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.left = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.right = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.left = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
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
