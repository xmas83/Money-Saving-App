package com.moneysaving.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class SetthingsActivity extends AppCompatActivity {
    private Switch nightMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Set theme before OnCreate method
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.DarkTheme);
        } else
            setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setthings);


        // Initialize the switch
        nightMode = (Switch) findViewById(R.id.switch_nightmode);


        // Check if switch is switched on
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            nightMode.setChecked(true);

            // Add onClickListener for the switch
            nightMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                        // Restart the layout
                        recreate();
                    } else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                        // Restart the layout
                        recreate();

                    }
                }
            });

        }
    }
}


