package com.example.padel;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserDeleteAdapter extends RecyclerView.Adapter<UserDeleteAdapter.MyViewHolder> {

    Context context;
    ArrayList<User> listItems ;

    public UserDeleteAdapter(ArrayList<User> listItems,Context context) {
        this.listItems = listItems;
        this.context = context;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.userdelete,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        User user = listItems.get(position);

        holder.name.setText(user.getName());
        holder.surname.setText(user.getSurname());
        holder.user = user;

        //al click sulla card faccio partire il fragment passandgli i dati
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();

                Bundle data = new Bundle();
                data.putString("name", String.valueOf(holder.name.getText()));
                data.putString("surname", String.valueOf(holder.surname.getText()));
                data.putString("email", String.valueOf(holder.user.getEmail()));

                UsersManage fragment = new UsersManage();
                fragment.setArguments(data);


                activity.getSupportFragmentManager().beginTransaction().replace(R.id.list,fragment,null).setReorderingAllowed(true).addToBackStack(null).commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name,surname,email,telephone,rank,soft_rank;
        User user;
        public MyViewHolder(@NonNull View ItemView){
            super(ItemView);

            name = ItemView.findViewById(R.id.name_text);
            surname = ItemView.findViewById(R.id.surname_text);

        }
    }
}
