package com.moneysaving.app;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class adduincome extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DBHelper dbHelper = new DBHelper(adduincome.this);
    ArrayList<String> itemName = new ArrayList<>();
    EditText amount;
    EditText Desc;
    EditText date;
    boolean check;
    ListView List;
    int d;
    int m;
    int y;
    String cid;
    DatePickerDialog datePickerDialog;
    TextView t;
    String date1;
    static int count = 1;
    int monthAsInt;
    String nm, em;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adduincome);
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar5);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add Income");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        monthAsInt = Calendar.getInstance().get(Calendar.MONTH);


        cid = getIntent().getExtras().getString("uid");
        nm = getIntent().getExtras().getString("name");
        em = getIntent().getExtras().getString("email");
        // initiate the date picker and a button

        amount = (EditText) findViewById(R.id.Am);
        Desc = (EditText) findViewById(R.id.dsc);
        date1 = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        date = (EditText) findViewById(R.id.date);
        date.setText(date1);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_profile, menu);
        TextView nm1 = (TextView) findViewById(R.id.name1);
        TextView em1 = (TextView) findViewById(R.id.em);
        nm1.setText(nm);
        em1.setText(em);
        return true;
    }

    public void save(View view) {

        String a = amount.getText().toString();
        String b = Desc.getText().toString();
        String[] arr = date1.split("-");
        String c = arr[1];
        if (!a.isEmpty() && !b.isEmpty()) {
            if (Integer.valueOf(a) < 800) {
                Toast.makeText(getApplicationContext(), "enter amount more than 800", Toast.LENGTH_SHORT).show();
            } else {
                long ab = dbHelper.insertIncome(Integer.valueOf(cid), a, b, Integer.valueOf(c));
                Toast.makeText(getApplicationContext(), String.valueOf(ab), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Enter the required Fields", Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.list) {
            // Handle the camera action
        } else if (id == R.id.nav_Home) {
            Intent I = new Intent(adduincome.this, UserProfile.class);
            I.putExtra("useri", cid);
            I.putExtra("username", nm);
            I.putExtra("email", em);
            startActivity(I);

        } else if (id == R.id.ourstory) {

        } else if (id == R.id.rateus) {
            final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
            }

        } else if (id == R.id.nav_setting) {
            Intent I = new Intent(adduincome.this, SetthingsActivity.class);
            startActivity(I);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_back:
                // do your stuff here
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
