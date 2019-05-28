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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {
    String name1;
    String email1;
    String value;
    String pass;
    Button Sign_up;
    TextView login;
    int uid;
    DBHelper dbHelper = new DBHelper(SignUp.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        login = (TextView) findViewById(R.id.login);
        uid = 60;

        //Sign_up=(Button)findViewById(R.id.email_sign_in_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, activity_login_in.class);
                startActivity(intent);

            }
        });

    }

    public void SignU(View view) {
        boolean check = false;
        boolean check1 = false;
        name1 = ((EditText) findViewById(R.id.name)).getText().toString();
        //username= ((EditText)findViewById(R.id.username)).getText().toString();
        email1 = ((EditText) findViewById(R.id.email)).getText().toString();
        pass = ((EditText) findViewById(R.id.password)).getText().toString();
        try {
            check1 = dbHelper.get_em(email1);
            //Toast.makeText(getApplicationContext(),"i am here",Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
        }
        // check1=dbHelper.get_em(email1);
        if (!email1.isEmpty() && !pass.isEmpty()) {
            check = isEmailValid(email1);
            if (check) {
                if (check1 == false) {
                    boolean passwordCheck = isPasswordValid(pass);
                    if (passwordCheck) {

                        long rownumber = dbHelper.insertUsersInDb(uid, name1, email1, pass);
                        uid += 1;
                        Toast.makeText(this, "Registration Successful, Member number: " + String.valueOf(rownumber), Toast.LENGTH_SHORT).show();
                        try {
                            Cursor c = dbHelper.get_em1(email1);
                            c.moveToFirst();
                            Intent I = new Intent(SignUp.this, UserProfile.class);
                            I.putExtra("useri", c.getString(0));
                            I.putExtra("username", name1);
                            I.putExtra("email", email1);
                            startActivity(I);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Password must be at least 8 characters, and must contain at least one letter and at least one number and special character.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Email already exists", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Email is invalid", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Enter required fields", Toast.LENGTH_SHORT).show();
        }


    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    public static boolean isPasswordValid(String pass) {
        String expression = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(pass);
        return matcher.matches();
    }
}

