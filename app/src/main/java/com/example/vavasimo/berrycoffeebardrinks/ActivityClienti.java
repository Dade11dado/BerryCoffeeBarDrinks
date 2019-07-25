package com.example.vavasimo.berrycoffeebardrinks;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vavasimo.berrycoffeebardrinks.Model.ButtonInformation;
import com.example.vavasimo.berrycoffeebardrinks.Model.Notification;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.encoder.QRCode;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import static android.app.Application.getProcessName;
import static com.example.vavasimo.berrycoffeebardrinks.Model.Notification.CHANNEL_1_ID;

public class ActivityClienti extends AppCompatActivity{
    private static final String CHAT_PREFS = "Chat prefs";
    private static final String NOME_KEY = "username";
    FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private String mail;
    private String mailNoSpace;
    TextView Utente;
    TextView ApeOmaggio;
    ImageView img1,img2,img3,img4,img5,img6,img7,img8,img9;
    ButtonInformation bInfo;
    FirebaseUser user;
    String utenteLog;
    ImageView QrCode;
    int Numero;
    private boolean btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;
    int contatore=0;
    int apeOmaggio=0;
    boolean sendNotiufication;
    private int notificationID1 = 0;
    NotificationManagerCompat notificationManager;
    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState){
        notificationManager = NotificationManagerCompat.from(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clienti);
        Utente=findViewById(R.id.tvUtente);
        ApeOmaggio = findViewById(R.id.tvOmaggio);
        bInfo=new ButtonInformation();
        img1=findViewById(R.id.img1);
        img2=findViewById(R.id.img2);
        img3=findViewById(R.id.img3);
        img4=findViewById(R.id.img4);
        img5=findViewById(R.id.img5);
        img6=findViewById(R.id.img6);
        img7=findViewById(R.id.img7);
        img8=findViewById(R.id.img8);
        img9=findViewById(R.id.img9);

        QrCode=(ImageView)findViewById(R.id.QRCode);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase=FirebaseDatabase.getInstance();
        user = mAuth.getCurrentUser();
        utenteLog=user.getDisplayName();
        mail=user.getEmail();
        mailNoSpace=mail.replaceAll("\\.","=");
        Log.i("mailNoSpace",mailNoSpace);
        try{
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            BitMatrix bitMatrix = multiFormatWriter.encode(mailNoSpace, BarcodeFormat.QR_CODE,500,500);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            QrCode.setImageBitmap(bitmap);}
        catch(WriterException e){
            e.printStackTrace();
        }

        myRef=mFirebaseDatabase.getReference(mailNoSpace);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ButtonInformation buttonInformation = dataSnapshot.getValue(ButtonInformation.class);
                showData(buttonInformation);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

        private void showData (ButtonInformation buttonInformation){
           try{ sendNotiufication=buttonInformation.getSendNotification();
            Numero=buttonInformation.getApeOmaggio();}catch(NullPointerException e){Log.e ("Errore","annulla");}
        try{
        if (buttonInformation.getButton1() == true) {
            btn1=true;
            img1.setImageResource(R.drawable.berry_icon);
            Log.i("ApeOmaggio",Integer.toString(contatore));
            creaNotifica();
        } else {
            btn1=false;
            img1.setImageResource(R.drawable.radio_button);
            creaNotifica();
        }

        if (buttonInformation.getButton2() == true) {
            btn2=true;
            img2.setImageResource(R.drawable.berry_icon);
            Log.i("ApeOmaggio",Integer.toString(contatore));
            creaNotifica();
        } else {
            btn2=false;
            img2.setImageResource(R.drawable.radio_button);
            creaNotifica();
        }

        if (buttonInformation.getButton3() == true) {
            btn3=true;
            img3.setImageResource(R.drawable.berry_icon);
            Log.i("ApeOmaggio",Integer.toString(contatore));
            creaNotifica();
        } else {
            btn3=false;
            img3.setImageResource(R.drawable.radio_button);
            creaNotifica();
        }

        if (buttonInformation.getButton4() == true) {
            img4.setImageResource(R.drawable.berry_icon);
            btn4=true;
            Log.i("ApeOmaggio",Integer.toString(contatore));
            creaNotifica();
        } else {
            btn4=false;
            img4.setImageResource(R.drawable.radio_button);
            creaNotifica();
        }

        if (buttonInformation.getButton5() == true) {
            img5.setImageResource(R.drawable.berry_icon);
            btn5=true;
            Log.i("ApeOmaggio",Integer.toString(contatore));
            creaNotifica();
        } else {
            btn5=false;
            img5.setImageResource(R.drawable.radio_button);
            creaNotifica();
        }

        if (buttonInformation.getButton6() == true) {
            img6.setImageResource(R.drawable.berry_icon);
            btn6=true;
            Log.i("ApeOmaggio",Integer.toString(contatore));
            creaNotifica();
        } else {
            btn6=false;
            img6.setImageResource(R.drawable.radio_button);
            creaNotifica();
        }

        if (buttonInformation.getButton7() == true) {
            img7.setImageResource(R.drawable.berry_icon);
            btn7=true;
            Log.i("ApeOmaggio",Integer.toString(contatore));
            creaNotifica();
        } else {
            btn7=false;
            img7.setImageResource(R.drawable.radio_button);
            creaNotifica();
        }

        if (buttonInformation.getButton8() == true) {
            img8.setImageResource(R.drawable.berry_icon);
            btn8=true;
            Log.i("ApeOmaggio",Integer.toString(contatore));
            creaNotifica();
        } else {
            btn8=false;
            img8.setImageResource(R.drawable.radio_button);
            creaNotifica();
        }

        if (buttonInformation.getButton9() == true) {
            img9.setImageResource(R.drawable.berry_icon);
            btn9=true;
            Log.i("ApeOmaggio",Integer.toString(contatore));
            creaNotifica();
        } else {
            btn9=false;
            img9.setImageResource(R.drawable.radio_button);
            creaNotifica();
        }
        ApeOmaggio.setText(Integer.toString(buttonInformation.getApeOmaggio()));}catch(NullPointerException e){
            Toast.makeText(this, "Benvenuto nella tua scheda utente",Toast.LENGTH_LONG).show();
        }}

    private void creaNotifica() {
        if(sendNotiufication==true){
            Log.i("NOTIFICHE","la notifica funziona");
        Intent intent = new Intent (this, ActivityClienti.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent ,0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.berry_icon)
                .setContentTitle("Hai "+ Numero+ " aperitivi in omaggio")
                .setContentText("Passa al Berry Coffee Bar & Drink e riscuoti il tuo aperitivo in omaggio")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setStyle(new NotificationCompat.BigTextStyle()
                .bigText("Passa al Berry Coffee Bar & Drink e riscuoti il tuo aperitivo in omaggio"));

        notificationManager.notify(notificationID1, builder.build()); }

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
            mAuth.signOut();
            Intent intent = new Intent (ActivityClienti.this, LogInActivity.class);
            finish();
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    }


