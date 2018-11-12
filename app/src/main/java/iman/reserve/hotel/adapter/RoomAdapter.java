
package iman.reserve.hotel.adapter;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.view.TintableBackgroundView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import iman.reserve.hotel.R;
import iman.reserve.hotel.model.Room;

import static android.content.Context.MODE_PRIVATE;


public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {
    private Context hContext;
    private ArrayList<Room> dataList;
    private int selectedPos = RecyclerView.NO_POSITION;
    private Drawable bg;

    public RoomAdapter(ArrayList<Room> dataList) {
        this.dataList = dataList;
    }

    @Override
    public RoomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.hContext = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(hContext);
        View view = layoutInflater.inflate(R.layout.row_room, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RoomViewHolder holder, final int position) {


        final String type = dataList.get(position).getType();
        holder.txtRoomType.setText(type);
        String food = "شامل : " + dataList.get(position).getFood();
        holder.txtRoomFood.setText(food);
        String price = "قیمت : " + dataList.get(position).getPrice() + " ریال";
        holder.txtRoomPrice.setText(price);
        String totroom = "تعداد کل اتاق : " + dataList.get(position).getTotroom();
        holder.txtRoomAvroom.setText(totroom);
        String size = "اندازه اتاق : " + dataList.get(position).getSize();
        holder.txtRoomSize.setText(size);


        SharedPreferences prefs = hContext.getSharedPreferences("UserData", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        if(selectedPos == position){
            holder.itemView.setBackgroundColor(Color.MAGENTA);
            editor.putString("selroom", type);
            editor.apply();
        }else {
            holder.itemView.setBackground(bg);

            //holder.itemView.setDrawingCacheBackgroundColor(Color.RED);
        }
        //holder.itemView.setBackground(null);

        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.itemView.setBackgroundColor(Color.CYAN);
                SharedPreferences prefs = hContext.getSharedPreferences("UserData", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("selroom", type);
                editor.apply();

                // Updating old as well as new positions
                notifyItemChanged(selectedPos);


            }
        });*/


    }



    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class RoomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtRoomType, txtRoomFood, txtRoomPrice, txtRoomAvroom, txtRoomSize;

        RoomViewHolder(View itemView) {
            super(itemView);
            bg = itemView.getBackground();
            txtRoomType = (TextView) itemView.findViewById(R.id.txt_room_type);
            txtRoomFood = (TextView) itemView.findViewById(R.id.txt_room_food);
            txtRoomPrice = (TextView) itemView.findViewById(R.id.txt_room_price);
            txtRoomAvroom = (TextView) itemView.findViewById(R.id.txt_room_avroom);
            txtRoomSize = (TextView) itemView.findViewById(R.id.txt_room_size);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // Below line is just like a safety check, because sometimes holder could be null,
            // in that case, getAdapterPosition() will return RecyclerView.NO_POSITION
            if (getAdapterPosition() == RecyclerView.NO_POSITION) return;

            // Updating old as well as new positions
            notifyItemChanged(selectedPos);
            selectedPos = getAdapterPosition();
            notifyItemChanged(selectedPos);

            // Do your another stuff for your onClick
        }

    }
}