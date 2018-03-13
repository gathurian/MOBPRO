package com.example.alex.ui_demo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    RadioButton linearLayout;
    RadioButton relativeLayout;
    Spinner spinner;
    Spinner dialogSpinner;
    int counter;
    Button countButton;
    boolean spinnerIsInitialized = false;
    boolean dialogIsInitialized = false;
    CustomDialog cd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counter = 0;
        linearLayout = (RadioButton) findViewById(R.id.radiobutton_linearLayout);
        relativeLayout = (RadioButton) findViewById(R.id.radiobutton_relativeLayout);
        spinner = (Spinner) findViewById(R.id.spinner);
        dialogSpinner = (Spinner) findViewById(R.id.dialogSpinner);
        countButton = (Button) findViewById(R.id.buttonSaveState);
        cd = new CustomDialog(this);

        countButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;
                String countString = (String) Integer.toString(counter);
                Toast.makeText(getApplicationContext(), countString, Toast.LENGTH_SHORT).show();
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(spinnerIsInitialized) {
                    String selectedItem = (String) adapterView.getItemAtPosition(i);
                    Toast.makeText(getApplicationContext(), selectedItem, Toast.LENGTH_SHORT).show();
                } else {
                    spinnerIsInitialized = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        dialogSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(dialogIsInitialized) {
                    String selectedItem = (String) adapterView.getItemAtPosition(i);
                    dialogChooser(selectedItem);
                } else {
                    dialogIsInitialized = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    public void LinearLayout(View view){
        Intent i = new Intent(this, LayoutDemoActivity.class);
        i.putExtra("layout",R.layout.layoutdemo_linearlayout);
        startActivity(i);
    }

    public void RelativeLayout(View view){
        Intent i = new Intent(this, LayoutDemoActivity.class);
        i.putExtra("layout",R.layout.layoutdemo_constraintlayout);
        startActivity(i);
    }

    public void ViewsDemos(View view){
        Intent i = new Intent(this,ViewsDemoActivity.class);
        startActivity(i);
    }

    public void dialogChooser(String selectedItem){
        String[] dialogItems = getResources().getStringArray(R.array.dialogues);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if(Objects.equals(selectedItem, dialogItems[0])){
            Toast.makeText(getApplicationContext(), selectedItem, Toast.LENGTH_SHORT).show();
        }
        if(Objects.equals(selectedItem, dialogItems[1])){
            builder.setTitle("Reaktorproblem")
                    .setMessage(getResources().getString(R.string.dialogWithDataMessage))
                    .setPositiveButton(getResources().getString(R.string.dialogWithDataPos), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.dialogWithDataToastPos), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNeutralButton(getResources().getString(R.string.dialogWithDataNeu), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.dialogWithDataToastNeu), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton(getResources().getString(R.string.dialogWithDataNeg), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.dialogWithDataToastNeg), Toast.LENGTH_SHORT).show();
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        if(Objects.equals(selectedItem, dialogItems[2])){
            final String[] items = {"Kaffee", "Tee", "Wasser"};
            //TODO: User can choose from Array items and Toast displays selected item.
            builder.setTitle("Wähle ein Getränk")
                    .setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(getApplicationContext(), items[i], Toast.LENGTH_SHORT).show();
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        if(Objects.equals(selectedItem, dialogItems[3])){   //Dialog mit eigenem Layout
            //TODO: Do something with CustomDialog.... dunno what
            Dialog dialog = createCustomLayoutDialog();
            dialog.show();

        }
        if(Objects.equals(selectedItem, dialogItems[4])) {
            //TODO: Work out how the fuck Notifications works
            String UI_DEMO_CHANNEL_ID = "ch.hslu.mobpro.ui-demo";
            String UI_DEMO_CHANNEL_NAME = "UI-DEMO CHANNEL";

            NotificationChannel uiDemoChannel = new NotificationChannel(UI_DEMO_CHANNEL_ID, UI_DEMO_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager nManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            nManager.createNotificationChannel(uiDemoChannel);

            Notification.Builder nbuilder = new Notification.Builder(getApplicationContext(), UI_DEMO_CHANNEL_ID)
                    .setContentTitle("Sie haben eine Notification")
                    .setContentText("Lorem Impsum Dolor")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setAutoCancel(true);

            nManager.notify(101,nbuilder.build());

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("KEY_COUNTER", counter);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        counter = savedInstanceState.getInt("KEY_COUNTER");
    }

    private Dialog createCustomLayoutDialog(){
        final View customView = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Gefällt Ihnen diese Katze?")
                .setPositiveButton("Abschicken", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),cd.comment.getText() , Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog dialog = builder.create();
        return dialog;
    }
}
