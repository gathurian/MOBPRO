package com.example.alex.persistenz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

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
        String sweetener = teaPrefs.getString("teaSweetener", "keinem Süssungsmittel");
        tea_preference.setText("ich trinke am liebsten " + teaPreferences + " mit " + sweetener);
    }

}
