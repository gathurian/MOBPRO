package com.example.alex.servicesreceiver;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;


public class DemoService extends Service implements DemoServiceApi {

    public DemoService(){

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        AsyncTask<Void, Void, Void> asyncTask = new AsyncTask<Void, Void, Void>() {     //Inline Create Asynctask with no Parameters, Progress or Result params
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Log.d("Demoservice", "Demoservice before sleep");
                    Thread.sleep(3000);
                    Log.d("Demoservice", "DemoService after sleep");
                }catch (InterruptedException e){
                    Log.d("Demoservice", "Demoservice sleep interrupted");
                    e.printStackTrace();
                }
                return null;
            }
        };
        asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,null);   //THREAD_POOL_EXECUTOR für mehrere Threads --> Nebenläufigkeit
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        getNumberOfJobsRunning();
        getNumberOfJobsCompleted();
        resetCounters();
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Demoservice", "Demoservice gestartet");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Demoservice", "Demoservice gestoppt");
    }

    @Override
    public int getNumberOfJobsRunning() {
        return 0;
    }

    @Override
    public int getNumberOfJobsCompleted() {
        return 0;
    }

    @Override
    public void resetCounters() {

    }
}