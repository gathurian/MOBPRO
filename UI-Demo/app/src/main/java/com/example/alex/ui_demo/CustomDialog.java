package com.example.alex.ui_demo;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Alex on 11.03.2018.
 */

public class CustomDialog extends Dialog implements View.OnClickListener{

    public Activity activity;
    public Dialog dialog;
    public Button send;
    public EditText comment;

    public CustomDialog(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        send = (Button) findViewById(R.id.customDialogYes);
        send.setOnClickListener(this);
        comment = (EditText) findViewById(R.id.customDialogText);
    }

    @Override
    public void onClick(View view){
    }


}
