package com.moneysaving.app;

import android.app.Dialog;
import android.app.NotificationManager;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

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
    Dialog addExpense;
    String i;
    String temp;
    String[] category = {"Food", "Entertainment", "Car", "Home", "Clothing", "Electronics", "Health", "Children", "Work", "Other"};
    EditText amnt, descr;
    DBHelper dbHelper;
    Dialog addIncome;
    EditText amount;
    EditText Desc;
    TextView date;
    String date1;
    Button addincomenow;
    Button addNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile2);
        ui = getIntent().getExtras().getString("useri");

        dbHelper = new DBHelper(this);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);


    }


    @Override
    protected void onStart() {
        super.onStart();

        textView1 = findViewById(R.id.aadactivityText);
        textView = findViewById(R.id.aaduincome);

        try {
            DateFormat dateFormat = new SimpleDateFormat("MM");
            Date date = new Date();
            d = dateFormat.format(date);


            Cursor cursor1 = dblist1.get_income(Integer.valueOf(ui), Integer.valueOf(d));
            cursor1.moveToFirst();
            inc = cursor1.getString(0);

            inc = cursor1.getString(0);
            textView.setText("Balance:" + inc);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {

            Cursor cursor = dblist1.sum_expense(Integer.valueOf(ui), Integer.valueOf(d));
            cursor.moveToFirst();
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

            if (!exp.isEmpty() && !inc.isEmpty()) {
                int a = Integer.valueOf(inc) - Integer.valueOf(exp);
                textView.setText("Balance:" + a);
                if (a < 800) {
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
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        nm = findViewById(R.id.name1);
        em = findViewById(R.id.em);
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
        my.putExtra("uid", ui);
        startActivity(my);
    }

    public void addincome(View view) {

        addIncome = new Dialog(this);
        addIncome.requestWindowFeature(Window.FEATURE_NO_TITLE);
        addIncome.setContentView(R.layout.addincomedialogue);
        Objects.requireNonNull(addIncome.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        addIncome.setCancelable(true);
        addIncome.show();

        amount = addIncome.findViewById(R.id.Am);
        Desc = addIncome.findViewById(R.id.dsc);
        date1 = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        date = addIncome.findViewById(R.id.date);
        date.setText(date1);
        addincomenow = addIncome.findViewById(R.id.addinc);
        addincomenow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = amount.getText().toString();
                String b = Desc.getText().toString();
                String[] arr = date1.split("-");
                String c = arr[1];
                if (!a.isEmpty() && !b.isEmpty()) {
                    if (Integer.valueOf(a) < 800) {
                        showSnackBar("Amount must be greater than 800");
                    } else {
                        long ab = dbHelper.insertIncome(Integer.valueOf(ui), a, b, Integer.valueOf(c));
                        if (ab > 1) {
                            showSnackBar("Income Added Successfully");
                            if (addIncome != null)
                                if (addIncome.isShowing())
                                    addIncome.dismiss();
                            onStart();
                        }

                    }
                } else
                    showSnackBar("All Fields are Required");
            }
        });

    }

    public void viewcat(View view) {
        Intent my1 = new Intent(UserProfile.this, CategoriesList.class);
        my1.putExtra("name", uname);
        my1.putExtra("email", uemail);
        my1.putExtra("uid", ui);
        startActivity(my1);
    }

    public void add(View view) {
        addExpense = new Dialog(this);
        addExpense.requestWindowFeature(Window.FEATURE_NO_TITLE);
        addExpense.setContentView(R.layout.addexpensenewdialogue);
        Objects.requireNonNull(addExpense.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        addExpense.setCancelable(true);

        String nm, em;
        amnt = addExpense.findViewById(R.id.am);
        descr = addExpense.findViewById(R.id.des);
        addNow = addExpense.findViewById(R.id.addExpenseNow);
        Spinner spin = addExpense.findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                temp = category[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, category);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

        addNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String am1 = amnt.getText().toString();
                String ds1 = descr.getText().toString();
                DateFormat dateFormat = new SimpleDateFormat("MM");
                Date date = new Date();
                if (!am1.isEmpty() && !ds1.isEmpty()) {
                    String d = dateFormat.format(date);
                    long t = dbHelper.insertExpensesInDb(Integer.valueOf(ui), am1, ds1, temp, Integer.valueOf(d));
                    if (t > 1)
                        showSnackBar("Expense has been added");
                    if (addExpense != null)
                        if (addExpense.isShowing())
                            addExpense.dismiss();
                    onStart();
                } else {
                    showSnackBar("Alll fields are required.");
                }
            }
        });
        addExpense.show();

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.list) {
            // Handle the camera action
        } else if (id == R.id.nav_Home) {

            try {
                nm.setText(uname);
                em.setText(uemail);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else if (id == R.id.ourstory) {
            showOurStory();
        } else if (id == R.id.rateus) {
            final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
            }

        } else if (id == R.id.nav_setting) {
            Intent I = new Intent(UserProfile.this, SetthingsActivity.class);
            startActivity(I);

        } else if (id == R.id.aboutus) {
            showOurStory();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showSnackBar(String show) {
        Snackbar snackbar;
        snackbar = Snackbar.make(findViewById(android.R.id.content), show, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(getResources().getColor(android.R.color.white));

        snackbar.setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        })
                .setActionTextColor(getResources().getColor(android.R.color.white))
                .show();
    }

    void showOurStory() {
        final Dialog ourstory = new Dialog(this);
        ourstory.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ourstory.setContentView(R.layout.ourstory);
        Objects.requireNonNull(ourstory.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ourstory.setCancelable(true);
        Button close = ourstory.findViewById(R.id.closeit);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ourstory.isShowing())
                    ourstory.dismiss();
            }
        });
        ourstory.show();
    }
}
