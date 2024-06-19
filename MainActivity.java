package com.example.padel;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    TextView textView;

    FirebaseUser user;

    String name;
    String surname;
    String email;
    String telephone;

    ImageButton buttonMain,buttonList,buttonCoach, buttonUser ;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();  //prendo istanza del mio database firebase
        DatabaseReference database = FirebaseDatabase.getInstance("https://padel-5d8f6-default-rtdb.europe-west1.firebasedatabase.app").getReference("users");

        //abbiamo bisogno adesso di un fragment manager che ci porterà la gestione tra vari fragment
        //cambiare da un fragment all'altro non è semplice come passare da un activity all'altra ma abbiamo bisogno di un fragment Manager
        FragmentManager fragmentManager = getSupportFragmentManager();

        user = auth.getCurrentUser();  /* così prendo l'id corrente*/
        String uid = null;
        if(user == null){
            //se l'utente è null richiamiamo la login acitivty e chiudiamo questas
            Intent intent = new Intent(getApplicationContext(),Login.class);
            startActivity(intent);
            finish();
        }else{
            uid = user.getUid().toString();
        }

        if(!uid.isEmpty()) {
            // Attach a listener to read the data at our posts reference
            database.child(uid).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    User user_r = dataSnapshot.getValue(User.class);
                    name = user_r.getName();
                    surname = user_r.getSurname();
                    telephone = user_r.getTelephone();
                    email = user_r.getEmail();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });
        }

        buttonMain = (ImageButton)findViewById(R.id.buttonFields);  //prendo il bottone della tool bar che mi porta al fragment con i campi

        buttonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction()   //indico di iniziare una transazione da un fragment ad un altro
                        .replace(R.id.fragmentContainerView, MainFragment.class, null)   //vogliamo indicare di spaostarci da questo fragment ad un altro, cambia quello che è in questo containetr con un nuovo fragment
                        .setReorderingAllowed(true)  //reordering allowed
                        .addToBackStack("name") // così che se faccio indietro ritorna a questo fragment
                        .commit();
            }
        });


        buttonList = (ImageButton)findViewById(R.id.buttonList);  //prendo il bottone della tool bar che mi porta al fragment con i campi

        buttonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction()   //indico di iniziare una transazione da un fragment ad un altro
                        .replace(R.id.fragmentContainerView, ListFragment.class, null)   //vogliamo indicare di spaostarci da questo fragment ad un altro, cambia quello che è in questo containetr con un nuovo fragment
                        .setReorderingAllowed(true)  //reordering allowed
                        .addToBackStack("name") // così che se faccio indietro ritorna a questo fragment
                        .commit();
            }
        });

        buttonCoach = (ImageButton)findViewById(R.id.buttonCoach);  //prendo il bottone della tool bar che mi porta al fragment con i campi

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

        buttonUser = (ImageButton)findViewById(R.id.buttonUser);  //prendo il bottone della tool bar che mi porta al fragment con i campi

        buttonUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle data = new Bundle();
                data.putString("name_user",name);
                data.putString("surname_user",surname);
                data.putString("telephone_user",telephone);
                data.putString("email_user",email);


                UserFragment fragment = new UserFragment();
                fragment.setArguments(data);

                fragmentManager.beginTransaction()   //indico di iniziare una transazione da un fragment ad un altro
                        .replace(R.id.fragmentContainerView, fragment, null)   //vogliamo indicare di spaostarci da questo fragment ad un altro, cambia quello che è in questo containetr con un nuovo fragment
                        .setReorderingAllowed(true)  //reordering allowed
                        .addToBackStack("name") // così che se faccio indietro ritorna a questo fragment
                        .commit();
            }
        });


    }
}