package com.example.alex.comcon;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button httpButton;
    Button demoThreadButton;
    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        httpButton = (Button) findViewById(R.id.httpButton);

        httpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent httpActivity = new Intent(getApplicationContext(), HttpDemosActivity.class);
                startActivity(httpActivity);
            }
        });

        demoThreadButton = (Button) findViewById(R.id.demoThreadButton);
        demoThreadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Button demoButton = (Button) view;
                if ((thread == null) || !(thread.isAlive())) {
                    thread = waitForSevenSeconds(demoButton);
                    thread.start();
                    demoThreadButton.setText("Demothread läuft");
                } else {
                    Toast.makeText(getApplicationContext(), "DemoThread läuft schon!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private Thread waitForSevenSeconds(final Button button) {
        return new Thread("DemoThread") {
            @Override
            public void run() {
                final Runnable doneRunnable = new Runnable() {
                    @Override
                    public void run() {
                        button.setText("DemoThread starten");
                    }
                };
                try {
                    Thread.sleep(7000);
                    MainActivity.this.runOnUiThread(doneRunnable);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public void startMultiAsyncTask(View view) {
        AsyncTask asyncTask = new MultiAsyncTask(this);
        try {
            URL[] urls = new URL[4];
            for(int i=0;i<4;i++){
                urls[i] = new URL("http://wherever.ch/hslu/title"+i+".txt");
            }
            asyncTask.execute(urls);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}



