package com.moneysaving.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserDetails extends AppCompatActivity {
    DBHelper dbHelper;
    TextView nm;
    TextView em;
    TextView pasword;
    EditText pas;
    String email;
    Button change;
    String ps;
    String pass;
    String id2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        change=(Button)findViewById(R.id.changepass);
        dbHelper=new DBHelper(UserDetails.this);
        nm=((TextView)findViewById(R.id.nm1));
        em=(TextView)findViewById(R.id.em1);
        pas=(EditText)findViewById(R.id.ps1);
        pasword=(TextView)findViewById(R.id.pas1);
        id2=getIntent().getExtras().getString("uid");
        String name=getIntent().getExtras().getString("nam");
        email=getIntent().getExtras().getString("email1");
        pass=getIntent().getExtras().getString("pass1");
        nm.setText(name);
        em.setText(email);
        pasword.setText(pass);


    }
    public void update(View view){


            ps=pas.getText().toString();
            dbHelper.updatePass(ps,Integer.valueOf(id2));
          // Toast.makeText(UserDetails.this,"Password Changed",Toast.LENGTH_SHORT).show();



    }
}
