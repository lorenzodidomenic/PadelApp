package com.example.padel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LessonInfo extends Fragment {

    TextView daytextView,hoursTextView,fieldTextView,descriptionTextView,coachTextView;

    Button button;

    String day,hours,field,description,coach,prenotati;

    FirebaseAuth auth;
    String name_user,surname_user;

    public LessonInfo() {
        // Required empty public constructor
    }


    public static LessonInfo newInstance(String param1, String param2) {
        LessonInfo fragment = new LessonInfo();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lesson_info, container, false);

        daytextView = view.findViewById(R.id.day);
        hoursTextView = view.findViewById(R.id.hour);
        descriptionTextView = view.findViewById(R.id.description);
        coachTextView = view.findViewById(R.id.coach);
        fieldTextView = view.findViewById(R.id.field);

        button = view.findViewById(R.id.btn);


        Bundle data = getArguments();
        if(data != null){
            day = data.getString("day");
            hours = data.getString("hour");
            description = data.getString("description");
            field = data.getString("field");
            coach = data.getString("coach");
            prenotati = data.getString("prenotati");


            daytextView.setText(day);
            hoursTextView.setText(hours);
            descriptionTextView.setText(description);
            coachTextView.setText(field);
            fieldTextView.setText(String.valueOf(coach));

        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference database = FirebaseDatabase.getInstance("https://padel-5d8f6-default-rtdb.europe-west1.firebasedatabase.app").getReference("lessons");

                FirebaseUser user = auth.getCurrentUser();
                String uid = user.getUid().toString();
                DatabaseReference database2 = FirebaseDatabase.getInstance("https://padel-5d8f6-default-rtdb.europe-west1.firebasedatabase.app").getReference("users").child(uid);


                database2.addValueEventListener(new ValueEventListener() {

                    String name,surname;

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user = snapshot.getValue(User.class);

                        surname = user.getSurname();
                        name = user.getName();
                        Lesson lesson = new Lesson(coach, description, day, hours,field, prenotati + " " + name + surname + ",");

                        if(prenotati.contains(name+surname)){
                            Toast.makeText(getContext(), "Sei Già Prenotato per questa lezione",
                                    Toast.LENGTH_SHORT).show();
                        }
                         else {
                            database.child("Lezione " + day + " " + hours).setValue(lesson);

                            Toast.makeText(getContext(), "Prenotazione Effettuata",
                                    Toast.LENGTH_SHORT).show();

                        }

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