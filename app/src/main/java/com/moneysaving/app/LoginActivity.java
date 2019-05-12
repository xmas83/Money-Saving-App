package com.moneysaving.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private EditText Username;
    private EditText Password;
    private TextView Attempts;
    private Button Login;
    private TextView Error;
    private TextView DontHaveAccount;
    private Button RegisterHere;
    private int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Username = (EditText)findViewById(R.id.usernameInput);
        Password = (EditText)findViewById(R.id.passwordInput);
        Attempts =(TextView)findViewById(R.id.showAttempts);
        Login =(Button)findViewById(R.id.loginBtn);
        Error = (TextView)findViewById(R.id.errorMsg);
        DontHaveAccount = (TextView)findViewById(R.id.accountText);
        RegisterHere = (Button)findViewById(R.id.registerHere);



        Attempts.setText("Number of Attempts remaining: 5");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Username.getText().toString(), Password.getText().toString());
            }
        });

    }
    private void validate (String userName, String userPassword) {
        if ((userName.equals("Admin")) && (userPassword.equals("12345"))) {
            Intent intent = new Intent(LoginActivity.this, AdminHomePageActivity.class);
            startActivity(intent);

        } else {
            counter--;

            Attempts.setText("Number of attempts remaining: " + String.valueOf(counter));

            Error.setText("Wrong username or password, try again");


            if (counter == 0) {
                Login.setEnabled(false);
            }
        }
    }
}

