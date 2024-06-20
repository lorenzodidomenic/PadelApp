package com.example.padel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Field1Fragment extends Fragment {

    DatabaseReference database;

    private String stringDateSelected;

    public Field1Fragment() {
        // Required empty public constructor
    }


    public static Field1Fragment newInstance(String param1, String param2) {
        Field1Fragment fragment = new Field1Fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance("https://padel-5d8f6-default-rtdb.europe-west1.firebasedatabase.app").getReference("events");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_field1, container, false);
        CalendarView calendarView = (CalendarView) view.findViewById(R.id.calendarView);
        Button button = (Button)view.findViewById(R.id.saveEventBtn);

        //get the spinner from the xml.
        Spinner possibleHours = (Spinner)view.findViewById(R.id.spinnerHours);
        String[] items = new String[]{"09 10","10 11","11 12","12 13","13 14","14 15","15 16","16 17","17 18","18 19","19 20","20 21","21 22","22 23","23 24"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        possibleHours.setAdapter(adapter);

        Spinner dropdown = (Spinner)view.findViewById(R.id.spinnerType);
        String[] items2 = new String[]{"Prenotazione","Semi-Prenotazione"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, items2);
        dropdown.setAdapter(adapter2);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                System.out.println(year);
                stringDateSelected =  Integer.toString(dayOfMonth)+":"+Integer.toString(month+1) +":"+ Integer.toString(year) ;
                System.out.println(stringDateSelected);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!stringDateSelected.isEmpty())
                   database.child(stringDateSelected).setValue("Nuova Prenotazione");


            }
        });

        return view;
    }

}