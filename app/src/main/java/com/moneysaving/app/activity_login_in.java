package com.moneysaving.app;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    String uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_in);

        dblist = new DBHelper(activity_login_in.this); //retreiveing data
        c12 = dblist.listRecord(); //calling the dbhelper function


    }

    public void loginNow(View view) {
        boolean check = false;
        boolean check1=false;
        boolean check2=false;
        em = ((EditText) findViewById(R.id.EmailId)).getText().toString();
        pas = ((EditText) findViewById(R.id.Password1)).getText().toString();

        if (!em.isEmpty() && !pas.isEmpty()) {

            if (em.equals("Admin") || em.equals("admin")) {
                if (pas.equals("12345")) {
                    Intent intent = new Intent(activity_login_in.this, Adminpanel.class);
                    startActivity(intent);
                }
            } else {


//tv1.setText(upass);*/
                check1=isEmailValid(em);
                c12.moveToFirst();
                 if(check1) {
                  try {
                      do {
                          uid = c12.getString(0);
                          uname = c12.getString(2);
                          uemail = c12.getString(3);
                          upass = c12.getString(4);
                          try {
                          if ((uemail.equals(em)) &&  (upass.equals(pas))) {

                                 // if (upass.equals(pas)) {
// Account exists, return true if the password matches.
                                      check = true;


                                 // }

                          } else {


                                  c12.moveToNext();

                          }
                          }catch (Exception ex){
                              ex.printStackTrace();
                          }
                      } while (c12 != null && !check);
                  }catch (Exception ex){
                      ex.printStackTrace();
                  }


                     if (check) {
                         Intent I = new Intent(activity_login_in.this, UserProfile.class);
                         I.putExtra("useri", uid);
                         I.putExtra("username", uname);
                         I.putExtra("email", uemail);
                         startActivity(I);
                     } else
                         {
                         Toast.makeText(this, "Register First/Email or password is wrong", Toast.LENGTH_SHORT).show();

                     }
                 }else{
                     Toast.makeText(this, "Email format is wrong", Toast.LENGTH_SHORT).show();
                 }

            }
        }
        else{
            Toast.makeText(this, "Enter in fields first", Toast.LENGTH_SHORT).show();
        }
    }
    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}


