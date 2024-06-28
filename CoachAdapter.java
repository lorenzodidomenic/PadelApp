package com.example.padel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CoachAdapter extends RecyclerView.Adapter<CoachAdapter.MyViewHolder> {

    Context context;
    ArrayList<Coach> listItems ;

    public CoachAdapter(ArrayList<Coach> listItems,Context context) {
        this.listItems = listItems;
        this.context = context;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.coach,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Coach coach = listItems.get(position);
        holder.name.setText(coach.getName());
        holder.description.setText(coach.getSpeciality());
        holder.coach = coach;
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name,description;
        Coach coach;
        public MyViewHolder(@NonNull View ItemView){
            super(ItemView);

            name = ItemView.findViewById(R.id.name);
            description = ItemView.findViewById(R.id.description);

        }
    }
}
