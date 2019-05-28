package com.moneysaving.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class expense extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] category = { "Food", "Entertainment", "Car", "Home", "Clothing","Electronics","Health","Children","Work","Other"};
    EditText amnt,descr;
    String i;
    String temp;
    DBHelper dbHelper=new DBHelper(expense.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        amnt=(EditText)findViewById(R.id.am) ;
        descr=(EditText)findViewById(R.id.des);
        i=getIntent().getExtras().getString("uid");

        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,category);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getApplicationContext(),category[i] , Toast.LENGTH_LONG).show();
        temp=category[i];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {



    }
    public void adduserexpense(View view){
        String am1=amnt.getText().toString();
        String ds1=descr.getText().toString();
        DateFormat dateFormat = new SimpleDateFormat("MM");
        Date date = new Date();
        if(!am1.isEmpty() && !ds1.isEmpty()) {
            String d = dateFormat.format(date);
            long t = dbHelper.insertexpensesInDb(Integer.valueOf(i), am1, ds1, temp, Integer.valueOf(d));
            Toast.makeText(getApplicationContext(), String.valueOf(t), Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Enter Value first", Toast.LENGTH_SHORT).show();
        }

    }
}
