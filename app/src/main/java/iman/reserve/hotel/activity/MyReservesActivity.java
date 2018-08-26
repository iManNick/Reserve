package iman.reserve.hotel.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import iman.reserve.hotel.R;
import iman.reserve.hotel.adapter.GetMyReservesAdapter;
import iman.reserve.hotel.model.MyReserve;
import iman.reserve.hotel.model.MyReserveList;
import iman.reserve.hotel.network.GetMyReservesDataService;
import iman.reserve.hotel.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyReservesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_reserves);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar4);
        setTitle("رزروهای من");
        setSupportActionBar(toolbar);

        DrawerUtil.getDrawer(this,toolbar);

        SharedPreferences prefs = getApplicationContext().getSharedPreferences("UserData", MODE_PRIVATE);
        String username = prefs.getString("username", "");

        GetMyReservesDataService service = RetrofitInstance.getRetrofitInstance().create(GetMyReservesDataService.class);

        /*Call the method with parameter in the interface to get the employee data*/
        Call<MyReserveList> call = service.getMyReservesData(username);

        /*Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<MyReserveList>() {
            @Override
            public void onResponse(Call<MyReserveList> call, Response<MyReserveList> response) {
                generateMyReservesList(response.body().getMyreserveArrayList());
            }

            @Override
            public void onFailure(Call<MyReserveList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void generateMyReservesList(ArrayList<MyReserve> mrsrvDataList) {


        RecyclerView recyclerView = findViewById(R.id.recycler_view_my_reserves);

        GetMyReservesAdapter adapter = new GetMyReservesAdapter(mrsrvDataList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);

        recyclerView.setAdapter(adapter);
    }

}
