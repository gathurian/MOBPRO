package com.example.alex.ui_demo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        }
        if(Objects.equals(selectedItem, dialogItems[3])){   //Dialog mit eigenem Layout
            //TODO: Do something with CustomDialog.... dunno what
        }
        if(Objects.equals(selectedItem, dialogItems[4])){
            //TODO: Work out how the fuck Notifications work
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
}
