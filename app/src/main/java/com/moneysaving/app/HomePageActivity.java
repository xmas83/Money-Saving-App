package com.moneysaving.app;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.hardware.SensorManager;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;


import com.squareup.seismic.ShakeDetector;

import java.util.ArrayList;

public class HomePageActivity extends AppCompatActivity implements ShakeDetector.Listener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        SensorManager sensorManager= (SensorManager) getSystemService(SENSOR_SERVICE);

        ShakeDetector shakeDetector = new ShakeDetector (this);

        shakeDetector.start(sensorManager);



    }

    @Override
    public void hearShake() {

        Intent intent1 = new Intent(HomePageActivity.this, BalanceActivity.class);
        startActivity(intent1);

    }
}
