package com.example.alex.ui_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LayoutDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layout = getIntent().getIntExtra("layout", R.layout.activity_layout_demo);
        setContentView(layout);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setTitle("Layout Demo");
    }
}
