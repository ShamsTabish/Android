package com.acadgild.sharedpreferencesandasynchtask;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class MySettingPreference extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       addPreferencesFromResource(R.xml.content_my_setting_preference);
/*

        SharedPreferences mySettings= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        Boolean status=mySettings.getBoolean("statusVisiblity",true);

        Toast.makeText(MySettingPreference.this, "Status "+status, Toast.LENGTH_SHORT).show();
*/

        SharedPreferences mySettings= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        mySettings.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
              //  if(sharedPreferences.contains(key))
               //     Toast.makeText(MySettingPreference.this, "Key is "+key+" Its Value is "+sharedPreferences.getBoolean(key,true), Toast.LENGTH_SHORT).show();
                Toast.makeText(MySettingPreference.this, sharedPreferences.getAll().toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }

}
