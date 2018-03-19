package com.example.alex.persistenz;

import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class AppPreferenceActivity extends AppCompatActivity {

    public static final class TeaPreferenceInitializer extends PreferenceFragment{
        @Override
        public void onCreate(final Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.tea_preferences);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void startPreferences(){
        TeaPreferenceInitializer initializer = new TeaPreferenceInitializer();
        Log.i("AppPreferenceActivity", "Der TeaPreferenceInitializer wurde initialisiert");
    }
}
