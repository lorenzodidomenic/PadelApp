package com.example.padel;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.MyViewHolder> {

    Context context;
    ArrayList<Lesson> listItems ;

    FirebaseAuth auth;


    public LessonAdapter(ArrayList<Lesson> listItems,Context context) {
        this.listItems = listItems;
        this.context = context;
        this.auth =  FirebaseAuth.getInstance();
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lesson,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Lesson lesson = listItems.get(position);
        holder.coach.setText(lesson.getCoach());
        holder.description.setText(lesson.getDescription());
        holder.day.setText(lesson.getDay());
        holder.hours.setText(lesson.getHour());
        holder.field.setText(lesson.getField());
        holder.lesson = lesson;


        //al click sulla card faccio partire il fragment passandgli i dati
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();

                Bundle data = new Bundle();
                data.putString("day", String.valueOf(holder.day.getText()));
                data.putString("hour", String.valueOf(holder.hours.getText()));
                data.putString("field", String.valueOf(holder.field.getText()));
                data.putString("coach", String.valueOf(holder.coach.getText()));
                data.putString("description", String.valueOf(holder.description.getText()));
                data.putString("prenotati", String.valueOf(holder.lesson.prenotati));

                LessonInfo fragment = new LessonInfo();
                fragment.setArguments(data);


                activity.getSupportFragmentManager().beginTransaction().replace(R.id.listrec,fragment,null).setReorderingAllowed(true).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView coach,day,hours,description,field;
        FragmentManager fragmentManager;
        Lesson lesson;
        Button button;
        CardView card_layout;
        public MyViewHolder(@NonNull View ItemView){
            super(ItemView);

            coach = ItemView.findViewById(R.id.coach);
            day = ItemView.findViewById(R.id.day);
            hours = ItemView.findViewById(R.id.hour);
            description = ItemView.findViewById(R.id.description);
            button = ItemView.findViewById(R.id.btn);
            field = ItemView.findViewById(R.id.field);

        }
    }
}
