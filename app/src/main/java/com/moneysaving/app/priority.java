package com.moneysaving.app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class priority extends Activity {
    DBHelper dbHelper = new DBHelper(priority.this);
    ListView list;
    ArrayList<String> itemname = new ArrayList<>();
    String usid;
    static String Slecteditem;
    TextView textView, textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9;
    String a, b;
    String fd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priority);
        fd = null;
        usid = getIntent().getExtras().getString("uid");
        itemname.add("Food");
        itemname.add("Entertainment");
        itemname.add("Car");
        itemname.add("Home");
        itemname.add("Clothing");
        itemname.add("Electronics");
        itemname.add("Health");
        itemname.add("Children");
        itemname.add("Work");
        itemname.add("Other");

        Integer[] imgid = {
                R.drawable.list


        };


        CustomListAdapter adapter = new CustomListAdapter(this, itemname, imgid);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, final long id) {
                // TODO Auto-generated method stub
                Slecteditem = itemname.get(+position);
                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();


                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("DATA", Context.MODE_PRIVATE);
                sharedPreferences.edit().putString("STRENGTH", Slecteditem).apply();
                Intent I = new Intent(priority.this, amount.class);
                I.putExtra("item", Slecteditem);
                I.putExtra("id", usid);
                startActivity(I);


            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("DATA", Context.MODE_PRIVATE);
        String strength = sharedPreferences.getString("STRENGTH", null);
        Toast.makeText(getApplicationContext(), strength, Toast.LENGTH_SHORT).show();
        DateFormat dateFormat = new SimpleDateFormat("MM");
        Date date = new Date();
        String d = dateFormat.format(date);
        textView = (TextView) findViewById(R.id.food);
        textView1 = (TextView) findViewById(R.id.entertainment);
        textView2 = (TextView) findViewById(R.id.car);
        textView3 = (TextView) findViewById(R.id.home);
        textView4 = (TextView) findViewById(R.id.clothing);
        textView5 = (TextView) findViewById(R.id.electronics);
        textView6 = (TextView) findViewById(R.id.healthbeauty);
        textView7 = (TextView) findViewById(R.id.children);
        textView8 = (TextView) findViewById(R.id.work);
        textView9 = (TextView) findViewById(R.id.other);


        try {
            Cursor cursor = dbHelper.get_category(Integer.valueOf(usid), "Food", Integer.valueOf(d));
            cursor.moveToFirst();
            textView.setText(cursor.getString(0));
            try {
                Cursor c1 = dbHelper.get_ex(Integer.valueOf(usid), "Food", Integer.valueOf(d));
                c1.moveToFirst();
                if (Integer.valueOf(c1.getString(0)) > Integer.valueOf(cursor.getString(0))) {


                    textView.setText("Amount consumed");
                } else {

                    int t = Integer.valueOf(cursor.getString(0)) - Integer.valueOf(c1.getString(0));
                    //if(t>Integer.valueOf(c1.getString(0))) {
                    textView.setText(String.valueOf(t));
                    //}
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            Cursor cursor2 = dbHelper.get_category(Integer.valueOf(usid), "Entertainment", Integer.valueOf(d));
            cursor2.moveToFirst();
            textView1.setText(cursor2.getString(0));
            try {
                Cursor c2 = dbHelper.get_ex(Integer.valueOf(usid), "Entertainment", Integer.valueOf(d));
                c2.moveToFirst();
                if (Integer.valueOf(c2.getString(0)) > Integer.valueOf(cursor2.getString(0))) {

                    textView1.setText("Amount consumed");

                } else {
                    int t1 = Integer.valueOf(cursor2.getString(0)) - Integer.valueOf(c2.getString(0));
                    //if(t1>Integer.valueOf(c2.getString(0))) {
                    textView1.setText(String.valueOf(t1));
                    // }

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            Cursor cursor3 = dbHelper.get_category(Integer.valueOf(usid), "Car", Integer.valueOf(d));
            cursor3.moveToFirst();
            textView2.setText(cursor3.getString(0));
            try {
                Cursor c3 = dbHelper.get_ex(Integer.valueOf(usid), "Car", Integer.valueOf(d));
                c3.moveToFirst();
                if (Integer.valueOf(c3.getString(0)) > Integer.valueOf(cursor3.getString(0))) {
                    textView2.setText("Amount consumed");
                } else {


                    int t2 = Integer.valueOf(cursor3.getString(0)) - Integer.valueOf(c3.getString(0));
                    // if(t2>Integer.valueOf(c3.getString(0))) {
                    textView2.setText(String.valueOf(t2));
                    //  }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            Cursor cursor4 = dbHelper.get_category(Integer.valueOf(usid), "Home", Integer.valueOf(d));
            cursor4.moveToFirst();
            textView3.setText(cursor4.getString(0));
            try {
                Cursor c4 = dbHelper.get_ex(Integer.valueOf(usid), "Home", Integer.valueOf(d));
                c4.moveToFirst();
                if (Integer.valueOf(c4.getString(0)) > Integer.valueOf(cursor4.getString(0))) {
                    textView3.setText("Amount Consumed");
                } else {
                    int t3 = Integer.valueOf(cursor4.getString(0)) - Integer.valueOf(c4.getString(0));
                    // if(t3>Integer.valueOf(c4.getString(0))) {
                    textView3.setText(String.valueOf(t3));
                    //}

                    // Toast.makeText(getApplicationContext(),"you are spending more than amount assigned",Toast.LENGTH_SHORT).show();

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            Cursor cursor5 = dbHelper.get_category(Integer.valueOf(usid), "Clothing", Integer.valueOf(d));
            cursor5.moveToFirst();
            textView4.setText(cursor5.getString(0));
            try {
                Cursor c5 = dbHelper.get_ex(Integer.valueOf(usid), "Clothing", Integer.valueOf(d));
                c5.moveToFirst();
                if (Integer.valueOf(c5.getString(0)) > Integer.valueOf(cursor5.getString(0))) {
                    int t4 = Integer.valueOf(cursor5.getString(0)) - Integer.valueOf(c5.getString(0));
                    //if(t4>Integer.valueOf(c5.getString(0))) {
                    //textView4.setText(String.valueOf(t4));
                    // }
                    textView4.setText("Amount consumed");

                } else {
                    //Toast.makeText(getApplicationContext(),"you are spending more than amount assigned",Toast.LENGTH_SHORT).show();
                    int t4 = Integer.valueOf(cursor5.getString(0)) - Integer.valueOf(c5.getString(0));
                    textView4.setText(String.valueOf(t4));
                    // textView4.setText("Amount consumed");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            Cursor cursor6 = dbHelper.get_category(Integer.valueOf(usid), "Electronics", Integer.valueOf(d));
            cursor6.moveToFirst();
            textView5.setText(cursor6.getString(0));
            try {
                Cursor c6 = dbHelper.get_ex(Integer.valueOf(usid), "Electronics", Integer.valueOf(d));
                c6.moveToFirst();
                if (Integer.valueOf(c6.getString(0)) > Integer.valueOf(cursor6.getString(0))) {
                    //int t5 = Integer.valueOf(cursor6.getString(0)) - Integer.valueOf(c6.getString(0));
                    // if(t5>Integer.valueOf(c6.getString(0))) {
                    textView5.setText(String.valueOf("Amount Consumed"));
                    // }

                } else {
                    //Toast.makeText(getApplicationContext(),"you are spending more than amount assigned",Toast.LENGTH_SHORT).show();
                    int t5 = Integer.valueOf(cursor6.getString(0)) - Integer.valueOf(c6.getString(0));
                    textView5.setText(String.valueOf(t5));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            Cursor cursor7 = dbHelper.get_category(Integer.valueOf(usid), "Health", Integer.valueOf(d));
            cursor7.moveToFirst();
            textView6.setText(cursor7.getString(0));
            try {
                Cursor c7 = dbHelper.get_ex(Integer.valueOf(usid), "Heatlh", Integer.valueOf(d));
                c7.moveToFirst();
                if (Integer.valueOf(c7.getString(0)) > Integer.valueOf(cursor7.getString(0))) {
                    // int t6 = Integer.valueOf(cursor7.getString(0)) - Integer.valueOf(c7.getString(0));
                    //if(t6>Integer.valueOf(c7.getString(0))) {
                    textView6.setText("Amount Consumed");
                    //}
                    //else
                    //{
                    //Toast.makeText(getApplicationContext(),"you are spending more than amount assigned",Toast.LENGTH_SHORT).show();
                    //textView6.setText("Amount consumed");
                    //}
                } else {
                    //Toast.makeText(getApplicationContext(),"you are spending more than amount assigned",Toast.LENGTH_SHORT).show();
                    int t6 = Integer.valueOf(cursor7.getString(0)) - Integer.valueOf(c7.getString(0));
                    textView6.setText(String.valueOf(t6));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            Cursor cursor8 = dbHelper.get_category(Integer.valueOf(usid), "Children", Integer.valueOf(d));
            cursor8.moveToFirst();
            textView7.setText(cursor8.getString(0));
            try {
                Cursor c8 = dbHelper.get_ex(Integer.valueOf(usid), "Children", Integer.valueOf(d));
                c8.moveToFirst();
                if (Integer.valueOf(c8.getString(0)) > Integer.valueOf(cursor8.getString(0))) {
                    int t7 = Integer.valueOf(cursor8.getString(0)) - Integer.valueOf(c8.getString(0));
                    //if(t7>Integer.valueOf(c8.getString(0))) {
                    textView7.setText("Amount Comsumed");
                    // }
                    // else
                    // {

                    // }
                } else {
                    int t7 = Integer.valueOf(cursor8.getString(0)) - Integer.valueOf(c8.getString(0));
                    textView7.setText(String.valueOf(t7));

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            Cursor cursor9 = dbHelper.get_category(Integer.valueOf(usid), "Work", Integer.valueOf(d));
            cursor9.moveToFirst();
            textView8.setText(cursor9.getString(0));
            try {
                Cursor c9 = dbHelper.get_ex(Integer.valueOf(usid), "Work", Integer.valueOf(d));
                c9.moveToFirst();
                if (Integer.valueOf(c9.getString(0)) > Integer.valueOf(cursor9.getString(0))) {
                    // int t8 = Integer.valueOf(cursor9.getString(0)) - Integer.valueOf(c9.getString(0));
                    // if(t8>Integer.valueOf(c9.getString(0))) {
                    textView8.setText("Amount Consumed");
                    //}

                } else {
                    int t8 = Integer.valueOf(cursor9.getString(0)) - Integer.valueOf(c9.getString(0));
                    textView8.setText(String.valueOf(t8));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            Cursor cursor10 = dbHelper.get_category(Integer.valueOf(usid), "Other", Integer.valueOf(d));
            cursor10.moveToFirst();
            textView9.setText(cursor10.getString(0));
            try {
                Cursor c10 = dbHelper.get_ex(Integer.valueOf(usid), "Other", Integer.valueOf(d));
                c10.moveToFirst();
                if (Integer.valueOf(c10.getString(0)) > Integer.valueOf(cursor10.getString(0))) {

                    textView9.setText("Amount Comsumed");


                } else {
                    int t9 = Integer.valueOf(cursor10.getString(0)) - Integer.valueOf(c10.getString(0));
                    textView9.setText(String.valueOf(t9));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }
}







