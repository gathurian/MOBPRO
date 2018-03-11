package com.example.alex.ui_demo;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioButton linearLayout;
    RadioButton relativeLayout;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = (RadioButton) findViewById(R.id.radiobutton_linearLayout);
        relativeLayout = (RadioButton) findViewById(R.id.radiobutton_relativeLayout);
        spinner = (Spinner) findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = (String) adapterView.getItemAtPosition(i);
                Toast.makeText(getApplicationContext(), selectedItem, Toast.LENGTH_SHORT).show();
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
}
