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


public class ListReservations extends Fragment {

    private RecyclerView recyclerView ;   //la view che mi permette di mostrare tutti gli utenti

    ArrayList<Reservation> list = new ArrayList<>();
    ReservationAdapter myAdapter =new ReservationAdapter(list,getContext());


    public ListReservations() {
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

        DatabaseReference database = FirebaseDatabase.getInstance("https://padel-5d8f6-default-rtdb.europe-west1.firebasedatabase.app").getReference("reservations");
        //recyclerView = getView().findViewById(R.id.recyclerView);


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {



                list.clear();
                //prendiamo tutti i dati dentro users
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                            for(DataSnapshot dataSnapshot2: dataSnapshot1.getChildren()) {
                                Reservation reservation = dataSnapshot2.getValue(Reservation.class);
                                if(reservation.getPlayer1() != null)
                                    list.add(reservation);
                            }
                    }
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
        View view =  inflater.inflate(R.layout.fragment_list_reservations, container, false);

        // Add the following lines to create RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(myAdapter);



        return view;
    }
}