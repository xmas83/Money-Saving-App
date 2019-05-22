package com.moneysaving.app;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.moneysaving.app.model.User;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserProfile extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DBHelper dblist1 = new DBHelper(UserProfile.this);
    TextView nm;
    String value;
    int curs;
    TextView em;
    String uname;
    String uemail;
    String ui;
    String t = null;
    String d;
    String exp, inc;
    TextView textView, textView1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile2);
        ui = getIntent().getExtras().getString("useri");


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }


    @Override
    protected void onStart() {
        super.onStart();

        textView1 = (TextView) findViewById(R.id.aadactivityText);
        textView = (TextView) findViewById(R.id.aaduincome);

        try {
            DateFormat dateFormat = new SimpleDateFormat("MM");
            Date date = new Date();
            d = dateFormat.format(date);


            // Toast.makeText(getApplicationContext(),d,Toast.LENGTH_SHORT).show();
            Cursor cursor1 = dblist1.get_income(Integer.valueOf(ui), Integer.valueOf(d));
            cursor1.moveToFirst();
            inc = cursor1.getString(0);
            Toast.makeText(getApplicationContext(), t, Toast.LENGTH_SHORT).show();
            inc = cursor1.getString(0);
            textView.setText("Balance:" + inc);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {

            Cursor cursor = dblist1.sum_expense(Integer.valueOf(ui), Integer.valueOf(d));
            cursor.moveToFirst();
            //Toast.makeText(getApplicationContext(), "i am null", Toast.LENGTH_SHORT).show();
            exp = cursor.getString(0);
            textView1.setText("Expense:" + cursor.getString(0));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            if (Integer.valueOf(textView1.getText().toString()) == Integer.valueOf(inc)) {
                int tt = Integer.valueOf(textView1.getText().toString()) - Integer.valueOf(inc);
                textView.setText(String.valueOf(tt));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            //Toast.makeText(getApplicationContext(), "i am "+inc, Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), "i am " + exp, Toast.LENGTH_SHORT).show();
            //  Integer.valueOf(inc)==Integer.valueOf(exp) |

            if (!exp.isEmpty() && !inc.isEmpty()) {
                // Toast.makeText(getApplicationContext(), "i am "+inc, Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(), "i am "+exp, Toast.LENGTH_SHORT).show();
                int a = Integer.valueOf(inc) - Integer.valueOf(exp);
                // Toast.makeText(getApplicationContext(), "i am "+String.valueOf(a), Toast.LENGTH_SHORT).show();
                textView.setText(String.valueOf("Balance:" + a));
                if (Integer.valueOf(textView.getText().toString()) < 800) {
                    NotificationCompat.Builder builder =
                            new NotificationCompat.Builder(getApplicationContext())
                                    .setSmallIcon(R.drawable.notification)
                                    .setContentTitle("Critical balance")
                                    .setContentText("Your balance is very low");


                    // Add as notification
                    NotificationManager manager = (NotificationManager) getSystemService(getApplicationContext().NOTIFICATION_SERVICE);
                    manager.notify(0, builder.build());


                }
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }


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
        nm = (TextView) findViewById(R.id.name1);
        em = (TextView) findViewById(R.id.em);
        uname = getIntent().getExtras().getString("username");
        uemail = getIntent().getExtras().getString("email");
        nm.setText(uname);
        em.setText(uemail);
        return true;
    }

    public void viewuprofile(View view) {
        Intent my = new Intent(UserProfile.this, myprofile.class);
        my.putExtra("name", uname);
        my.putExtra("email", uemail);
        startActivity(my);
    }

    public void addincome(View view) {
        Intent my = new Intent(UserProfile.this, adduincome.class);
        my.putExtra("uid", ui);
        startActivity(my);
    }

    public void viewcat(View view) {
        Intent my1 = new Intent(UserProfile.this, priority.class);
        my1.putExtra("uid", ui);
        startActivity(my1);
    }

    public void add(View view) {
        Intent my1 = new Intent(UserProfile.this, expense.class);
        my1.putExtra("uid", ui);
        startActivity(my1);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.list) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_settings) {
            Intent settingsIntent = new Intent(UserProfile.this, SettingsActivity.class);
            startActivity(settingsIntent);

        } else if (id == R.id.nav_project_report) {

            Intent projectReportIntent = new Intent(UserProfile.this, ProjectReportActivity.class);
            startActivity(projectReportIntent);


        } else if (id == R.id.nav_share) {
            final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
            }

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
