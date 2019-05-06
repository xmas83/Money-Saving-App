package com.moneysaving.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    String name1;
    String email1;
    String value;
    String pass;
    Button Sign_up;
    TextView login;
    int uid;
    DBHelper dbHelper=new DBHelper(SignUp.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        login=(TextView)findViewById(R.id.login);
        uid=60;

        //Sign_up=(Button)findViewById(R.id.email_sign_in_button);
       login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignUp.this,activity_login_in.class);
                startActivity(intent);

            }
        });

    }
    public void SignU(View view){
        name1= ((EditText)findViewById(R.id.name)).getText().toString();
        //username= ((EditText)findViewById(R.id.username)).getText().toString();
        email1= ((EditText)findViewById(R.id.email)).getText().toString();
        pass= ((EditText)findViewById(R.id.password)).getText().toString();
        long rownumber= dbHelper.insertUsersInDb(uid,name1,email1,pass);
        uid+=1;
        Toast.makeText(this,"Registration Successful, Member number: "+String.valueOf(rownumber),Toast.LENGTH_SHORT).show();
       // Intent intent=new Intent(SignUp.this,Adminpanel.class);
        //startActivity(intent);

    }
}


