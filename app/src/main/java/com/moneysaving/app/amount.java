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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class amount extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DBHelper dbHelper = new DBHelper(amount.this);
    EditText editText;
    String uid, sitem;
    String nm, em;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amount);
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add Amount");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        editText = (EditText) findViewById(R.id.enteramount);
        nm = getIntent().getExtras().getString("name");
        em = getIntent().getExtras().getString("email");
        uid = getIntent().getExtras().getString("id");
        sitem = getIntent().getExtras().getString("item");
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

    public void enteramount(View view) {
        DateFormat dateFormat = new SimpleDateFormat("MM");
        Date date = new Date();
        String d = dateFormat.format(date);
        String temp = editText.getText().toString();


        if ((!temp.isEmpty())) {
            long a = dbHelper.insertCategoryInDb(Integer.valueOf(uid), sitem, temp, Integer.valueOf(d));
            Toast.makeText(getApplicationContext(), String.valueOf(a), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Enter required field/amount is greater than income", Toast.LENGTH_SHORT).show();
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
            Intent I = new Intent(amount.this, UserProfile.class);
            I.putExtra("useri", uid);
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
            Intent I = new Intent(amount.this, SetthingsActivity.class);
            startActivity(I);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
