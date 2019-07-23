package com.example.vavasimo.berrycoffeebardrinks;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.app.AlertDialog;
import android.content.DialogInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class RecuperoPassword extends AppCompatActivity {
    EditText mMail;
    String mail;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_password);
        mMail = (EditText)findViewById(R.id.EtRecuperoPass);
        mAuth = FirebaseAuth.getInstance();

    }

    public void BackLog(View view) {
        Intent intent1 = new Intent(RecuperoPassword.this,LogInActivity.class);
        finish();
        startActivity(intent1);
    }

    public void ResetAll(View view) {
        mail = mMail.getText().toString();
        mAuth.sendPasswordResetEmail(mail)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                           AlertPositive("Ripristino eseguito con successo","Controlla la tua casella mail per ripristinare la password");
                        }else{
                            AlertPositive("Errore","Riprovare più tardi");
                        }
                    }
                });
    }

    public void AlertPositive(String Title, String Messaggio){
        AlertDialog.Builder miaAlert = new AlertDialog.Builder(this);
        miaAlert.setTitle(Title);
        miaAlert.setMessage(Messaggio);
        miaAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent2 = new Intent(RecuperoPassword.this, LogInActivity.class);
                finish();
                startActivity(intent2);
            }
        });
        AlertDialog alert = miaAlert.create();
        alert.show();
    }
}
