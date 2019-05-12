package com.moneysaving.app;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class activity_login_in extends AppCompatActivity {
    DBHelper dblist;
    Cursor c12;
    TextView tv1;
    String value;
    int curs;
    TextView login;
    String uname;
    String uemail;
    String upass;
    String em;
    String pas;
    Button logIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_in);

        dblist = new DBHelper(activity_login_in.this); //retreiveing data
        c12 = dblist.listRecord(); //calling the dbhelper function


    }

    public void loginNow(View view) {
        boolean check = false;
        em = ((EditText) findViewById(R.id.EmailId)).getText().toString();
        pas = ((EditText) findViewById(R.id.Password1)).getText().toString();
        if (em.equals("Admin")) {
            if (pas.equals("12345")) {
                Intent intent = new Intent(activity_login_in.this, AdminHomePageActivity.class);
                startActivity(intent);
            }
        } else {

//tv1.setText(upass);*/
            c12.moveToFirst();


            do {
                uname = c12.getString(2);
                uemail = c12.getString(3);
                upass = c12.getString(4);

                if (uemail.equals(em)) {
                    if (upass.equals(pas)) {
                // Account exists, return true if the password matches.
                        check = true;


                    }
                } else {
                    c12.moveToNext();
                }
            } while (c12 != null && !check);

            // When you click the log in button you proceed to the home page.
            if (check) {
                Intent I = new Intent(activity_login_in.this, HomePageActivity.class);
                I.putExtra("username", uname);
                I.putExtra("email", uemail);
                startActivity(I);
            } else {
                Toast.makeText(this, "Register First", Toast.LENGTH_SHORT).show();

            }

        }
    }
}


