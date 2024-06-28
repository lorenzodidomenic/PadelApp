package com.example.padel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class InsertLessonFragment extends Fragment {

    FragmentManager fragmentManager;
    private Spinner possibleHours, possibleCoaches;

    private String stringDateSelected,field;

    private TextInputEditText textDescriptionView;
    ArrayList<String> items = new ArrayList<String>();
    ArrayList<String> items_coaches = new ArrayList<String>();

    public InsertLessonFragment() {
        // Required empty public constructor
    }


    public static InsertLessonFragment newInstance(String param1, String param2) {
        InsertLessonFragment fragment = new InsertLessonFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getParentFragmentManager();
        Bundle data = this.getArguments();
        if(data != null){

            items = data.getStringArrayList("items");
            items_coaches = data.getStringArrayList("items_coaches");
            stringDateSelected = data.getString("stringDateSelected");
            field = data.getString("field");

        }else{
            System.out.println("data null");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_insert_lesson, container, false);

        possibleHours = (Spinner)view.findViewById(R.id.spinnerHours);
        possibleCoaches = (Spinner)view.findViewById(R.id.spinnerCoaches);
        textDescriptionView = (TextInputEditText) view.findViewById(R.id.description) ;

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        possibleHours.setAdapter(adapter);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, items_coaches);
        possibleCoaches.setAdapter(adapter1);

        /* devo passare coach che spuntano in tutto il database
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, coaches);
        possibleHours.setAdapter(adapter);*/


        Button button = (Button) view.findViewById(R.id.saveEventBtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hours = possibleHours.getSelectedItem().toString();
                String coach = possibleCoaches.getSelectedItem().toString();

                String description = String.valueOf(textDescriptionView.getText());


                DatabaseReference database = FirebaseDatabase.getInstance("https://padel-5d8f6-default-rtdb.europe-west1.firebasedatabase.app").getReference("lessons");

                String prenotazione = stringDateSelected;

                database.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if(database.child("Lezione " + prenotazione + " "+ hours)==null){
                            Toast.makeText(getContext(), "Orario occupato", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Lesson lesson = new Lesson(coach, description, stringDateSelected, hours, field,"");
                            database.child("Lezione " + prenotazione + " " + hours).setValue(lesson);
                            DatabaseReference database = FirebaseDatabase.getInstance("https://padel-5d8f6-default-rtdb.europe-west1.firebasedatabase.app").getReference("reservations/"+field);

                            database.child(stringDateSelected).child(hours).setValue(lesson);
                            Toast.makeText(getContext(), "Inserimento lezione effettuato", Toast.LENGTH_LONG).show();

                        }
                        AdminManage fragment = new AdminManage();

                        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, fragment, null)   //vogliamo indicare di spaostarci da questo fragment ad un altro, cambia quello che è in questo containetr con un nuovo fragment
                                .setReorderingAllowed(true)  //reordering allowed
                                .addToBackStack("name") // così che se faccio indietro ritorna a questo fragment
                                .commit();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


        return view;
    }
}