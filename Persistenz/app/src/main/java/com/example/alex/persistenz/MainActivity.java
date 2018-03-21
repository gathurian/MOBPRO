package com.example.alex.persistenz;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;

public class MainActivity extends AppCompatActivity {

    TextView onResume_text;
    TextView tea_preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume(){
        super.onResume();

        onResume_text = (TextView) findViewById(R.id.preference_text);
        tea_preference = (TextView) findViewById(R.id.tea_preference);

        final SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        final int newResumeCount = preferences.getInt("COUNTER_KEY", 0)+1;
        editor.putInt("COUNTER_KEY", newResumeCount);
        editor.apply();

        onResume_text.setText("MainActivity.onResume() wurde seit der Installation dieser App " + newResumeCount + " mal aufgerufen");

        setTeaPrefText();
    }

    public void editPreferences(View view){
        Intent prefs = new Intent(this,AppPreferenceActivity.class);
        startActivity(prefs);
    }

    public void defaultTeaSettings(View button){
        SharedPreferences defaultPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = defaultPrefs.edit();

        editor.putBoolean("sweetTea", true);
        editor.putString("teaSweetener", "Rohrzucker");
        editor.putString("teaPreferred", "Lipton/Pfefferminztee");
        editor.apply();

        setTeaPrefText();
    }

    private void setTeaPrefText(){
        SharedPreferences teaPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String teaPreferences = teaPrefs.getString("teaPreferred", "ohne nix");
        Boolean teaSweetened = teaPrefs.getBoolean("sweetTea", false);
        String sweetener;
        if(teaSweetened) {
            sweetener = " mit " + teaPrefs.getString("teaSweetener", "keinem Süssungsmittel");
        } else{
            sweetener = " ungesüsst";
        }
        tea_preference.setText("ich trinke am liebsten " + teaPreferences + sweetener);
    }

    public void saveData(View Button){
        CheckBox sdCard = (CheckBox) findViewById(R.id.writeToSDCard);
        EditText data = (EditText) findViewById(R.id.editText);
        if(data.getText().toString().length() == 0){
            Toast.makeText(getApplicationContext(), "You haven't written anything yet", Toast.LENGTH_SHORT).show();
        } else {
            if (sdCard.isChecked()) {
                int grant = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if(grant != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},23);
                } else {
                    writeSDCard();
                }
            } else {
                try {
                    FileOutputStream fOut = openFileOutput("notes.txt", MODE_PRIVATE);
                    String note = data.getText().toString();
                    fOut.write(note.getBytes());
                    fOut.close();
                    Toast.makeText(getApplicationContext(), "file saved", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }
    }

    public void loadData(View Button){
        CheckBox sdCard = (CheckBox) findViewById(R.id.writeToSDCard);
        EditText data = (EditText) findViewById(R.id.editText);
        data.setText("");

        if(sdCard.isChecked()){
            int grant = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
            if(grant != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},24);
            } else{
               readSDCard();
            }

        } else {
            try {
                FileInputStream fis = getApplicationContext().openFileInput("notes.txt");
                InputStreamReader is = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(is);
                StringBuilder sb = new StringBuilder();
                String string;
                while ((string = br.readLine()) != null) {
                    sb.append(string);
                }
                data.setText(sb.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void writeSDCard(){
        EditText data = (EditText) findViewById(R.id.editText);

        boolean ExternalStorageAvailable = false;
        boolean ExternalStorageWritable = false;
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {    //Check if SD-Card is mounted
            ExternalStorageAvailable = ExternalStorageWritable = true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {       //Check if SD-Card is writable
            ExternalStorageWritable = false;
            ExternalStorageAvailable = true;
        } else {
            ExternalStorageWritable = ExternalStorageAvailable = false;
        }

        File directory = new File(android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/persistence");
        directory.mkdirs();
        File notes = new File(directory, "notes.txt");
        if (ExternalStorageAvailable == ExternalStorageWritable == true) {
            try {
                FileOutputStream fOut = new FileOutputStream(notes);
                PrintWriter pw = new PrintWriter(fOut);
                pw.print(data.getText().toString());
                pw.flush();
                pw.close();
                fOut.close();
                Toast.makeText(getApplicationContext(), "file saved", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Could not write to SD-Card. Is it mounted?", Toast.LENGTH_SHORT).show();
        }
    }

    private void readSDCard(){
        EditText data = (EditText) findViewById(R.id.editText);
        try {
            final File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/persistence", "notes.txt");
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader is = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(is);
            StringBuilder sb = new StringBuilder();
            String stLine;
            while ((stLine = br.readLine()) != null) {
                sb.append(stLine);
            }
            data.setText(sb.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        switch (requestCode){
            case 24:
                if(grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "Permission " + permissions[0] + " denied!", Toast.LENGTH_SHORT).show();
                } else {
                    readSDCard();
                }
            case 23:
                if(grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "Permission " + permissions[0] + " denied!", Toast.LENGTH_SHORT).show();
                } else {
                    writeSDCard();
                }
                break;
        }
    }

}
