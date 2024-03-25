package com.example.get_a_job;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper_user extends SQLiteOpenHelper {
    public DBHelper_user(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE users (user_id INTEGER PRIMARY KEY AUTOINCREMENT,email TEXT UNIQUE, firstname TEXT, lastname TEXT,password TEXT)";
        db.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        return;
    }

    public long add_user(String firstname , String lastname ,String email ,String password ){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("firstname",firstname);
        contentValues.put("lastname",lastname);
        contentValues.put("email",email);
        contentValues.put("password",password);

        return  db.insert("users",null,contentValues);

    }
    public boolean check_login(String user_email, String user_password) {
        Log.d("test", "Hello ji");
        SQLiteDatabase db = getReadableDatabase();

        // Corrected query to use parameters safely
        String query = "SELECT password FROM users WHERE email=?";
        Log.d("test", "Hello 2");

        Cursor cursor = db.rawQuery(query, new String[]{user_email});
        Log.d("test", "Hello 3");

        String result = null;
        if (cursor.moveToFirst()) {
            // Retrieve password from the cursor
            result = cursor.getString(0);
        }

        cursor.close(); // closing the cursor after using it

        Log.d("test", "Hello 4");

        // Check if result is not null before comparing
        if (result != null && result.equals(user_password)) {
            Log.d("test", "Hello 5- SUccess login");
            return true;
        }
        Log.d("test", "Hello 6- Failure login");
        return false;
    }


    public Cursor displayData(){

        SQLiteDatabase db= getReadableDatabase();

        return db.rawQuery("SELECT * FROM users",null);
    }
    public long deleteData(String email){

        SQLiteDatabase db= getWritableDatabase();


        return db.delete("users","email=?",new String[]{email});
    }

    public long editValue(String email,String firstname,String lastname,String password){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("firstname",firstname);
        contentValues.put("lastname",lastname);
        contentValues.put("password",password);

        return db.update("users",contentValues,"email=?",new String[]{email});

    }


}
