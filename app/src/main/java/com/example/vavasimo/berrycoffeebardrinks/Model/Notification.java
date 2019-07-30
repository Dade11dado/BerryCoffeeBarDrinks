package com.example.vavasimo.berrycoffeebardrinks.Model;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import com.example.vavasimo.berrycoffeebardrinks.R;

import java.util.Collections;
import java.util.List;

public class Notification extends Application {

    public static final String CHANNEL_1_ID = "channel 1";
    public static final String CHANNEL_2_ID = "channel 2";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    private void createNotificationChannel(){

         if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
             //Creazione del canale di notifica 1
             CharSequence name1 = getString(R.string.chn1name);
             String description1 = getString(R.string.chn1des);
             int importance1 = NotificationManager.IMPORTANCE_HIGH;
             NotificationChannel channel1 = new NotificationChannel(CHANNEL_1_ID, name1, importance1);
             channel1.setDescription(description1);

             //Creazione del canale di notifica 2
             CharSequence name2 = getString(R.string.chn2name);
             String description2 = getString(R.string.chn2des);
             int importance2 = NotificationManager.IMPORTANCE_HIGH;
             NotificationChannel channel2 = new NotificationChannel(CHANNEL_2_ID,name2,importance2);
             channel2.setDescription(description2);

             NotificationManager notificationManager = getSystemService(NotificationManager.class);
             notificationManager.createNotificationChannels(Collections.singletonList(channel1));
             notificationManager.createNotificationChannels(Collections.singletonList(channel2));

         }

     }}
