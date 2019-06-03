package com.moneysaving.app;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

public class CategoriesList extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ArrayList<String> categoriesNames = new ArrayList<>();
    String usid;
    static String Slecteditem;
    String nm, em;
    RecyclerView categoriesListView;
    RecyclerAdapterForCategories adapterForCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priority);

        nm = getIntent().getExtras().getString("name");
        em = getIntent().getExtras().getString("email");
        usid = getIntent().getExtras().getString("uid");

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar6);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add Income");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);


        categoriesNames.add("Food");
        categoriesNames.add("Entertainment");
        categoriesNames.add("Car");
        categoriesNames.add("Home");
        categoriesNames.add("Clothing");
        categoriesNames.add("Electronics");
        categoriesNames.add("Health");
        categoriesNames.add("Children");
        categoriesNames.add("Work");
        categoriesNames.add("Other");


        categoriesListView = findViewById(R.id.categories);
        adapterForCategories = new RecyclerAdapterForCategories(CategoriesList.this, categoriesNames, usid, categoriesListView);
        categoriesListView.setAdapter(adapterForCategories);
        categoriesListView.setLayoutManager(new LinearLayoutManager(this));
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_profile, menu);
        TextView nm1 = findViewById(R.id.name1);
        TextView em1 = findViewById(R.id.em);
        nm1.setText(nm);
        em1.setText(em);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.list) {
            // Handle the camera action
        } else if (id == R.id.nav_Home) {
            Intent I = new Intent(CategoriesList.this, UserProfile.class);
            I.putExtra("useri", usid);
            I.putExtra("username", nm);
            I.putExtra("email", em);
            startActivity(I);

        } else if (id == R.id.ourstory) {
            showOurStory();
        } else if (id == R.id.aboutus)
            showOurStory();
        else if (id == R.id.rateus) {

            final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
            }

        } else if (id == R.id.nav_setting) {
            Intent I = new Intent(CategoriesList.this, SetthingsActivity.class);
            startActivity(I);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
