package com.example.vavasimo.berrycoffeebardrinks;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.vavasimo.berrycoffeebardrinks.Model.ButtonInformationSend;

import static com.example.vavasimo.berrycoffeebardrinks.Model.Notification.CHANNEL_1_ID;
import static com.example.vavasimo.berrycoffeebardrinks.Model.Notification.CHANNEL_2_ID;


public class LogInActivity extends AppCompatActivity {

    //Dichiarazione variabili  membro
    private FirebaseAuth mAuth;
    EditText mEmail;
    EditText mPassword;
    FirebaseUser user;
    String email;
    Button button;
    Switch ShowPassword;
    FirebaseDatabase mDatabase;
    DatabaseReference myRef;
    String testoNotifica;
    NotificationManagerCompat notificationManager;
    private int notificationID1 = 0;

    //Dichiarazione costanti
    public final String TAG = "LogInActivity";

    @Override
    protected void onStop() {
        super.onStop();
        creaNotifica();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        creaNotifica();
        //Inizializzazione FirebaseAuth
        mAuth=FirebaseAuth.getInstance();
        //Inizializzazione variabili
        mEmail=findViewById(R.id.etMail);
        mPassword=findViewById(R.id.etPassword);
        user = mAuth.getCurrentUser();
        button = findViewById(R.id.buttonLog);
        ShowPassword=(Switch)findViewById(R.id.switch1);
        mDatabase=FirebaseDatabase.getInstance();
        myRef=mDatabase.getReference("TestoNotifica");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ButtonInformationSend buttonInformationSend = dataSnapshot.getValue(ButtonInformationSend.class);
                testoNotifica=buttonInformationSend.getTestoNotifica();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


       ShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if(isChecked){
                   mPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
               }else{
                   mPassword.setInputType(129);
               }
           }
       });







    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        //Se l'utente è loggato andare in Main Activity
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user!=null){
            String email = user.getEmail();
            Intent chat = new Intent (LogInActivity.this, ActivityClienti.class);
            chat.putExtra("msg",email);
            finish();
            startActivity(chat);
        }
    }



    public void StringReg(View view) {
        Intent intent1 = new Intent (LogInActivity.this, ActivityRegistrazione.class);
        finish();
        startActivity(intent1);
    }

    public void LogInBtn(View view) {
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();
        if ( (email.equals("Maurizio")) && (password.equals("Berry1234")) ){
            Intent intent2 = new Intent (LogInActivity.this, ScannerActivity.class);
            finish();
            startActivity(intent2);
        }else{
            if (mEmail==null||mPassword==null){
                Toast.makeText(this, "Il campo nome utente o password non possono essere vuoti",Toast.LENGTH_LONG).show();
            }else{
                if (!(email.length()>7) || !(email.contains("@"))){
                    Toast.makeText(this, "Email non valida",Toast.LENGTH_LONG).show();
                }else if (!(password.length()>7)){
                    Toast.makeText(this, "Password non valida",Toast.LENGTH_LONG).show();
                }else{
                    loginUser(email,password);
                }
            }
        }
    }

    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LogInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }


    public void ResetPassword(View view) {
        Intent Intent1 = new Intent(LogInActivity.this,RecuperoPassword.class);
        finish();;
        startActivity(Intent1);
    }

    public void prendoDati(ButtonInformationSend buttonInformationSend){
        testoNotifica=buttonInformationSend.getTestoNotifica().toString();
    }
    public void creaNotifica(){
    if(testoNotifica!=null){
        Intent intent = new Intent (this, ActivityClienti.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent ,0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.berry_icon)
                .setContentTitle("Berry Coffe Bar & Drinks")
                .setContentText(testoNotifica)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(testoNotifica));

        notificationManager.notify(notificationID1, builder.build()); }}


    }



