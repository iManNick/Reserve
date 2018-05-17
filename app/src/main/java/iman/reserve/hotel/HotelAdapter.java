package iman.reserve.hotel;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import iman.reserve.hotel.R;


public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.MyViewHolder> {

    private Context hContext;
    private List<Hotel> hotelList;

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


    public HotelAdapter(Context hContext, List<Hotel> hotelList) {
        this.hContext = hContext;
        this.hotelList = hotelList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hotel_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
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
                intent.putExtra("HOTELNAME",hotel.getName());
                hContext.startActivity(intent);
            }
        });

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);
            }
        });
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
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
     */
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

    @Override
    public int getItemCount() {
        return hotelList.size();
    }
}
