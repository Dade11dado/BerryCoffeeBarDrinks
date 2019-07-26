package com.example.vavasimo.berrycoffeebardrinks.Model;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import com.example.vavasimo.berrycoffeebardrinks.R;

import java.util.Collection;
import java.util.Collections;

public class Notification extends Application {

    public static final String CHANNEL_1_ID="channel 1";
    public static final String CHANNEL_2_ID = "channel 2";


    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {


        CharSequence name1 = getString(R.string.channel_1_id);
        String desciption1 = getString(R.string.descizione_notifica);
        int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel1 = new NotificationChannel(CHANNEL_1_ID,name1,importance);
            channel1.setDescription(desciption1);

            CharSequence name2 = getString(R.string.informazioni);
            String desciption2 = getString(R.string.descrizioneAttivita);
            int importance1 = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel channel2 = new NotificationChannel(CHANNEL_2_ID,name2,importance1);
            channel2.setDescription(desciption2);


            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel1);
            notificationManager.createNotificationChannel(channel2);
        }
        }
    }

