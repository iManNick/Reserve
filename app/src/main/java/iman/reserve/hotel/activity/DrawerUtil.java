package iman.reserve.hotel.activity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import iman.reserve.hotel.R;

import static android.content.Context.MODE_PRIVATE;


public class DrawerUtil {


        public static Drawer getDrawer(final Activity activity, Toolbar toolbar,final Context context) {
        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem drawerEmptyItem = new PrimaryDrawerItem().withIdentifier(0).withName("");
        drawerEmptyItem.withEnabled(false);

        PrimaryDrawerItem drawerItemCities = new PrimaryDrawerItem().withIdentifier(1)
                .withName("شهرها").withIcon(R.drawable.cities); //icon = "ic_people_outline_black_48px"
        PrimaryDrawerItem drawerItemFavs = new PrimaryDrawerItem()
                .withIdentifier(2).withName("علاقه مندی ها").withIcon(R.drawable.fav); //R.drawable.tournamenticon

        SecondaryDrawerItem drawerItemReserves = new SecondaryDrawerItem().withIdentifier(3)
                .withName("رزروهای من").withIcon(R.drawable.planner); //R.drawable.ic_help_black_24px

        SecondaryDrawerItem drawerItemSettings = new SecondaryDrawerItem().withIdentifier(4)
                .withName("تنظیمات").withIcon(R.drawable.settings8xxl); //R.drawable.ic_settings_black_48px
        SecondaryDrawerItem drawerItemAbout = new SecondaryDrawerItem().withIdentifier(5)
                .withName("درباره ما").withIcon(R.drawable.friendicon); //R.drawable.ic_info_black_24px
        SecondaryDrawerItem drawerItemLogout = new SecondaryDrawerItem().withIdentifier(6)
                .withName("خروج از حساب").withIcon(R.drawable.accountlogoutxxl); //R.drawable.ic_payment_black_24px



/*
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        new ProfileDrawerItem().withName("Mike Penz").withEmail("mikepenz@gmail.com").withIcon(getResources().getDrawable(R.drawable.profile))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

                add this to result
                .withAccountHeader(headerResult)

                */



        //create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
                .withActivity(activity)
                .withToolbar(toolbar)

                .withActionBarDrawerToggle(true)
                .withActionBarDrawerToggleAnimated(true)
                .withCloseOnClick(true)
                .withSelectedItem(-1)
                .addDrawerItems(
                       // drawerEmptyItem,drawerEmptyItem,drawerEmptyItem,
                        drawerItemCities,
                        drawerItemFavs,
                        new DividerDrawerItem(),
                        drawerItemReserves,
                        drawerItemSettings,

                        drawerItemAbout,
                        drawerItemLogout

                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem.getIdentifier() == 1 && !(activity instanceof CityActivity)) {
                            // load tournament screen
                            Intent intent = new Intent(activity, CityActivity.class);
                            view.getContext().startActivity(intent);
                        }
                        else if (drawerItem.getIdentifier() == 2 && !(activity instanceof FavActivity)) {
                            // load tournament screen
                            Intent intent = new Intent(activity, FavActivity.class);
                            view.getContext().startActivity(intent);
                        }

                        else if (drawerItem.getIdentifier() == 3 && !(activity instanceof MyReservesActivity)) {
                            // load tournament screen
                            Intent intent = new Intent(activity, MyReservesActivity.class);
                            view.getContext().startActivity(intent);
                        }
                        else if (drawerItem.getIdentifier() == 5 ) {
                            // load tournament screen
                            Snackbar.make(view,"Developed by Iman NickAein",Snackbar.LENGTH_LONG).show();
                        }


                        else if (drawerItem.getIdentifier() == 6 && !(activity instanceof LoginActivity)) {
                            SharedPreferences prefs = context.getSharedPreferences("UserData", MODE_PRIVATE);
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putString("username", "");
                            editor.putString("password", "");
                            editor.apply();

                            // load tournament screen
                            Intent intent = new Intent(activity, LoginActivity.class);
                            view.getContext().startActivity(intent);
                        }




                        return true;



                    }
                })
                .build();

        return result;
    }
}