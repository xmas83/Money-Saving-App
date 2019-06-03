package com.moneysaving.app;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    DBHelper dblist;
    Cursor databaseCursor;
    String userName;
    String userEmail;
    String upass;
    String Email;
    String Password;
    String uid;
    RelativeLayout goToRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginnew);
        goToRegistration = findViewById(R.id.goToRegistration);
        goToRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                finish();
            }
        });
        dblist = new DBHelper(LoginActivity.this); //retreiveing data
        databaseCursor = dblist.listRecord(); //calling the dbhelper function


    }

    public void loginNow(View view) {
        boolean check = false;
        boolean check1 = false;
        boolean check2 = false;
        Email = ((EditText) findViewById(R.id.loginEmail)).getText().toString();
        Password = ((EditText) findViewById(R.id.loginPassword)).getText().toString();

        if (!Email.isEmpty() && !Password.isEmpty()) {

            if (Email.equals("Admin") || Email.equals("admin")) {
                if (Password.equals("12345")) {
                    showSnackBar("Successfully Signed In");
                    new CountDownTimer(2000, 1000) { //30000 milli seconds is total time, 1000 milli seconds is time interval

                        public void onTick(long millisUntilFinished) {
                        }

                        public void onFinish() {
                            try {
                                Intent intent = new Intent(LoginActivity.this, Adminpanel.class);
                                startActivity(intent);
                                finish();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                            finish();

                        }
                    }.start();

                }
            } else {


                check1 = isEmailValid(Email);
                databaseCursor.moveToFirst();
                if (check1) {
                    try {
                        do {
                            uid = databaseCursor.getString(0);
                            userName = databaseCursor.getString(2);
                            userEmail = databaseCursor.getString(3);
                            upass = databaseCursor.getString(4);
                            try {
                                if ((userEmail.equals(Email)) && (upass.equals(Password)))
                                    check = true;
                                else
                                    databaseCursor.moveToNext();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        } while (databaseCursor != null && !check);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    if (check) {
                        showSnackBar("Successfully Signed In");
                        new CountDownTimer(2000, 1000) { //30000 milli seconds is total time, 1000 milli seconds is time interval

                            public void onTick(long millisUntilFinished) {
                            }

                            public void onFinish() {
                                try {
                                    Intent I = new Intent(LoginActivity.this, UserProfile.class);
                                    I.putExtra("useri", uid);
                                    I.putExtra("username", userName);
                                    I.putExtra("email", userEmail);
                                    startActivity(I);
                                    finish();
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }

                            }
                        }.start();


                    } else {
                        showSnackBar("Email or Password Incorrect.");

                    }
                } else {
                    showSnackBar("Incorrect Format Incorrect");
                }

            }
        } else {
            showSnackBar("All Fields are Required");
        }
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
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

}


