package com.example.padel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.MyViewHolder> {

    Context context;
    ArrayList<Reservation> listItems ;

    public ReservationAdapter(ArrayList<Reservation> listItems,Context context) {
        this.listItems = listItems;
        this.context = context;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.reservation,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Reservation reservation = listItems.get(position);
        holder.day.setText(reservation.getDay());
        holder.hour.setText(reservation.getHour());
        holder.field.setText(reservation.getField());
        holder.player1.setText(reservation.getPlayer1());
        holder.player2.setText(reservation.getPlayer2());
        holder.player3.setText(reservation.getPlayer3());
        holder.player4.setText(reservation.getPlayer4());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView day,hour,field,player1,player2,player3,player4;
        public MyViewHolder(@NonNull View ItemView){
            super(ItemView);

            day = ItemView.findViewById(R.id.day);
            hour = ItemView.findViewById(R.id.hour);
            field = ItemView.findViewById(R.id.field);
            player1 = ItemView.findViewById(R.id.player1);
            player2 = ItemView.findViewById(R.id.player2);
            player3 = ItemView.findViewById(R.id.player3);
            player4 = ItemView.findViewById(R.id.player4);

        }
    }
}
