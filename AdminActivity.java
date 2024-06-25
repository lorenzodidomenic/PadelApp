package com.example.padel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminActivity extends AppCompatActivity {

    ImageButton buttonMain, buttonList, buttonCoach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin);

        DatabaseReference database = FirebaseDatabase.getInstance("https://padel-5d8f6-default-rtdb.europe-west1.firebasedatabase.app").getReference("users");

        //abbiamo bisogno adesso di un fragment manager che ci porterà la gestione tra vari fragment
        //cambiare da un fragment all'altro non è semplice come passare da un activity all'altra ma abbiamo bisogno di un fragment Manager
        FragmentManager fragmentManager = getSupportFragmentManager();


        buttonMain = (ImageButton) findViewById(R.id.buttonFields);  //prendo il bottone della tool bar che mi porta al fragment con i campi

        buttonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction()   //indico di iniziare una transazione da un fragment ad un altro
                        .replace(R.id.fragmentContainerView, AdminMainFragment.class, null)   //vogliamo indicare di spaostarci da questo fragment ad un altro, cambia quello che è in questo containetr con un nuovo fragment
                        .setReorderingAllowed(true)  //reordering allowed
                        .addToBackStack("name") // così che se faccio indietro ritorna a questo fragment
                        .commit();
            }
        });

        buttonList = (ImageButton) findViewById(R.id.buttonList);  //prendo il bottone della tool bar che mi porta al fragment con i campi

        buttonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction()   //indico di iniziare una transazione da un fragment ad un altro
                        .replace(R.id.fragmentContainerView, FragmentAdminListUsers.class, null)   //vogliamo indicare di spaostarci da questo fragment ad un altro, cambia quello che è in questo containetr con un nuovo fragment
                        .setReorderingAllowed(true)  //reordering allowed
                        .addToBackStack("name") // così che se faccio indietro ritorna a questo fragment
                        .commit();
            }
        });


        buttonCoach = (ImageButton) findViewById(R.id.buttonCoach);  //prendo il bottone della tool bar che mi porta al fragment con i campi

        buttonCoach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction()   //indico di iniziare una transazione da un fragment ad un altro
                        .replace(R.id.fragmentContainerView, CoachFragment.class, null)   //vogliamo indicare di spaostarci da questo fragment ad un altro, cambia quello che è in questo containetr con un nuovo fragment
                        .setReorderingAllowed(true)  //reordering allowed
                        .addToBackStack("name") // così che se faccio indietro ritorna a questo fragment
                        .commit();
            }
        });

    }


}

