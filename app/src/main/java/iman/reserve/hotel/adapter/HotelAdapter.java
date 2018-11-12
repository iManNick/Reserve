package iman.reserve.hotel.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import iman.reserve.hotel.R;
import iman.reserve.hotel.activity.FavActivity;
import iman.reserve.hotel.model.Hotel;
import iman.reserve.hotel.activity.ReserveActivity;
import iman.reserve.hotel.model.Message;
import iman.reserve.hotel.network.GetFavDataService;
import iman.reserve.hotel.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.MyViewHolder> {

    private Context hContext;
    private ArrayList<Hotel> hotelList;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private Boolean isfav;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, desc;
        public ImageView thumbnail, overflow;
        public RatingBar rate;
        public Button rezBut;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title_hotel);
            rate = (RatingBar) view.findViewById(R.id.rating_hotel);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail_hotel);
            overflow = (ImageView) view.findViewById(R.id.overflow_hotel);
            desc = (TextView) view.findViewById(R.id.desc_hotel);
            rezBut = (Button) view.findViewById(R.id.rezBut);
        }
    }


    public HotelAdapter(Context hContext, ArrayList<Hotel> hotelList,Boolean isfav) {
        this.hContext = hContext;
        this.hotelList = hotelList;
        this.isfav = isfav;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hotel_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        prefs = hContext.getSharedPreferences("UserData", MODE_PRIVATE);
        editor = prefs.edit();

        final Hotel hotel = hotelList.get(position);
        holder.title.setText(hotel.getName());

        holder.rate.setNumStars(hotel.getNumOfHotels());
        holder.rate.setRating(hotel.getNumOfHotels());

        holder.desc.setText(hotel.getDesc());
        // loading hotel cover using Glide library
        Glide.with(hContext).load(hotel.getThumbnail()).into(holder.thumbnail);

        holder.rezBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(hContext,ReserveActivity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                //intent.putExtra("HOTELNAME",hotel.getName());

                editor.putString("selectedhotel", hotel.getName());
                //editor.putString("h_id", Integer.toString(hotel.getHotelID()));
                editor.apply();
                hContext.startActivity(intent);
            }
        });

        if(isfav) {
            holder.overflow.setVisibility(View.GONE);
        }
        else{
            holder.overflow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.overflow.setImageResource(R.drawable.fav);
                    addtofav(hotel);
                    //showPopupMenu(holder.overflow);
                }
            });
        }

    }
    private void addtofav(Hotel hotel){

        SharedPreferences prefs = hContext.getSharedPreferences("UserData", MODE_PRIVATE);
        String username = prefs.getString("username","");

        GetFavDataService service = RetrofitInstance.getRetrofitInstance().create(GetFavDataService.class);

        /*Call the method with parameter in the interface to get the employee data*/
        Call<Message> call = service.setFavData(hotel.getName(),username);
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                String message = response.body().getMessage();
                Toast.makeText(hContext, message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                Toast.makeText(hContext, R.string.no_internet, Toast.LENGTH_SHORT).show();
            }
        });

    }


    /**
     * Showing popup menu when tapping on 3 dots
     *
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(hContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    /**
     * Click listener for popup menu items
     *
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(hContext, "به علاقه مندی های شما اضافه شد", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(hContext, "تست", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }
    */

    @Override
    public int getItemCount() {
        return hotelList.size();
    }
}
