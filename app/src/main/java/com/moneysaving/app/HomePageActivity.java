package com.moneysaving.app;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;


import com.lukedeighton.wheelview.WheelView;
import com.lukedeighton.wheelview.adapter.WheelAdapter;

import java.util.ArrayList;

public class HomePageActivity extends AppCompatActivity {

    private WheelView mWheelView;
    private String[] colors = {"#fd5308","#fd5308","#fd5308","#fd5308"};
    private ArrayList<Drawable> icons = new ArrayList<>();
    int wheelItems = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        populateWheelView();

    }
    public void populateWheelView () {

        mWheelView = (WheelView) findViewById(R.id.wheelView);
        mWheelView.setWheelItemCount(wheelItems);

        final ShapeDrawable[] shapeDrawables = new ShapeDrawable[wheelItems];
        icons.add(getResources().getDrawable(R.drawable.log_out_icon));
        icons.add(getResources().getDrawable(R.drawable.activity_icon));
        icons.add(getResources().getDrawable(R.drawable.bank_account_icon));
        icons.add(getResources().getDrawable(R.drawable.profile_icon));
        icons.add(getResources().getDrawable(R.drawable.overview_icon));

        for (int i = 0; i < wheelItems; i++) {
            shapeDrawables[i] = new ShapeDrawable(new OvalShape());
            // shapeDrawables[i].getPaint().setColor(Color.parseColor(colors[i]));
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
                Toast.makeText(HomePageActivity.this, "Menu Item: "+ position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}