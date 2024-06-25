package com.example.padel;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CoachFragment extends Fragment {


    TextInputEditText editTextName,editTextSpeciality;
    Button buttonSave;
    FragmentManager fragmentManager;
    DatabaseReference database;

    public CoachFragment() {
        // Required empty public constructor
    }


    public static CoachFragment newInstance(String param1, String param2) {
        CoachFragment fragment = new CoachFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getParentFragmentManager();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_coach, container, false);

        editTextName = (TextInputEditText)view.findViewById(R.id.name);
        editTextSpeciality = (TextInputEditText)view.findViewById(R.id.speciality);
        buttonSave = (Button) view.findViewById(R.id.btn_save);

        DatabaseReference database = FirebaseDatabase.getInstance("https://padel-5d8f6-default-rtdb.europe-west1.firebasedatabase.app").getReference("coaches");


        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = editTextName.getText().toString();
                String speciality = editTextSpeciality.getText().toString();

                if ((name.isEmpty() || speciality.isEmpty())){
                    Toast.makeText(getContext(), "Dati Inseriti non corretti",
                            Toast.LENGTH_SHORT).show();
                }
                Coach new_coach = new Coach(name,speciality);

                database.child(name).setValue(new_coach);

                Toast.makeText(getContext(), "Salvataggio Maestro effettuato",
                        Toast.LENGTH_SHORT).show();


                CoachFragment fragment = new CoachFragment();

                fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, fragment, null)   //vogliamo indicare di spaostarci da questo fragment ad un altro, cambia quello che è in questo containetr con un nuovo fragment
                        .setReorderingAllowed(true)  //reordering allowed
                        .addToBackStack("name") // così che se faccio indietro ritorna a questo fragment
                        .commit();
            }
        });

        return view;
    }
}