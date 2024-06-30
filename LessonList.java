package com.example.padel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;


public class LessonList extends Fragment {

    private RecyclerView recyclerView ;   //la view che mi permette di mostrare tutti gli utenti

    ArrayList<Lesson> list = new ArrayList<>();
    LessonAdapter myAdapter =new LessonAdapter(list,getContext());


    public LessonList() {
        // Required empty public constructor
    }


    public static LessonList newInstance(String param1, String param2) {
        LessonList fragment = new LessonList();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DatabaseReference database = FirebaseDatabase.getInstance("https://padel-5d8f6-default-rtdb.europe-west1.firebasedatabase.app").getReference("lessons");
        //recyclerView = getView().findViewById(R.id.recyclerView);


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {



                list.clear();
                //prendiamo tutti i dati dentro users
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Lesson lesson = dataSnapshot.getValue(Lesson.class);

                    String date_lesson = lesson.getDay();
                    String[] date = date_lesson.split(":");
                    String hour_lesson = lesson.getHour();
                    String[] hour = hour_lesson.split(" ");


                    Date date_les = new Date(Integer.parseInt(date[2])-1900,Integer.parseInt(date[1])-1,Integer.parseInt(date[0]),Integer.parseInt(hour[0]),0);

                    System.out.println(date_les);
                    if(date_les.after(new Date()))
                       list.add(lesson);

                }
                myAdapter.notifyDataSetChanged();
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_lesson_list, container, false);

        // Add the following lines to create RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(myAdapter);



        return view;
    }
}