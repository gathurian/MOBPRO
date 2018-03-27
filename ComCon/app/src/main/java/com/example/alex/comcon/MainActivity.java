package com.example.alex.comcon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button;
    Button button1;
    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent httpActivity = new Intent(getApplicationContext(), HttpDemosActivity.class);
                startActivity(httpActivity);
            }
        });

        button1 = (Button) findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((thread == null) || (!thread.isAlive())){
                    thread = waitForSevenSeconds(button1);
                    thread.start();
                    button1.setText("Demotrhread läuft");
                } else {
                    Toast.makeText(getApplicationContext(), "DemoThread läuft schon!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private Thread waitForSevenSeconds(final Button button){
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


}
