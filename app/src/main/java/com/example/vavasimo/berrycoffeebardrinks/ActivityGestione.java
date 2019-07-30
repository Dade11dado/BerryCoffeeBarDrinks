package com.example.vavasimo.berrycoffeebardrinks;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vavasimo.berrycoffeebardrinks.Model.ButtonInformation;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityGestione extends AppCompatActivity {

    boolean btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,sendNotification;
    int Contatore=0;
    TextView apeOmaggio;
    int sommaAperitivi=0;
    ImageView img1,img2,img3,img4,img5,img6,img7,img8,img9;
    FirebaseDatabase mDatabase;
    TextView riferimentoUtente;
    String utenteFinale;
    DatabaseReference myRef;
    String utenteFinaleRep, testoNotifica;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestione);
        apeOmaggio=findViewById(R.id.TvContatore);
        img1=findViewById(R.id.img1);
        img2=findViewById(R.id.img2);
        img3=findViewById(R.id.img3);
        img4=findViewById(R.id.img4);
        img5=findViewById(R.id.img5);
        img6=findViewById(R.id.img6);
        img7=findViewById(R.id.img7);
        img8=findViewById(R.id.img8);
        img9=findViewById(R.id.img9);
        mDatabase=FirebaseDatabase.getInstance();
        riferimentoUtente=findViewById(R.id.etMailGest);
        myRef = mDatabase.getReference();
        utenteFinale=getIntent().getExtras().getString("mail","null");
        utenteFinaleRep = utenteFinale.replace("Format: QR_CODE Contents: ","");
        riferimentoUtente.setText(utenteFinaleRep.replace("=","."));
        myRef = mDatabase.getReference(utenteFinaleRep);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ButtonInformation buttonInformation = dataSnapshot.getValue(ButtonInformation.class);
                showData(buttonInformation);
                sommaAperitivi=buttonInformation.getApeOmaggio();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void showData (ButtonInformation buttonInformation){
        try{if(buttonInformation.getButton1()==true){
            img1.setImageResource(R.drawable.berry_icon);
            btn1=true;
            ResetConteggio();
        }else{
            btn1=false;
            img1.setImageResource(R.drawable.radio_button);


        }

        if(buttonInformation.getButton2()==true){

            img2.setImageResource(R.drawable.berry_icon);
            btn2=true;
            ResetConteggio();
        }else{
            img2.setImageResource(R.drawable.radio_button);
            btn2=false;
        }

        if(buttonInformation.getButton3()==true){
            img3.setImageResource(R.drawable.berry_icon);
            btn3=true;
            ResetConteggio();
        }else{
            img3.setImageResource(R.drawable.radio_button);
            btn3=false;
        }

        if(buttonInformation.getButton4()==true){
            img4.setImageResource(R.drawable.berry_icon);
            btn4=true;
            ResetConteggio();
        }else{
            img4.setImageResource(R.drawable.radio_button);
            btn4=false;
        }

        if(buttonInformation.getButton5()==true){
            img5.setImageResource(R.drawable.berry_icon);
            btn5=true;
            ResetConteggio();
        }else{
            img5.setImageResource(R.drawable.radio_button);
            btn5=false;
        }

        if(buttonInformation.getButton6()==true){
            img6.setImageResource(R.drawable.berry_icon);
            btn6=true;
            ResetConteggio();
        }else{
            img6.setImageResource(R.drawable.radio_button);
            btn6=false;
        }

        if(buttonInformation.getButton7()==true){
            img7.setImageResource(R.drawable.berry_icon);
            btn7=true;
            ResetConteggio();
        }else{
            img7.setImageResource(R.drawable.radio_button);
            btn7=false;
        }

        if(buttonInformation.getButton8()==true){
            img8.setImageResource(R.drawable.berry_icon);
            btn8=true;
            ResetConteggio();
        }else{
            img8.setImageResource(R.drawable.radio_button);
            btn8=false;
        }

        if(buttonInformation.getButton9()==true){
            img9.setImageResource(R.drawable.berry_icon);
            btn9=true;
            ResetConteggio();
        }else{
            img9.setImageResource(R.drawable.radio_button);
            btn9=false;
        }

        apeOmaggio.setText(Integer.toString(buttonInformation.getApeOmaggio()));}catch(NullPointerException e){Log.e("ERRORE","CIAO");}
    }



    public void Btn1(View view) {
        if (btn1==false){
            btn1=true;
        }else{
            btn1=false;
        }
        if (btn1==true){
            img1.setImageResource(R.drawable.berry_icon);
        }else{
            img1.setImageResource(R.drawable.radio_button);
        }
     ResetConteggio();
    }

    public void Btn2(View view){
        if (btn2==false){
        btn2=true;
    }else{
        btn2=false;
    }
        if (btn2==true){
        img2.setImageResource(R.drawable.berry_icon);
    }else{
        img2.setImageResource(R.drawable.radio_button);
    }
        ResetConteggio();
    }

    public void Btn3(View view) {
        if (btn3==false){
            btn3=true;
        }else{
            btn3=false;
        }
        if (btn3==true){
            img3.setImageResource(R.drawable.berry_icon);
        }else{
            img3.setImageResource(R.drawable.radio_button);
        }
        ResetConteggio();
    }

    public void Btn4(View view) {
        if (btn4==false){
            btn4=true;
        }else{
            btn4=false;
        }
        if (btn4==true){
            img4.setImageResource(R.drawable.berry_icon);
        }else{
            img4.setImageResource(R.drawable.radio_button);
        }
        ResetConteggio();
    }

    public void Btn5(View view) {
        if (btn5==false){
            btn5=true;
        }else{
            btn5=false;
        }
        if (btn5==true){
            img5.setImageResource(R.drawable.berry_icon);
        }else{
            img5.setImageResource(R.drawable.radio_button);
        }
        ResetConteggio();
    }

    public void Btn6(View view) {
        if (btn6==false){
            btn6=true;
        }else{
            btn6=false;
        }
        if (btn6==true){
            img6.setImageResource(R.drawable.berry_icon);
        }else{
            img6.setImageResource(R.drawable.radio_button);
        }
        ResetConteggio();
    }

    public void Btn7(View view) {
        if (btn7==false){
            btn7=true;
        }else{
            btn7=false;
        }
        if (btn7==true){
            img7.setImageResource(R.drawable.berry_icon);
        }else{
            img7.setImageResource(R.drawable.radio_button);
        }
        ResetConteggio();
    }

    public void Btn8(View view) {
        if (btn8==false){
            btn8=true;
        }else{
            btn8=false;
        }
        if (btn8==true){
            img8.setImageResource(R.drawable.berry_icon);
        }else{
            img8.setImageResource(R.drawable.radio_button);
        }
        ResetConteggio();
    }

    public void Btn9(View view) {
        if (btn9==false){
            btn9=true;
        }else{
            btn9=false;
        }
        if (btn9==true){
            img9.setImageResource(R.drawable.berry_icon);
        }else{
            img9.setImageResource(R.drawable.radio_button);
        }
        ResetConteggio();
    }

    public void resetConteggio(View view) {
       sommaAperitivi=0;
       apeOmaggio.setText(Integer.toString(sommaAperitivi));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.layout_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.log_out_item){
            Intent intent = new Intent (ActivityGestione.this, LogInActivity.class);
            finish();
            startActivity(intent);
        return true;
    }
        return true;
    }



     public void ResetConteggio(){
         if ((btn1==true&&btn2==true&&btn3==true&&btn4==true&&btn5==true&&btn6==true&&btn7==true&&btn8==true&&btn9==true)){
             sommaAperitivi++;
             apeOmaggio.setText(Integer.toString(sommaAperitivi));
             sendNotification=true;
             btn1=false;btn2=false;btn3=false;btn4=false;btn5=false;btn6=false;btn7=false;btn8=false;btn9=false;
             img1.setImageResource(R.drawable.radio_button);
             img2.setImageResource(R.drawable.radio_button);
             img3.setImageResource(R.drawable.radio_button);
             img4.setImageResource(R.drawable.radio_button);
             img5.setImageResource(R.drawable.radio_button);
             img6.setImageResource(R.drawable.radio_button);
             img7.setImageResource(R.drawable.radio_button);
             img8.setImageResource(R.drawable.radio_button);
             img9.setImageResource(R.drawable.radio_button);

         }
     }

    public void inviaDati(View view) {
        ButtonInformation data = new ButtonInformation(btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,sommaAperitivi,sendNotification);
        myRef.setValue(data);
    }

    public void onBackPressed (){
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        ButtonInformation data = new ButtonInformation(btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,sommaAperitivi,sendNotification);
                        myRef.setValue(data);
                        finish();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        break;


                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Assicurati di salvare prima di uscire").setPositiveButton("Salva", dialogClickListener)
                .setNegativeButton("Torna", dialogClickListener).show();
    }
    }










