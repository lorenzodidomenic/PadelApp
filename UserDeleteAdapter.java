package com.example.padel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name,surname,email,telephone;
        public MyViewHolder(@NonNull View ItemView){
            super(ItemView);

            name = ItemView.findViewById(R.id.name_text);
            surname = ItemView.findViewById(R.id.surname_text);
            email = ItemView.findViewById(R.id.email_text);
            telephone = ItemView.findViewById(R.id.telephone_text);
        }
    }
}
