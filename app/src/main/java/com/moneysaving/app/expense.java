package com.moneysaving.app;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class expense extends AppCompatActivity implements AdapterView.OnItemSelectedListener, NavigationView.OnNavigationItemSelectedListener {
    String[] category = {"Food", "Entertainment", "Car", "Home", "Clothing", "Electronics", "Health", "Children", "Work", "Other"};
    EditText amnt, descr;
    String i;
    String temp;
    DBHelper dbHelper = new DBHelper(expense.this);
    String nm, em;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add Expense");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        amnt = (EditText) findViewById(R.id.am);
        descr = (EditText) findViewById(R.id.des);

        i = getIntent().getExtras().getString("uid");
        nm = getIntent().getExtras().getString("name");
        em = getIntent().getExtras().getString("email");

        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, category);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
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
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getApplicationContext(), category[i], Toast.LENGTH_LONG).show();
        temp = category[i];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {


    }

    public void adduserexpense(View view) {
        String am1 = amnt.getText().toString();
        String ds1 = descr.getText().toString();
        DateFormat dateFormat = new SimpleDateFormat("MM");
        Date date = new Date();
        if (!am1.isEmpty() && !ds1.isEmpty()) {
            String d = dateFormat.format(date);
            long t = dbHelper.insertExpensesInDb(Integer.valueOf(i), am1, ds1, temp, Integer.valueOf(d));
            Toast.makeText(getApplicationContext(), String.valueOf(t), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Enter Value first", Toast.LENGTH_SHORT).show();
        }


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

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.list) {
            // Handle the camera action
        } else if (id == R.id.nav_Home) {
            Intent I = new Intent(expense.this, UserProfile.class);
            I.putExtra("useri", i);
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
            Intent I = new Intent(expense.this, SetthingsActivity.class);
            startActivity(I);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
