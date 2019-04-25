package com.moneysaving.app.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.moneysaving.app.model.User;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * The type Database helper.
 */

public class SQLiteDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "SQLiteDatabaseHelper";
    private Context context;

    // Database Version
    private static int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "App.db";

    // User table name
    private static final String TABLE_USER = "userTable";

    // User Table Columns names
    private static final String COLUMN_USER_ACCOUNT_TYPE = "account_type";
    private static final String COLUMN_USER_FIRST_NAME = "first_name";
    private static final String COLUMN_USER_LAST_NAME = "last_name";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_PASSWORD = "user_password";
    private static final String COLUMN_USER_EMAIL_ADDRESS = "email_address";
    private static final String COLUMN_USER_DATE_CREATED = "date_created";


    // create table sql query for user table
    private String CREATE_USER_TABLE = "CREATE TABLE "
            + TABLE_USER + "("
            + COLUMN_USER_FIRST_NAME + " VARCHAR,"
            + COLUMN_USER_LAST_NAME + " VARCHAR,"
            + COLUMN_USER_NAME + " VARCHAR,"
            + COLUMN_USER_PASSWORD + " VARCHAR,"
            + COLUMN_USER_EMAIL_ADDRESS + " VARCHAR,"
            + COLUMN_USER_DATE_CREATED + " DATE " + ")";


    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;


    /**
     * Constructor
     *
     * @param context the context
     */
    public SQLiteDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);

        // Create tables again
        onCreate(db);

        DATABASE_VERSION = newVersion;


    }

    /**
     * This method is to create user record
     *
     * @param user the student
     */

    public boolean addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ACCOUNT_TYPE, user.getAccountType());
        values.put(COLUMN_USER_FIRST_NAME, user.getFirstName());
        values.put(COLUMN_USER_LAST_NAME, user.getLastName());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        values.put(COLUMN_USER_EMAIL_ADDRESS, user.getEmailAddress());
        values.put(COLUMN_USER_DATE_CREATED, new Timestamp(Calendar.getInstance().getTime().getTime()).toString());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        Log.e(TAG, "INSERTED INTO USER TABLE");
        Log.e(TAG, user.toString());
        db.close();


        return SQLiteDatabaseHelper.this.addUser(user);



    }
}
