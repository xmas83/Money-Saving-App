package com.moneysaving.app;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class adduincome extends AppCompatActivity {
    DBHelper dbHelper=new DBHelper(adduincome.this);
    ArrayList<String> itemname=new ArrayList<>();
    EditText amount;
    EditText Desc;
    EditText date;
    boolean check;
    ListView List;
    int d;
    int m;
    int y;
    String id;
    DatePickerDialog datePickerDialog;
    TextView t;
    String date1;
    static int count=1;
    int monthAsInt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adduincome);
        //List=(ListView)findViewById(R.id.list1);
        monthAsInt = Calendar.getInstance().get(Calendar.MONTH);
        // dbHelper=new DBHelper(AddIncome.this);

        id=getIntent().getExtras().getString("uid");
        // initiate the date picker and a button

        amount=(EditText) findViewById(R.id.Am);
        Desc=(EditText) findViewById(R.id.dsc);
        date1 = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        date = (EditText) findViewById(R.id.date);
        date.setText(date1);

    }
    public void save(View view) {

        String a = amount.getText().toString();
        String b = Desc.getText().toString();
        String[] arr=date1.split("-");
        String c=arr[1];
        Toast.makeText(getApplicationContext(),arr[1],Toast.LENGTH_SHORT).show();
      //  if (count == 1) {
        if(!a.isEmpty() && !b.isEmpty()) {
            if(Integer.valueOf(a)<800){
                Toast.makeText(getApplicationContext(), "enter amount more than 800", Toast.LENGTH_SHORT).show();
            }
            else {
                long ab = dbHelper.insertincome(Integer.valueOf(id), a, b, Integer.valueOf(c));
                Toast.makeText(getApplicationContext(), String.valueOf(ab), Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "Enter the required Fields", Toast.LENGTH_SHORT).show();
        }


    }

}
