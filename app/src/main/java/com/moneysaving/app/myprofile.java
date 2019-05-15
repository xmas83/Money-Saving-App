package com.moneysaving.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class myprofile extends AppCompatActivity {
    TextView name1;
    TextView Email1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);
        name1=(TextView)findViewById(R.id.nId);
        Email1=(TextView)findViewById(R.id.email11);
        String nm=getIntent().getExtras().getString("name");
        String em=getIntent().getExtras().getString("email");
        name1.setText(nm);
        Email1.setText(em);
    }
}
