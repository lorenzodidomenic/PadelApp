package com.example.padel;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class UsersManage extends Fragment {

    EditText nametextView,surnameTextView,emailTextView,skillTextView,softskillTextView,telephoneTextView;

    String name_user,surname_user,email_user;


    FragmentManager fragmentManager;
    FirebaseUser user;
    FirebaseAuth auth;
    public UsersManage() {
        // Required empty public constructor
    }


    public static UsersManage newInstance(String param1, String param2) {
        UsersManage fragment = new UsersManage();


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        fragmentManager = getParentFragmentManager();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_users_manage, container, false);

        nametextView = view.findViewById(R.id.name);
        surnameTextView = view.findViewById(R.id.surname);
        emailTextView = view.findViewById(R.id.email);

        Button button = view.findViewById(R.id.btn);
        Button button1 = view.findViewById(R.id.btn_modify);


        Bundle data = getArguments();
        if(data != null){
            name_user = data.getString("name");
            surname_user = data.getString("surname");
            email_user = data.getString("email");

            nametextView.setText(name_user);
            surnameTextView.setText(surname_user);
            emailTextView.setText(email_user);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                user = auth.getCurrentUser();  /* così prendo l'id corrente*/
                String uid = user.getUid().toString();

                DatabaseReference database = FirebaseDatabase.getInstance("https://padel-5d8f6-default-rtdb.europe-west1.firebasedatabase.app").getReference("users");

                //mi cerco il profilo dell'utente con quel nome
                database.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                            //prendiamo tutti i dati dentro users
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                User user = dataSnapshot.getValue(User.class);
                                String player = name_user + " " + surname_user;

                                if(player.compareTo(user.name+" "+user.surname)==0) {
                                    database.child(dataSnapshot.getKey()).removeValue();
                                }

                                }
                            }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });

                Toast.makeText(getContext(), "Rimozione effettuata", Toast.LENGTH_LONG).show();

                AdminMainFragment fragment = new AdminMainFragment();

                fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, fragment, null)   //vogliamo indicare di spaostarci da questo fragment ad un altro, cambia quello che è in questo containetr con un nuovo fragment
                        .setReorderingAllowed(true)  //reordering allowed
                        .addToBackStack("name") // così che se faccio indietro ritorna a questo fragment
                        .commit();

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email,password,name,surname,telephone;

                name = String.valueOf(nametextView.getText());
                surname = String.valueOf(surnameTextView.getText());
                //telephone = String.valueOf(telephoneTextView.getText());
                email = String.valueOf(emailTextView.getText());


                DatabaseReference database = FirebaseDatabase.getInstance("https://padel-5d8f6-default-rtdb.europe-west1.firebasedatabase.app").getReference("users");

                //mi cerco il profilo dell'utente con quel nome
                database.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                        //prendiamo tutti i dati dentro users
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            User user = dataSnapshot.getValue(User.class);
                            String player = name_user + " " + surname_user;

                            User new_user = new User(name,surname,user.getTelephone(),email,user.getSkillValue(), user.softSkillValue, user.getNumGames());

                            if(player.compareTo(user.name+" "+user.surname)==0) {
                                database.child(dataSnapshot.getKey()).setValue(new_user);
                            }

                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                Toast.makeText(getContext(), "Modifica effettuata", Toast.LENGTH_LONG).show();

                AdminMainFragment fragment = new AdminMainFragment();

                fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, fragment, null)   //vogliamo indicare di spaostarci da questo fragment ad un altro, cambia quello che è in questo containetr con un nuovo fragment
                        .setReorderingAllowed(true)  //reordering allowed
                        .addToBackStack("name") // così che se faccio indietro ritorna a questo fragment
                        .commit();
            }
        });

        return view;
    }
}