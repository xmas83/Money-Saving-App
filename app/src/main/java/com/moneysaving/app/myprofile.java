package com.moneysaving.app;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class myprofile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView name11;
    TextView Email1;
    TextView em1, nm1;
    String nm, em;
    ImageView image;
    String tid;
    DBHelper dbHelper = new DBHelper(myprofile.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);
        image = findViewById(R.id.imageView2);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar5);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Profile");
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


        name11 = findViewById(R.id.nId);
        Email1 = findViewById(R.id.email11);
        tid = getIntent().getExtras().getString("uid");
        nm = getIntent().getExtras().getString("name");
        em = getIntent().getExtras().getString("email");
        name11.setText(nm);
        Email1.setText(em);


    }

    public void uploadimg(View view) {

        Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 2) {
            try {
                final Uri imageUri = data.getData();
                getContentResolver().takePersistableUriPermission(imageUri, Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                long im = dbHelper.insertImgDb(Integer.valueOf(tid), imageUri.toString());
                showSnackBar("Photo changed Successfully");
            } catch (Exception e) {
                e.printStackTrace();
                showSnackBar("Something went wrong");
            }

        } else {
            showSnackBar("Please pick an image");
        }
    }

    @Override
    protected void onStart() {
        try {
            Cursor c = dbHelper.get_img(Integer.valueOf(tid));
            c.moveToFirst();
            String temp = c.getString(0);
            Uri selectedImage = Uri.parse(temp);
            image.setImageURI(selectedImage);


        } catch (Exception ex) {
            ex.printStackTrace();
        }
        super.onStart();
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
            Intent I = new Intent(myprofile.this, UserProfile.class);
            I.putExtra("useri", tid);
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
            Intent I = new Intent(myprofile.this, SetthingsActivity.class);
            startActivity(I);

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
