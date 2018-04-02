package com.example.alex.servicesreceiver;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;


public class DemoIntentService extends IntentService {

    public DemoIntentService() {
        super("IntentService");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
            Log.d("DemoIntentService", "IntentService before sleep");
            Thread.sleep(3000);
            Log.d("DemoIntentService", "IntentService after sleep");
        } catch (InterruptedException e) {
            Log.d("DemoIntentService", "Intent$ervice sleep interrupted");
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("DemoIntentService", "IntentService gestartet");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("DemoIntentService", "IntentService gestoppt");
    }


}
