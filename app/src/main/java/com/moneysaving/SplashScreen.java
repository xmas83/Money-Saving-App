package com.moneysaving;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.moneysaving.app.LoginActivity;
import com.moneysaving.app.R;

public class SplashScreen extends AppCompatActivity implements Animation.AnimationListener {

    ProgressBar progressBar;
    Animation animFadein;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageView = findViewById(R.id.iconrotate);

// load the animation
        animFadein = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.rotate);

        // set animation listener
        animFadein.setAnimationListener(this);

        imageView.startAnimation(animFadein);
        progressBar = findViewById(R.id.progressBar);
        Window window = SplashScreen.this.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.setStatusBarColor(ContextCompat.getColor(SplashScreen.this, R.color.colorPrimaryDark));
        }
        new CountDownTimer(3000, 40) { //30000 milli seconds is total time, 1000 milli seconds is tick time interval

            public void onTick(long millisUntilFinished) {
                progressBar.setProgress(progressBar.getProgress() + 1);
            }

            public void onFinish() {
                progressBar.setProgress(100);
                startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                finish();

            }
        }.start();

    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        // Take any action after completing the animation


    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onAnimationStart(Animation animation) {
        // TODO Auto-generated method stub

    }
}
