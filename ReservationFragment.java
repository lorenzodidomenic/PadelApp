package com.example.padel;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Date;


public class ReservationFragment extends Fragment {

    FragmentManager fragmentManager;
    private Spinner possibleHours, possibleTypes;
    private TextInputEditText player1, player2 , player3, player4;
    private String stringDateSelected,field;

    ArrayList<String> items = new ArrayList<String>();

    public ReservationFragment() {
        // Required empty public constructor
    }

    public static ReservationFragment newInstance(String param1, String param2) {

        ReservationFragment fragment = new ReservationFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getParentFragmentManager();

        Bundle data = this.getArguments();
        if(data != null){

            items = data.getStringArrayList("items");
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



        View view = inflater.inflate(R.layout.fragment_reservation, container, false);


        player1 = (TextInputEditText) view.findViewById(R.id.player1) ;
        player2 = (TextInputEditText) view.findViewById(R.id.player2) ;
        player3 = (TextInputEditText) view.findViewById(R.id.player3) ;
        player4 = (TextInputEditText) view.findViewById(R.id.player4) ;


        player1 = (TextInputEditText) view.findViewById(R.id.player1) ;
        player2 = (TextInputEditText) view.findViewById(R.id.player2) ;
        player3 = (TextInputEditText) view.findViewById(R.id.player3) ;
        player4 = (TextInputEditText) view.findViewById(R.id.player4) ;

        //dovrei fare qui una query su quel campo per quel girono e vedere quali orari sono già occupati e levarli dalla lista
        //mi potrei fare questa cosa e passare i dati in un nuovo frammento dobe ho items con gli orari disponibili
        //e faccio la stessa cosa che faccio qui

        possibleHours = (Spinner)view.findViewById(R.id.spinnerHours);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        possibleHours.setAdapter(adapter);

        possibleTypes = (Spinner)view.findViewById(R.id.spinnerType);
        String[] items2 = new String[]{"Prenotazione","Semi-Prenotazione"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, items2);
        possibleTypes.setAdapter(adapter2);



        Button button = (Button)view.findViewById(R.id.saveEventBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hours = possibleHours.getSelectedItem().toString();
                String types = possibleTypes.getSelectedItem().toString();

                String player1name = String.valueOf(player1.getText());
                String player2name = String.valueOf(player2.getText());
                String player3name = String.valueOf(player3.getText());
                String player4name = String.valueOf(player4.getText());








                DatabaseReference database = FirebaseDatabase.getInstance("https://padel-5d8f6-default-rtdb.europe-west1.firebasedatabase.app").getReference("reservations/"+field);

                String prenotazione = stringDateSelected;

                database.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {


                        if (types.isEmpty()) {
                            Toast.makeText(getContext(), "Seleziona un tipo di prenotazione", Toast.LENGTH_LONG).show();
                        } else if ((types.compareTo("Prenotazione") == 0) && (player1name.isEmpty() || player2name.isEmpty() || player3name.isEmpty() || player4name.isEmpty())) {
                            Toast.makeText(getContext(), "Devi inserire almeno 4 giocatori", Toast.LENGTH_LONG).show();
                        }
                        else if((types.compareTo("Semi-Prenotazione")==0)&&(snapshot.hasChild(prenotazione+ "/" + hours+" yellow-flag"))){

                            Toast.makeText(getContext(), "Campo Semi Prenotato", Toast.LENGTH_LONG).show();
                        }
                        else if((types.compareTo("Semi-Prenotazione")==0)&&(!snapshot.hasChild(prenotazione+ "/" + hours+" yellow-flag"))&&(player1name.isEmpty())){
                            Toast.makeText(getContext(), "Inserire almeno un giocatore", Toast.LENGTH_LONG).show();
                        }
                        else if((types.compareTo("Semi-Prenotazione")==0)&&(!snapshot.hasChild(prenotazione+ "/" + hours+" yellow-flag"))&&(!player1name.isEmpty())&&(!player2name.isEmpty())&&(!player3name.isEmpty())&&(!player4name.isEmpty())){
                            Toast.makeText(getContext(), "Semi-Prenotazione non possibile con 4 giocatori", Toast.LENGTH_LONG).show();
                        }
                        else if((types.compareTo("Semi-Prenotazione")==0)&&(!snapshot.hasChild(prenotazione+ "/" + hours+"yellow-flag"))){
                            Reservation reservation = new Reservation(types, player1name, player2name, player3name, player4name,0,0,0,0,0,0,0,0,0);
                            database.child(prenotazione).child(hours+" yellow-flag").setValue(reservation);

                            Toast.makeText(getContext(), "Semi-Prenotazione effettuata", Toast.LENGTH_LONG).show();

                            MainFragment fragment = new MainFragment();

                            fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, fragment, null)   //vogliamo indicare di spaostarci da questo fragment ad un altro, cambia quello che è in questo containetr con un nuovo fragment
                                    .setReorderingAllowed(true)  //reordering allowed
                                    .addToBackStack("name") // così che se faccio indietro ritorna a questo fragment
                                    .commit();
                        }
                        else{

                            Reservation reservation = new Reservation(types, player1name, player2name, player3name, player4name, 0,0,0,0,0,0,0,0,0);
                            database.child(prenotazione).child(hours).setValue(reservation);

                            Toast.makeText(getContext(), "Prenotazione effettuata", Toast.LENGTH_LONG).show();

                            MainFragment fragment = new MainFragment();

                            fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, fragment, null)   //vogliamo indicare di spaostarci da questo fragment ad un altro, cambia quello che è in questo containetr con un nuovo fragment
                                    .setReorderingAllowed(true)  //reordering allowed
                                    .addToBackStack("name") // così che se faccio indietro ritorna a questo fragment
                                    .commit();
                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                // Reservation reservation = new Reservation(types,player1name,player2name,player3name,player4name);

                // database.child(prenotazione).setValue(reservation);





            }
        });

        return view;


    }
}