package com.moneysaving.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Initialize components
        tv = (TextView) findViewById(R.id.intro_text);
        iv = (ImageView) findViewById(R.id.intro_image);

        // Initialize the Animation/transition
        Animation myAnimation = AnimationUtils.loadAnimation(this, R.anim.mytransition);

        tv.startAnimation(myAnimation);
        iv.startAnimation(myAnimation);

        // Transition to SignUp class
        final Intent intent = new Intent(MainActivity.this, SignUp.class);


        // Create a thread and set how long the intro shall be
        Thread timer = new Thread() {


            // Run the thread
            @Override
            public void run() {
                try {

                    sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();

                } finally {
                    startActivity(intent);
                    finish();
                }

                super.run();

            }
        };
        timer.start();
    }
}

