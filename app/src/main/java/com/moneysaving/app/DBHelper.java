package com.moneysaving.app;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {
    String value;

    public DBHelper(Context context) {
        super(context, "myDb", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
       try {

            db.execSQL("CREATE TABLE `userprofile` (  `_id`	INTEGER PRIMARY KEY AUTOINCREMENT,userid  INTEGER,name	TEXT,  email	TEXT, password TEXT)");
            db.execSQL("insert into userprofile(name,email,password) values(55,'Book1','Reading','pass')");
            db.execSQL("CREATE TABLE `expenses` (  `_id`	INTEGER PRIMARY KEY AUTOINCREMENT,userid INTEGER,amount  TEXT)");
            db.execSQL("CREATE TABLE `income` (  `_id`	INTEGER PRIMARY KEY AUTOINCREMENT,userid INTEGER,amount	TEXT,description TEXT)");
            db.execSQL("CREATE TABLE `category` (  `_id`	INTEGER PRIMARY KEY AUTOINCREMENT,userid INTEGER,name TEXT,priorityno INTEGER)");
            db.execSQL("CREATE TABLE `notification` (  `_id`	INTEGER PRIMARY KEY AUTOINCREMENT,userid INTEGER,title	TEXT,description TEXT)");

           //db.execSQL("CREATE TABLE if not exists `profile` (`_id` INTEGER PRIMARY KEY AUTOINCREMENT,`name` INTEGER,`email` TEXT,`password` INTEGER )");
        }
        catch (Exception ex)
        {
            Log.i("Error","Create Table");
            ex.printStackTrace();
        }
    }



    public long  insertUsersInDb(int uid,String name1, String email1,String pass)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
       long rowinserted=0;
        try {
            cv.put("userid",uid);
            cv.put("name", name1);
            cv.put("email", email1);
            cv.put("password", pass);
            rowinserted = db.insert("userprofile", null, cv);
            //Toast.makeText(DBHelper.this,"data inserted in row:"+String.valueOf(rowinserted),Toast.LENGTH_SHORT)).show();

      }
        catch (Exception ex)
      {

            ex.printStackTrace();
       }
        db.close();
        return rowinserted;

    }


    public Cursor listRecord()
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c1=null;
        try {
            c1 = db.rawQuery("select * from userprofile", null);


        }
        catch(Exception ex)
        {
            Log.i("Error","Data Showing");
            ex.printStackTrace();
        }
        return c1;

    }
    public long  insertexpensesInDb(int uid,String amount1) // Insert data in expenses table
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        long rowinserted=0;
        try {
            cv.put("userid",uid);
            cv.put("amount", amount1);

            rowinserted = db.insert("expenses", null, cv);


        }
        catch (Exception ex)
        {

            ex.printStackTrace();
        }
        db.close();
        return rowinserted;

    }
    public long  insertincomeInDb(int uid,String amount1,String desc) // Insert data in income table
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        long rowinserted=0;
        try {
            cv.put("userid",uid);
            cv.put("amount", amount1);
            cv.put("description",desc);

            rowinserted = db.insert("income", null, cv);

        }
        catch (Exception ex)
        {

            ex.printStackTrace();
        }
        db.close();
        return rowinserted;

    }
    public long  insertCategoryInDb(int uid,String nam,int priorityn) // Insert data in category table
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        long rowinserted=0;
        try {
            cv.put("userid",uid);
            cv.put("name", nam);
            cv.put("priorityno",priorityn);

            rowinserted = db.insert("income", null, cv);

        }
        catch (Exception ex)
        {

            ex.printStackTrace();
        }
        db.close();
        return rowinserted;

    }

    public long  insertnotificationInDb(int uid,String title1,String desc)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        long rowinserted=0;
        try {
            cv.put("userid",uid);
            cv.put("title", title1);
            cv.put("description",desc);

            rowinserted = db.insert("income", null, cv);

        }
        catch (Exception ex)
        {

            ex.printStackTrace();
        }
        db.close();
        return rowinserted;

    }
    public Cursor get_expenses(int uid)
    {
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from expenses where userid = "+ uid+" ",null);
        return cursor;
    }
    public Cursor get_income(int uid)
    {
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from income where userid = "+ uid+" ",null);
        return cursor;
    }
    public Cursor get_category(int uid)
    {
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from category where userid = "+ uid+" ",null);
        return cursor;
    }
    public Cursor get_notification(int uid)
    {
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from notification where userid = "+ uid+" ",null);
        return cursor;
    }
    public  Cursor getdata(int i){
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from userprofile where _id = "+ i+" ",null);
        return cursor;

    }
    public void updatePass(String ps,  int i) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("password", ps);
        String where =  "_id" + "=" + i;
        db.update("userprofile", values, where, null);
        db.close();
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
