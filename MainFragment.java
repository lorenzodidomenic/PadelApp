package com.example.padel;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.lang.reflect.Field;

public class MainFragment extends Fragment {


    FragmentManager fragmentManager;


    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         fragmentManager = getParentFragmentManager();
    }

    //mi prendo il bottone e gli setto l'onClick e mi manderà ad un fragment per la prenotazione
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ImageButton buttonField1 = (ImageButton) view.findViewById(R.id.field_one);
        buttonField1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Field1Fragment fragment = new Field1Fragment();
                Bundle data = new Bundle();
                String field1 = "field1";
                data.putString("field",field1);

                fragment.setArguments(data);

                fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, fragment, null)   //vogliamo indicare di spaostarci da questo fragment ad un altro, cambia quello che è in questo containetr con un nuovo fragment
                        .setReorderingAllowed(true)  //reordering allowed
                        .addToBackStack("name") // così che se faccio indietro ritorna a questo fragment
                        .commit();
            }

        });

        ImageButton buttonField2 = (ImageButton) view.findViewById(R.id.field_two);
        buttonField2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Field1Fragment fragment = new Field1Fragment();
                Bundle data = new Bundle();
                data.putString("field","field2");



                fragment.setArguments(data);
                fragmentManager.beginTransaction()   //indico di iniziare una transazione da un fragment ad un altro
                        .replace(R.id.fragmentContainerView, fragment, null)   //vogliamo indicare di spaostarci da questo fragment ad un altro, cambia quello che è in questo containetr con un nuovo fragment
                        .setReorderingAllowed(true)  //reordering allowed
                        .addToBackStack("name") // così che se faccio indietro ritorna a questo fragment
                        .commit();
            }

        });


        ImageButton buttonField3 = (ImageButton) view.findViewById(R.id.field_three);
        buttonField3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Field1Fragment fragment = new Field1Fragment();
                Bundle data = new Bundle();
                data.putString("field","field3");
                System.out.println(data);



                fragment.setArguments(data);
                fragmentManager.beginTransaction()   //indico di iniziare una transazione da un fragment ad un altro
                        .replace(R.id.fragmentContainerView, fragment, null)   //vogliamo indicare di spaostarci da questo fragment ad un altro, cambia quello che è in questo containetr con un nuovo fragment
                        .setReorderingAllowed(true)  //reordering allowed
                        .addToBackStack("name") // così che se faccio indietro ritorna a questo fragment
                        .commit();
            }

        });


        ImageButton buttonField4 = (ImageButton) view.findViewById(R.id.field_four);
        buttonField4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Field1Fragment fragment = new Field1Fragment();
                Bundle data = new Bundle();
                data.putString("field","field4");



                fragment.setArguments(data);
                fragmentManager.beginTransaction()   //indico di iniziare una transazione da un fragment ad un altro
                        .replace(R.id.fragmentContainerView, fragment, null)   //vogliamo indicare di spaostarci da questo fragment ad un altro, cambia quello che è in questo containetr con un nuovo fragment
                        .setReorderingAllowed(true)  //reordering allowed
                        .addToBackStack("name") // così che se faccio indietro ritorna a questo fragment
                        .commit();


            }

        });

        return view;


    }
}