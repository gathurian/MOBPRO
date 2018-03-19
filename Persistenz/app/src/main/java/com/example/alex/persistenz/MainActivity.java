package com.example.alex.persistenz;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    AppPreferenceActivity preferences;
    TextView onResume_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = new AppPreferenceActivity();
    }

    @Override
    protected void onResume(){
        super.onResume();

        onResume_text = (TextView) findViewById(R.id.preference_text);

        final SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        final int newResumeCount = preferences.getInt("COUNTER_KEY", 0)+1;
        editor.putInt("COUNTER_KEY", newResumeCount);
        editor.apply();

        onResume_text.setText("MainActivity.onResume() wurde seit der Installation dieser App " + newResumeCount + " mal aufgerufen");
    }

    public void editPreferences(View view){
        preferences.startPreferences();
    }

}
