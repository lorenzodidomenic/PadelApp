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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MatchRating#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MatchRating extends Fragment {

    FragmentManager fragmentManager;
    private String stringDateSelected,field;

    private int update;
    private TextView player1text,player2text,player3text,player4text;
    private int RankPlayer1,RankPlayer2,RankPlayer3,RankPlayer4,SoftRankPlayer1,SoftRankPlayer2,SoftRankPlayer3,SoftRankPlayer4;
    private Reservation match;

    Boolean player1done = false,player2done=false,player3done=false,player4done = false;

    ArrayList<String> items = new ArrayList<String>();
    Spinner possibleHours,spinnerGiocatore1,spinnerGiocatore2,spinnerGiocatore3,spinnerGiocatore4,spinner2Giocatore1,spinner2Giocatore2,spinner2Giocatore3,spinner2Giocatore4 ;


    public MatchRating() {
        // Required empty public constructor
    }


    public static MatchRating newInstance(String param1, String param2) {
        MatchRating fragment = new MatchRating();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getParentFragmentManager();
        Bundle data = this.getArguments();

        if(data != null) {
            items = data.getStringArrayList("items");
            stringDateSelected = data.getString("stringDateSelected");
            field = data.getString("field");
            System.out.println(items.size());
        }
        else{
            System.out.println("datanull");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_match_rating, container, false);

        possibleHours = (Spinner)view.findViewById(R.id.spinnerHours);
        player1text = (TextView)view.findViewById(R.id.player1) ;
        player2text = (TextView)view.findViewById(R.id.player2) ;
        player3text = (TextView)view.findViewById(R.id.player3) ;
        player4text = (TextView)view.findViewById(R.id.player4) ;
        spinnerGiocatore1 = (Spinner)view.findViewById(R.id.spinnerGiocatore1);
       spinnerGiocatore2 = (Spinner)view.findViewById(R.id.spinnerGiocatore2);
        spinnerGiocatore3 = (Spinner)view.findViewById(R.id.spinnerGiocatore3);
       spinnerGiocatore4 = (Spinner)view.findViewById(R.id.spinnerGiocatore4);
        spinner2Giocatore1 = (Spinner)view.findViewById(R.id.spinner2Giocatore1);
        spinner2Giocatore2 = (Spinner)view.findViewById(R.id.spinner2Giocatore2);
        spinner2Giocatore3 = (Spinner)view.findViewById(R.id.spinner2Giocatore3);
        spinner2Giocatore4 = (Spinner)view.findViewById(R.id.spinner2Giocatore4);

        String[] possibleRate = {"0","1","2","3","4","5","6","7","8","9","10"};
        ArrayAdapter<String> adapter0 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, possibleRate);
        spinnerGiocatore1.setAdapter(adapter0);
        spinnerGiocatore2.setAdapter(adapter0);
        spinnerGiocatore3.setAdapter(adapter0);
        spinnerGiocatore4.setAdapter(adapter0);
        spinner2Giocatore1.setAdapter(adapter0);
        spinner2Giocatore2.setAdapter(adapter0);
        spinner2Giocatore3.setAdapter(adapter0);
        spinner2Giocatore4.setAdapter(adapter0);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        possibleHours.setAdapter(adapter);

        DatabaseReference database = FirebaseDatabase.getInstance("https://padel-5d8f6-default-rtdb.europe-west1.firebasedatabase.app").getReference("reservations/"+field+"/"+stringDateSelected);
        Button button = (Button)view.findViewById(R.id.searchBtn);
        Button button2 = (Button)view.findViewById(R.id.saveBtb);


        DatabaseReference databaseUsers = FirebaseDatabase.getInstance("https://padel-5d8f6-default-rtdb.europe-west1.firebasedatabase.app").getReference("users");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hour = possibleHours.getSelectedItem().toString();  //prendo ora selezionata

                //mi devo andare a prendere come reference quella partita



                database.child(hour).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        match  = dataSnapshot.getValue(Reservation.class);
                        String player1 = match.getPlayer1();
                        String player2 = match.getPlayer2();
                        String player3 = match.getPlayer3();
                        String player4 = match.getPlayer4();

                        player1text.setText(player1);
                        player2text.setText(player2);
                        player3text.setText(player3);
                        player4text.setText(player4);


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("The read failed: " + databaseError.getCode());
                    }
                });

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                update = 0; //mi serve per finire l'onChange quando ho fatto 4 cambiamenti



                String hour = possibleHours.getSelectedItem().toString();  //prendo ora selezionata

                RankPlayer1 = Integer.valueOf(spinnerGiocatore1.getSelectedItem().toString());
                RankPlayer2 = Integer.valueOf(spinnerGiocatore2.getSelectedItem().toString());
                RankPlayer3 = Integer.valueOf(spinnerGiocatore3.getSelectedItem().toString());
                RankPlayer4 = Integer.valueOf(spinnerGiocatore4.getSelectedItem().toString());
                SoftRankPlayer1 = Integer.valueOf(spinner2Giocatore1.getSelectedItem().toString());
                SoftRankPlayer2 = Integer.valueOf(spinner2Giocatore2.getSelectedItem().toString());
                SoftRankPlayer3 = Integer.valueOf(spinner2Giocatore3.getSelectedItem().toString());
                SoftRankPlayer4 = Integer.valueOf(spinner2Giocatore4.getSelectedItem().toString());

                int numeroVoti = match.getNumVote();
                float rank1 = ((match.skillPlayer1*numeroVoti)+RankPlayer1)/(numeroVoti+1);
                float rank2 = ((match.skillPlayer2*numeroVoti)+RankPlayer2)/(numeroVoti+1);
                float rank3 = ((match.skillPlayer3*numeroVoti)+RankPlayer3)/(numeroVoti+1);
                float rank4 = ((match.skillPlayer4*numeroVoti)+RankPlayer4)/(numeroVoti+1);
                float softRank1 = ((match.softSkillPlayer1*numeroVoti)+SoftRankPlayer1)/(numeroVoti+1);
                float softRank2 = ((match.softSkillPlayer2*numeroVoti)+SoftRankPlayer2)/(numeroVoti+1);
                float softRank3 = ((match.softSkillPlayer3*numeroVoti)+SoftRankPlayer3)/(numeroVoti+1);
                float softRank4 = ((match.softSkillPlayer4*numeroVoti)+SoftRankPlayer4)/(numeroVoti+1);

                Reservation match = new Reservation("Prenotazione",(String)player1text.getText(), (String)player2text.getText(),(String)player3text.getText(),(String)player4text.getText(),rank1,rank2,rank3,rank4,numeroVoti+1,softRank1,softRank2,softRank3,softRank4);

                database.child(hour).setValue(match);

                Toast.makeText(getContext(), "Valutazione Partita Effettuata", Toast.LENGTH_LONG).show();

                MainFragment fragment = new MainFragment();

                fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, fragment, null)   //vogliamo indicare di spaostarci da questo fragment ad un altro, cambia quello che è in questo containetr con un nuovo fragment
                        .setReorderingAllowed(true)  //reordering allowed
                        .addToBackStack("name") // così che se faccio indietro ritorna a questo fragment
                        .commit();




                //mi cerco il profilo dell'utente con quel nome
                databaseUsers.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

if(update < 4) {
    //prendiamo tutti i dati dentro users
    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

        User user = dataSnapshot.getValue(User.class);
        String player = user.getName() + " " + user.getSurname();
        if (player.compareTo((String) player1text.getText()) == 0 && !player1done) {


            Float newRank = ((user.getSkillValue() * user.numGames) + RankPlayer1) / (user.numGames + 1);
            Float newSoftRank = ((user.getSoftSkillValue() * user.numGames) + SoftRankPlayer1) / (user.numGames + 1);
            System.out.println(newRank);
            System.out.println(newSoftRank);
            User new_user = new User(user.getName(), user.getSurname(), user.getEmail(), user.getTelephone(), newRank, newSoftRank, user.getNumGames() + 1);

            System.out.println(dataSnapshot.getKey());
            databaseUsers.child(dataSnapshot.getKey()).setValue(new_user);
            update ++;
            player1done = true;
            break;

        }
        else if (player.compareTo((String) player2text.getText()) == 0 && !player2done) {
            Float newRank = ((user.getSkillValue() * user.numGames) + RankPlayer2) / (user.numGames + 1);
            Float newSoftRank = ((user.getSoftSkillValue() * user.numGames) + SoftRankPlayer2) / (user.numGames + 1);

            User new_user = new User(user.getName(), user.getSurname(), user.getEmail(), user.getTelephone(), newRank, newSoftRank, user.getNumGames() + 1);

            System.out.println(dataSnapshot.getKey());
            databaseUsers.child(dataSnapshot.getKey()).setValue(new_user);
            player2done = true;
            update ++;
            break;
        } else if (player.compareTo((String) player3text.getText()) == 0 && !player3done) {
            Float newRank = ((user.getSkillValue() * user.numGames) + RankPlayer3) / (user.numGames + 1);
            Float newSoftRank = ((user.getSoftSkillValue() * user.numGames) + SoftRankPlayer3) / (user.numGames + 1);

            User new_user = new User(user.getName(), user.getSurname(), user.getEmail(), user.getTelephone(), newRank, newSoftRank, user.getNumGames() + 1);

            System.out.println(dataSnapshot.getKey());
            databaseUsers.child(dataSnapshot.getKey()).setValue(new_user);
            update ++;
            player3done = true;
            break;
        } else if (player.compareTo((String) player4text.getText()) == 0 && !player4done) {
            Float newRank = ((user.getSkillValue() * user.numGames) + RankPlayer4) / (user.numGames + 1);
            Float newSoftRank = ((user.getSoftSkillValue() * user.numGames) + SoftRankPlayer4) / (user.numGames + 1);

            User new_user = new User(user.getName(), user.getSurname(), user.getEmail(), user.getTelephone(), newRank, newSoftRank, user.getNumGames() + 1);

            System.out.println(dataSnapshot.getKey());
            databaseUsers.child(dataSnapshot.getKey()).setValue(new_user);
            update ++;
            player4done = true;
            break;
        }

    }
}
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                MainFragment fragment2 = new MainFragment();

                fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, fragment2, null)   //vogliamo indicare di spaostarci da questo fragment ad un altro, cambia quello che è in questo containetr con un nuovo fragment
                        .setReorderingAllowed(true)  //reordering allowed
                        .addToBackStack("name") // così che se faccio indietro ritorna a questo fragment
                        .commit();
            }
        });
        return view;

    }
}