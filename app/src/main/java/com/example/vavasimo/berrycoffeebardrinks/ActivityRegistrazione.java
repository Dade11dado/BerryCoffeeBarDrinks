package com.example.vavasimo.berrycoffeebardrinks;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class ActivityRegistrazione extends AppCompatActivity {

    private static final String CHAT_PREFS = "Chat prefs";
    private static final String NOME_KEY = "username";
    EditText mEmail;
    EditText mNomeUtente;
    EditText mPassword;
    EditText mConfermaPassword;
    Switch MostraPassword;

    private FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user!=null){
            String email = user.getEmail();
            Intent chat = new Intent (ActivityRegistrazione.this, ActivityClienti.class);
            chat.putExtra("msg",email);
            finish();
            startActivity(chat);
            Toast.makeText(this, "Utente giaà loggato",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione);
        mAuth=FirebaseAuth.getInstance();
        initUI();
        MostraPassword = (Switch)findViewById(R.id.switch2);
        MostraPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    mPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    mConfermaPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }else{
                    mPassword.setInputType(129);
                    mConfermaPassword.setInputType(129);
                }
            }
        });

    }

    private void initUI() {
        mEmail=findViewById(R.id.etRegMail);
        mNomeUtente=findViewById(R.id.etRegUtente);
        mPassword=findViewById(R.id.etRegPass);
        mConfermaPassword=findViewById(R.id.etRegRipPass);

    }

    private void createFirebaseUser (String email, String password, final String nome){

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            //Registrazione avvenuta con successo, aggiorna le informazioni dell'utente
                            showDialog("Registrazione effettuata con successo","Avviso",android.R.drawable.ic_dialog_info);
                            salvaNome();
                            setNome(nome);
                            Intent intent1 = new Intent (ActivityRegistrazione.this, ActivityClienti.class);
                            finish();
                            startActivity(intent1);
                        }else{
                            showDialog("Errore nella registrazione","ERRORE",android.R.drawable.ic_dialog_alert);
                        }
                    }
                });
    }

    private void salvaNome(){
        String nome = mNomeUtente.getText().toString();
        SharedPreferences pref = getSharedPreferences(CHAT_PREFS,0);
        pref.edit().putString(NOME_KEY,nome);
    }

    private void setNome (String nome){
        FirebaseUser user = mAuth.getCurrentUser();
        UserProfileChangeRequest changeRequest = new UserProfileChangeRequest.Builder()
                .setDisplayName(nome)
                .build();
            }

            private void showDialog(String messaggio, String titolo, int icona){
        new AlertDialog.Builder(this)
                .setTitle(titolo)
                .setMessage(messaggio)
                .setPositiveButton(android.R.string.ok,null)
                .setIcon(icona)
                .show();
            }

    public void RegBtn(View view) {
        if (mEmail==null||mPassword==null||mNomeUtente==null||mConfermaPassword==null){
            erroreDati();
        }
        String nome = mNomeUtente.getText().toString();
        String ConfPassword = mConfermaPassword.getText().toString();
        String password = mPassword.getText().toString();
        String email = mEmail.getText().toString();
        if (!nomeValido(nome)){
            Toast.makeText(this, "Nome utente non valido",Toast.LENGTH_LONG).show();
        }else if (!passwordValida(password)){
            Toast.makeText(this, "PLa Password deve contenere più di 7 caratteri per essere valida",Toast.LENGTH_LONG).show();
        }else if (!mailValida(email)){
            Toast.makeText(this, "Email non valida",Toast.LENGTH_LONG).show();
        }else if (!confermaValida(password,ConfPassword)){
            Toast.makeText(this, "La conferma della password non combacia con la password",Toast.LENGTH_LONG).show();
        } else{
            createFirebaseUser(email,password,nome);
        }


    }

    private void erroreDati() {
        new AlertDialog.Builder(this)
                .setTitle("Errore")
                .setMessage("Assicurarsi di aver riempito tutti i campi")
                .setPositiveButton(android.R.string.ok,null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void StringLog(View view) {
        Intent inten2 = new Intent(ActivityRegistrazione.this, LogInActivity.class);
        finish();
        startActivity(inten2);
    }

    private boolean nomeValido(String nome){
        if (nome.length()>=4)
            return true;
            else
                return false;
    }

    private boolean passwordValida(String password){
        if (password.length()>7)
            return true;
            else
                return false;
    }

    private boolean confermaValida(String password, String confPassword){
        if(password.equals(confPassword))
            return true;
            else
                return false;
    }

    private boolean mailValida(String mail){return mail.contains("@");}
}


