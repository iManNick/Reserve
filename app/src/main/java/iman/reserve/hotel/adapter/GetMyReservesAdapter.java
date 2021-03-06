
package iman.reserve.hotel.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import iman.reserve.hotel.R;
import iman.reserve.hotel.activity.ReserveActivity;
import iman.reserve.hotel.model.Comment;
import iman.reserve.hotel.model.MyReserve;

import static android.content.Context.MODE_PRIVATE;


public class GetMyReservesAdapter extends RecyclerView.Adapter<GetMyReservesAdapter.MyReserveViewHolder> {
    private Context hContext;
    private ArrayList<MyReserve> dataList;

    public GetMyReservesAdapter(ArrayList<MyReserve> dataList) {
        this.dataList = dataList;
    }

    @Override
    public MyReserveViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.hContext = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(hContext);
        View view = layoutInflater.inflate(R.layout.row_myreserve, parent, false);
        return new MyReserveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyReserveViewHolder holder, final int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(hContext,ReserveActivity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                //intent.putExtra("HOTELNAME",hotel.getName());
                SharedPreferences prefs = hContext.getSharedPreferences("UserData", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("selectedhotel", dataList.get(position).getName());
                editor.apply();
                hContext.startActivity(intent);
            }
        });
        String id = "شماره رزرو : " + dataList.get(position).getR_id();
        holder.txtID.setText(id);

        String hotel = "هتل : " + dataList.get(position).getName();
        holder.txtHOTEL.setText(hotel);

        String type = "اتاق : " + dataList.get(position).getR_type();
        holder.txtTYPE.setText(type);

        String checkIn = "تاریخ ورود : " + dataList.get(position).getR_chkindt();
        holder.txtCHECKIN.setText(checkIn);

        String checkOut = "تاریخ خروج : " + dataList.get(position).getR_chkoutdt();
        holder.txtCHECKOUT.setText(checkOut);

        int NUMBEROFROOMS = Integer.parseInt(dataList.get(position).getR_rooms());
        String numrooms = "تعداد اتاق : " + NUMBEROFROOMS;
        holder.txtNUMROOMS.setText(numrooms);

        int ROOMNUMBER = Integer.parseInt(dataList.get(position).getRoom_number()) ;
        String roomnumber = "شماره اتاق : " + ROOMNUMBER;
        if( NUMBEROFROOMS > 1){
            int lastroom = ROOMNUMBER + NUMBEROFROOMS;
            roomnumber += " تا " + lastroom;
        }
        holder.txtROOMNUMBER.setText(roomnumber);



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

    class MyReserveViewHolder extends RecyclerView.ViewHolder {

        TextView txtHOTEL, txtTYPE, txtCHECKIN, txtCHECKOUT, txtNUMROOMS, txtROOMNUMBER, txtID;

        MyReserveViewHolder(View itemView) {
            super(itemView);
            txtHOTEL = itemView.findViewById(R.id.txt_my_reserve_hotel);
            txtTYPE = itemView.findViewById(R.id.txt_my_reserve_type);
            txtCHECKIN =  itemView.findViewById(R.id.txt_my_reserve_checkIn_date);
            txtCHECKOUT =  itemView.findViewById(R.id.txt_my_reserve_checkOut_date);
            txtNUMROOMS =  itemView.findViewById(R.id.txt_my_reserve_number_of_rooms);
            txtROOMNUMBER =  itemView.findViewById(R.id.txt_my_reserve_room_numbers);
            //txtROOMNUMBER =  itemView.findViewById(R.id.txt_my_reserve_room_number);
            txtID = itemView.findViewById(R.id.txt_my_reserve_id);
            //itemView.setOnClickListener(this);
        }
/*
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
*/
    }
}