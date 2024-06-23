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
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;


public class Field1Fragment extends Fragment {

    DatabaseReference database;
    FragmentManager fragmentManager;
    private String stringDateSelected;
    private Spinner possibleHours, possibleTypes;
    private TextInputEditText player1, player2 , player3, player4;


    ArrayList<String> items_reservations;
    ArrayList<String> items_rating;
    private String field;   //identificativo passato dalla'avtivty main che mi servirà per prendermi il nodo del campo

    public Field1Fragment() {
        // Required empty public constructor
    }


    public static Field1Fragment newInstance(String param1, String param2) {
        Field1Fragment fragment = new Field1Fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getParentFragmentManager();
        Bundle data = this.getArguments();
        if(data != null){

            field = data.getString("field");
            System.out.println(field);
        }else{
            System.out.println("data null");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_field1, container, false);
        CalendarView calendarView = (CalendarView) view.findViewById(R.id.calendarView);
        Button button = (Button)view.findViewById(R.id.saveEventBtn);
        Button button2 = (Button)view.findViewById(R.id.insertRating);

        DatabaseReference database = FirebaseDatabase.getInstance("https://padel-5d8f6-default-rtdb.europe-west1.firebasedatabase.app").getReference("reservations/"+field);

        //io adesso faccio una query sugli items che non esistono pee quel giorno e creo una lista di items

        /*
        player1 = (TextInputEditText) view.findViewById(R.id.player1) ;
        player2 = (TextInputEditText) view.findViewById(R.id.player2) ;
        player3 = (TextInputEditText) view.findViewById(R.id.player3) ;
        player4 = (TextInputEditText) view.findViewById(R.id.player4) ;

        //dovrei fare qui una query su quel campo per quel girono e vedere quali orari sono già occupati e levarli dalla lista
        //mi potrei fare questa cosa e passare i dati in un nuovo frammento dobe ho items con gli orari disponibili
        //e faccio la stessa cosa che faccio qui

        possibleHours = (Spinner)view.findViewById(R.id.spinnerHours);
        String[] items = new String[]{"09 10","10 11","11 12","12 13","13 14","14 15","15 16","16 17","17 18","18 19","19 20","20 21","21 22","22 23","23 24"};
     
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        possibleHours.setAdapter(adapter);

        possibleTypes = (Spinner)view.findViewById(R.id.spinnerType);
        String[] items2 = new String[]{"Prenotazione","Semi-Prenotazione"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, items2);
        possibleTypes.setAdapter(adapter2);
*/
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                                                 @Override
                                                 public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                                                     String[] aux_items = new String[]{"09 10", "10 11", "11 12", "12 13", "13 14", "14 15", "15 16", "16 17", "17 18", "18 19", "19 20", "20 21", "21 22", "22 23", "23 24"};
                                                     stringDateSelected = Integer.toString(dayOfMonth) + ":" + Integer.toString(month + 1) + ":" + Integer.toString(year);

                                                     items_reservations = new ArrayList<String>(); //simensione massima
                                                     items_rating = new ArrayList<String>(); //simensione massima
                                                     int length = 15;
                                                     String prenotazione = stringDateSelected;




                                                     /*
                                                     if(stringDateSelected == null){
                                                         Toast.makeText(getContext(), "Seleziona una data", Toast.LENGTH_LONG ).show();
                                                     }
                                                     else if(date_selected.before(new Date())){
                                                         Toast.makeText(getContext(), "Data selezionata errata", Toast.LENGTH_LONG ).show();
                                                     }
                                                     */

                                                     if(stringDateSelected != null){

                                                         String[] date_information = stringDateSelected.split(":");
                                                         //-1900 perchè ritorna a partire dal 1900
                                                         Date date_selected = new Date(Integer.parseInt(date_information[2])-1900,Integer.parseInt(date_information[1])-1,Integer.parseInt(date_information[0]));

                                                         //se selezionata data futura vedo posti disponibili
                                                         if (date_selected.after(new Date())) {

                                                             database.addListenerForSingleValueEvent(new ValueEventListener() {
                                                             @Override
                                                             public void onDataChange(DataSnapshot snapshot) {

                                                             //faccio qiery al database per vedere orari disponibili per quel giorno
                                                             for (int i = 0; i < length; i++) {
                                                                 if (snapshot.hasChild(prenotazione + "/" + aux_items[i])) {
                                                                     //nothing
                                                                 } else {
                                                                     //System.out.println("Impossbile prenotare");
                                                                     items_reservations.add(aux_items[i]);
                                                                 }
                                                             }
                                                             }

                                                              @Override
                                                              public void onCancelled(@NonNull DatabaseError error) {

                                                              }
                                                      });
                                                         }else{  //nel caso sto cercando di valutare partita prendo solo partite possibili
                                                             database.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                 @Override

                                                                 public void onDataChange(DataSnapshot snapshot) {

                                                                     //faccio qiery al database per vedere orari disponibili per quel giorno
                                                                     for (int i = 0; i < length; i++) {
                                                                         System.out.println(prenotazione + "/" + aux_items[i]);
                                                                         if (snapshot.hasChild(prenotazione + "/" + aux_items[i])) {
                                                                             System.out.println("aaa");
                                                                             items_rating.add(aux_items[i]);
                                                                         } else {
                                                                             //System.out.println("Impossbile prenotare");

                                                                         }
                                                                     }
                                                                 }

                                                                 @Override
                                                                 public void onCancelled(@NonNull DatabaseError error) {

                                                                 }
                                                             });



                                                         }
                                                     }
                                                 }
                                             });
/*
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hours = possibleHours.getSelectedItem().toString();
                String types = possibleTypes.getSelectedItem().toString();

                String player1name = String.valueOf(player1.getText());
                String player2name = String.valueOf(player2.getText());
                String player3name = String.valueOf(player3.getText());
                String player4name = String.valueOf(player4.getText());

                if(stringDateSelected == null){
                    Toast.makeText(getContext(), "Seleziona una data", Toast.LENGTH_LONG ).show();
                }




                DatabaseReference database = FirebaseDatabase.getInstance("https://padel-5d8f6-default-rtdb.europe-west1.firebasedatabase.app").getReference("reservations/"+field);

                String prenotazione = stringDateSelected;

                database.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.hasChild(prenotazione+ "/" + hours)) {
                            //System.out.println("Impossbile prenotare");
                            Toast.makeText(getContext(), "Campo Occupato", Toast.LENGTH_LONG ).show();
                        }else {

                            System.out.println(prenotazione);
                            // run some code
                            Reservation reservation = new Reservation(types, player1name, player2name, player3name, player4name);
                            database.child(prenotazione).child(hours).setValue(reservation);

                            Toast.makeText(getContext(), "Prenotazione effettuata", Toast.LENGTH_LONG ).show();
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
*/


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Field1Fragment fragment = new Field1Fragment();
                Bundle data = new Bundle();
                String field1 = "field1";
                data.putString("field",field1);

                fragment.setArguments(data);
            */
                String[] date_information = stringDateSelected.split(":");
                //-1900 perchè ritorna a partire dal 1900
                Date date_selected = new Date(Integer.parseInt(date_information[2]) - 1900, Integer.parseInt(date_information[1]) - 1, Integer.parseInt(date_information[0]));
                if (stringDateSelected != null) {
                    if (date_selected.before(new Date())) {
                        Toast.makeText(getContext(), "Data selezionata errata", Toast.LENGTH_LONG).show();
                    } else {
                        Bundle data = new Bundle();
                        data.putStringArrayList("items", items_reservations);
                        data.putString("stringDateSelected", stringDateSelected);
                        data.putString("field", field);


                        ReservationFragment fragment = new ReservationFragment();
                        fragment.setArguments(data);

                        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, fragment, null)   //vogliamo indicare di spaostarci da questo fragment ad un altro, cambia quello che è in questo containetr con un nuovo fragment
                                .setReorderingAllowed(true)  //reordering allowed
                                .addToBackStack("name") // così che se faccio indietro ritorna a questo fragment
                                .commit();
                    }
                }else{
                    Toast.makeText(getContext(), "Seleziona una data", Toast.LENGTH_LONG).show();
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                Bundle data = new Bundle();
                data.putStringArrayList("items",items);
                data.putString("stringDateSelected",stringDateSelected);
                data.putString("field",field);
*/
                if(stringDateSelected != null) {
                    String[] date_information = stringDateSelected.split(":");
                    //-1900 perchè ritorna a partire dal 1900
                    Date date_selected = new Date(Integer.parseInt(date_information[2]) - 1900, Integer.parseInt(date_information[1]) - 1, Integer.parseInt(date_information[0]));
                   if (date_selected.after(new Date())) {
                        Toast.makeText(getContext(), "Non puoi valutare una partita futura", Toast.LENGTH_LONG).show();
                    } else {

                        //fragment.setArguments(data);
                       Bundle data = new Bundle();
                       data.putStringArrayList("items", items_rating);  //ho preso solo le partite possibili e gliele passos
                       data.putString("stringDateSelected", stringDateSelected);
                       data.putString("field", field);

                       MatchRating fragment = new MatchRating();
                       fragment.setArguments(data);



                        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, fragment, null)   //vogliamo indicare di spaostarci da questo fragment ad un altro, cambia quello che è in questo containetr con un nuovo fragment
                                .setReorderingAllowed(true)  //reordering allowed
                                .addToBackStack("name") // così che se faccio indietro ritorna a questo fragment
                                .commit();
                    }
                }else{
                        Toast.makeText(getContext(), "Seleziona una data", Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }

}