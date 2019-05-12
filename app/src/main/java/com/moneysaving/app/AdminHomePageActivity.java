package com.moneysaving.app;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.hardware.SensorManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.lukedeighton.wheelview.WheelView;
import com.lukedeighton.wheelview.adapter.WheelAdapter;
import com.squareup.seismic.ShakeDetector;

import java.util.ArrayList;

public class AdminHomePageActivity extends AppCompatActivity implements ShakeDetector.Listener{


        private WheelView mWheelView;
        private String[] colors = {"#fd5308", "#fd5308", "#fd5308", "#fd5308"};
        private ArrayList<Drawable> icons = new ArrayList<>();
        int wheelItems = 6;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_admin_home_page);

            SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

            ShakeDetector shakeDetector = new ShakeDetector(this);

            shakeDetector.start(sensorManager);

            populateWheelView();

        }

        public void populateWheelView() {

            mWheelView = (WheelView) findViewById(R.id.wheelViewAdmin);
            mWheelView.setWheelItemCount(wheelItems);

            final ShapeDrawable[] shapeDrawables = new ShapeDrawable[wheelItems];
            icons.add(getResources().getDrawable(R.drawable.log_out_icon));
            icons.add(getResources().getDrawable(R.drawable.activity_icon));
            icons.add(getResources().getDrawable(R.drawable.bank_account_icon));
            icons.add(getResources().getDrawable(R.drawable.profile_icon));
            icons.add(getResources().getDrawable(R.drawable.overview_icon));
            icons.add(getResources().getDrawable(R.drawable.users_icon));

            for (int i = 0; i < wheelItems; i++) {
                shapeDrawables[i] = new ShapeDrawable(new OvalShape());
            }

            mWheelView.setAdapter(new WheelAdapter() {
                @Override
                public Drawable getDrawable(int position) {
                    return icons.get(position);
                }

                @Override
                public int getCount() {
                    return icons.size();
                }

                public Object getItem(int position) {
                    return null;
                }
            });
            mWheelView.setOnWheelItemClickListener(new WheelView.OnWheelItemClickListener() {
                @Override
                public void onWheelItemClick(WheelView parent, int position, boolean isSelected) {
                    if (position == 0) {
                        logOutBtnClick();
                    } else if (position == 2) {
                        budgetBtnClick();
                    } else if (position == 3) {
                        profileBtnClick();
                    } else if (position == 4) {
                        overviewBtnClick();
                    } else if (position == 5) {
                        usersBtnClick();
                    } else {
                        Toast.makeText(com.moneysaving.app.AdminHomePageActivity.this, "Menu Item: " + position, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    @Override
    public void hearShake() {
        Intent intent1 = new Intent(AdminHomePageActivity.this, BalanceActivity.class);
        startActivity(intent1);
    }

    public void usersBtnClick () {
        Intent intent1 = new Intent(AdminHomePageActivity.this, Adminpanel.class);
        startActivity(intent1);
    }
    public void profileBtnClick () {
        Intent intent1 = new Intent(AdminHomePageActivity.this, UserActivity.class);
        startActivity(intent1);
    }
    public void budgetBtnClick () {
        Intent intent1 = new Intent(AdminHomePageActivity.this, BalanceActivity.class);
        startActivity(intent1);
    }
    public void overviewBtnClick () {
        Intent intent1 = new Intent(AdminHomePageActivity.this, OverviewActivity.class);
        startActivity(intent1);
    }

    public void logOutBtnClick () {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Security Message");
        builder.setMessage("Are you sure that you want log out?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                Intent intent1 = new Intent(AdminHomePageActivity.this, SignUp.class);
                startActivity(intent1);
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
}
