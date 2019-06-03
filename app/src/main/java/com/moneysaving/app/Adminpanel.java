package com.moneysaving.app;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Adminpanel extends AppCompatActivity {
    ListView List;
    DBHelper dbHelper;
    Cursor c1;
    String pass;
    String em;
    String name;
    ArrayList<String> itemname=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpanel);
       List=(ListView)findViewById(R.id.list);
       dbHelper=new DBHelper(Adminpanel.this);
        c1=dbHelper.listRecord();
        c1.moveToFirst();
        do{
            itemname.add(c1.getString(2));
        }while (c1.moveToNext() && c1 !=null);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, itemname);
        List.setAdapter(adapter);
        List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                Cursor c2=dbHelper.getData(i+1);
                c2.moveToFirst();
                String id1=c2.getString(0);
                String nm=c2.getString(2);
                String em=c2.getString(3);
                String pas=c2.getString(4);
                Toast.makeText(Adminpanel.this,nm+em+pas+id1,Toast.LENGTH_SHORT).show();
               Intent I=new Intent(Adminpanel.this,UserDetails.class);
                I.putExtra("uid",id1);
                I.putExtra("nam",nm);
                I.putExtra("email1",em);
                I.putExtra("pass1",pas);
               startActivity(I);


            }
        });
    }
}
