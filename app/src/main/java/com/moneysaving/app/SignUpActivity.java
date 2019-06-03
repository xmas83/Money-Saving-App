package com.moneysaving.app;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {
    String name1;
    String email1;
    String value;
    String pass;
    Button Sign_up;
    TextView login;
    RelativeLayout goToLogin;
    int uid;
    DBHelper dbHelper = new DBHelper(SignUpActivity.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signupnew);

        uid = 60;
        goToLogin = findViewById(R.id.goToLogin);
        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


    }

    public void SignUpNow(View view) {
        boolean check = false;
        boolean check1 = false;
        name1 = ((EditText) findViewById(R.id.registerName)).getText().toString();
        email1 = ((EditText) findViewById(R.id.registerEmail)).getText().toString();
        pass = ((EditText) findViewById(R.id.registerPassword)).getText().toString();
        try {
            check1 = dbHelper.get_em(email1);

        } catch (Exception ex) {
        }
        if (!email1.isEmpty() && !pass.isEmpty()) {
            check = isEmailValid(email1);
            if (check) {
                if (check1 == false) {

                    long rownumber = dbHelper.insertUsersInDb(uid, name1, email1, pass);
                    uid += 1;
                    showSnackBar("Registration Successful!");
                    new CountDownTimer(2000, 1000) { //30000 milli seconds is total time, 1000 milli seconds is time interval

                        public void onTick(long millisUntilFinished) {
                        }

                        public void onFinish() {
                            try {
                                Cursor c = dbHelper.get_em1(email1);
                                c.moveToFirst();
                                Intent I = new Intent(SignUpActivity.this, UserProfile.class);
                                I.putExtra("useri", c.getString(0));
                                I.putExtra("username", name1);
                                I.putExtra("email", email1);
                                startActivity(I);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                            finish();

                        }
                    }.start();

                } else {
                    showSnackBar("Email already exists");

                }
            } else {
                showSnackBar("Email is invalid");

            }
        } else {
            showSnackBar("Enter required fields");

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


