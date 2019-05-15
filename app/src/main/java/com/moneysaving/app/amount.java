package com.moneysaving.app;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class amount extends AppCompatActivity {
    DBHelper dbHelper = new DBHelper(amount.this);
    EditText editText;
    String uid, sitem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amount);
        editText = (EditText) findViewById(R.id.enteramount);
        uid = getIntent().getExtras().getString("id");
        sitem = getIntent().getExtras().getString("item");
    }

    public void enteramount(View view) {
        DateFormat dateFormat = new SimpleDateFormat("MM");
        Date date = new Date();
        String d = dateFormat.format(date);
        String temp = editText.getText().toString();


        if ((!temp.isEmpty())) {
            long a = dbHelper.insertCategoryInDb(Integer.valueOf(uid), sitem, temp, Integer.valueOf(d));
            Toast.makeText(getApplicationContext(), String.valueOf(a), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Enter required field/amount is greater than income", Toast.LENGTH_SHORT).show();
        }

    }
}
