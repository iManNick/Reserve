
package iman.reserve.hotel.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import iman.reserve.hotel.R;
import iman.reserve.hotel.model.Comment;

import static android.content.Context.MODE_PRIVATE;


public class GetCommentAdapter extends RecyclerView.Adapter<GetCommentAdapter.CommentViewHolder> {
    private Context hContext;
    private ArrayList<Comment> dataList;

    public GetCommentAdapter(ArrayList<Comment> dataList) {
        this.dataList = dataList;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.hContext = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(hContext);
        View view = layoutInflater.inflate(R.layout.row_comment, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CommentViewHolder holder, final int position) {




        String username = "کاربر : " + dataList.get(position).getUN();
        holder.txtUSERNAME.setText(username);

        String comment = "نظر : " + dataList.get(position).getComment();
        holder.txtCOMMENT.setText(comment);

        String rating = "امتیاز : " + dataList.get(position).getRating() + "از 5";
        holder.txtRATING.setText(rating);


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

    class CommentViewHolder extends RecyclerView.ViewHolder {

        TextView txtUSERNAME, txtCOMMENT, txtRATING;

        CommentViewHolder(View itemView) {
            super(itemView);
            txtUSERNAME = itemView.findViewById(R.id.txt_users_username);
            txtCOMMENT = itemView.findViewById(R.id.txt_users_comment);
            txtRATING =  itemView.findViewById(R.id.txt_users_rating);
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