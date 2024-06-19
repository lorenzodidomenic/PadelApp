package com.example.padel;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.os.FileUtils;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {

    TextInputEditText editTextEmail,editTextPassword,editName,editSurname,editTelephone;
    Button buttonReg;
    ProgressBar progressBar;
    FirebaseAuth auth;



    TextView register;

    /* questo contorlla se l untente è gia loggato */
    /*
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
         //prendiamo

        DatabaseReference database = FirebaseDatabase.getInstance("https://padel-5d8f6-default-rtdb.europe-west1.firebasedatabase.app").getReference("users");

        editTextEmail = (TextInputEditText)findViewById(R.id.email);
        editTextPassword = (TextInputEditText) findViewById(R.id.password);
        editName = (TextInputEditText) findViewById(R.id.name);
        editSurname = (TextInputEditText) findViewById(R.id.surname);
        editTelephone = (TextInputEditText) findViewById(R.id.telephone);


        buttonReg = (Button) findViewById(R.id.btn_register);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        register = (TextView) findViewById(R.id.loginNow);

        //mi creo un ascoltatore di eventi
        ValueEventListener userListener = new ValueEventListener(){
            //questo verrà invocato quando cambierà un dato
            //(in questo banale esempio sappiamo l'id che stiamo cambiando)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Log.W(TAG,"User on cancelled",databaseError.toException())
            }
        };

        //aggiungo l'ascoltaotre di eventi al database


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });

        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,password,name,surname,telephone;
                progressBar.setVisibility(View.VISIBLE);

                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());
                name = String.valueOf(editName.getText());
                surname = String.valueOf(editSurname.getText());
                telephone = String.valueOf(editTelephone.getText());


                auth = FirebaseAuth.getInstance();
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Register.this,"Enter Email",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Register.this,"Enter Password",Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {

                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(Register.this, "Account Created.",
                                            Toast.LENGTH_SHORT).show();

                                    FirebaseUser user = auth.getCurrentUser();
                                    String uid = String.valueOf(user.getUid());
                                    User new_user = new User(name,surname,telephone,email);

                                    DatabaseReference database = FirebaseDatabase.getInstance("https://padel-5d8f6-default-rtdb.europe-west1.firebasedatabase.app").getReference("users");
                                    database.child(uid).setValue(new_user);

                                    Intent intent = new Intent(getApplicationContext(), Login.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Register.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });


                }

                //dopo aver controllato se email e password esistono dobbiamo creare l'utente

            //codice preso da firebase

            });
        }

}
