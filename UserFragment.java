package com.example.padel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class UserFragment extends Fragment {

  FirebaseAuth auth;   //istanza del mio database di autenticazione
  TextView nametextView,surnameTextView,emailTextView,telephoneTextView,skillTextView,softSkillTextView;


  String name_user,surname_user,email_user,telephone_user;

  Float skill,softskill;

    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();  //prendo istanza del mio database firebase


        FirebaseUser user = auth.getCurrentUser();  /* così prendo l'id corrente*/
        String uid = user.getUid().toString();

        DatabaseReference database = FirebaseDatabase.getInstance("https://padel-5d8f6-default-rtdb.europe-west1.firebasedatabase.app").getReference("users");

        if (!uid.isEmpty()) {
            // Attach a listener to read the data at our posts reference
            Query query = database.child(uid);

            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    User user_r = snapshot.getValue(User.class);
                    UserFragment.this.LaunchUser(user_r);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
        public void LaunchUser(User user_r){
            nametextView.setText(user_r.getName());
            surnameTextView.setText(user_r.getSurname());
            emailTextView.setText(user_r.getEmail());
            telephoneTextView.setText(user_r.getTelephone());

        }


        //textView.setText(user.getDisplayName());


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_user, container, false);
        nametextView = view.findViewById(R.id.name_user);
        surnameTextView = view.findViewById(R.id.surname_user);
        emailTextView = view.findViewById(R.id.email_user);
        telephoneTextView = view.findViewById(R.id.telephone_user);
        skillTextView = view.findViewById(R.id.skill);
        softSkillTextView =view.findViewById(R.id.softskill);

        Bundle data = getArguments();
        if(data != null){
            name_user = data.getString("name_user");
            surname_user = data.getString("surname_user");
            telephone_user = data.getString("telephone_user");
            email_user = data.getString("email_user");
            skill = data.getFloat("skill");
            softskill = data.getFloat("softskill");

            nametextView.setText(name_user);
            surnameTextView.setText(surname_user);
            emailTextView.setText(email_user);
            telephoneTextView.setText(telephone_user);
            skillTextView.setText(String.valueOf(skill));
            softSkillTextView.setText(String.valueOf(softskill));
        }

        return view;
    }
}