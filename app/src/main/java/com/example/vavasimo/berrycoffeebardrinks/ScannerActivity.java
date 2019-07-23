package com.example.vavasimo.berrycoffeebardrinks;

import android.os.Bundle;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.example.vavasimo.berrycoffeebardrinks.Model.ButtonInformationSend;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Scanner;


public class ScannerActivity extends AppCompatActivity implements View.OnClickListener {


        //View Object
    private Button buttonScan;
    private String mail;
    private EditText editTextNotifica;
    private Button Invia;
    //qrCodeScannerObject
    private IntentIntegrator qrScan;
    String results;
    String notifica;
    FirebaseDatabase mDatabase;
    DatabaseReference myRef;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanner_activity);

        //View Object
        buttonScan = (Button) findViewById(R.id.buttonScan);

        //initialising scan object
        qrScan = new IntentIntegrator(this);

        //attaching on click listener
        buttonScan.setOnClickListener(this);

        editTextNotifica=(EditText)findViewById(R.id.editTextNotifica);


        mDatabase=FirebaseDatabase.getInstance();
        myRef=mDatabase.getReference("TestoNotifica");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
               mail = result.getContents();
                Intent intent1 = new Intent (ScannerActivity.this, ActivityGestione.class);
                intent1.putExtra("mail",mail);
                finish();
                startActivity(intent1);

                //if qr contains data
                /*try {
                    //converting the data to json
                    JSONObject obj = new JSONObject(result.getContents());
                    //setting values to textviews
                    textViewName.setText(obj.getString("name"));
                    textViewAddress.setText(obj.getString("address"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    //if control comes here
                    //that means the encoded format not matches
                    //in this case you can display whatever data is available on the qrcode
                    //to a toast
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                }*/
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onClick(View v) {
        qrScan.initiateScan();
    }

    public void Btn1(View view) {
        Intent intent1 = new Intent(ScannerActivity.this, LogInActivity.class);
        finish();
        startActivity(intent1);
    }

    public void InviaNotifica(View view){
        notifica = editTextNotifica.getText().toString();
        ButtonInformationSend data = new ButtonInformationSend(notifica);
        myRef.setValue(data);
    }
}