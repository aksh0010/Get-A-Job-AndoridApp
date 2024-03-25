package com.example.get_a_job;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //creating users table
//        db.execSQL("DROP TABLE IF EXISTS users");
//        db.execSQL("DROP TABLE IF EXISTS jobs");
//        db.execSQL("DROP TABLE IF EXISTS applications");
        String createUserTableQuery = "CREATE TABLE users (user_id INTEGER PRIMARY KEY AUTOINCREMENT,email TEXT UNIQUE, firstname TEXT, lastname TEXT,password TEXT)";
        db.execSQL(createUserTableQuery);

        // Create jobs table
        // job_id,title,company,location,salary,date,description
        String createJobTableQuery = "CREATE TABLE jobs (job_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, company TEXT,location TEXT,salary TEXT,date_posted TIMESTAMP DEFAULT CURRENT_TIMESTAMP, description TEXT )";
        db.execSQL(createJobTableQuery);

        // Create applications table with foreign keys
        String createApplicationsTableQuery = "CREATE TABLE applications (application_id INTEGER PRIMARY KEY AUTOINCREMENT,applied_by_user INTEGER DEFAULT 0,saved_by_user INTEGER DEFAULT 0,date_applied TIMESTAMP DEFAULT CURRENT_TIMESTAMP , user_id INTEGER, job_id INTEGER, FOREIGN KEY(user_id) REFERENCES users(user_id), FOREIGN KEY(job_id) REFERENCES jobs(job_id))";
        db.execSQL(createApplicationsTableQuery);

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
    public long add_new_Job(String title, String description,String company, String location, String salary) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("title", title);
        contentValues.put("description", description);
        contentValues.put("location", location);
        contentValues.put("company", company);
        contentValues.put("company", company);
        contentValues.put("salary", salary);

        Log.d("test", "DATABASE adding title "+title);
        Log.d("test", "DATABASE adding comp "+company);
        Log.d("test", " DATABASE adding loc "+location);
//        Log.d("test", "DATABASE adding date "+date);
        return db.insert("jobs", null, contentValues);
    }
    // Method to set a job as saved by a user
    public long saveJob(String user_email, int job_id) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        int user_id = getUserIdByEmail(user_email); // Get user ID from email

        if (user_id != -1) { // If user exists
            contentValues.put("user_id", user_id);
            contentValues.put("job_id", job_id);
            contentValues.put("saved_by_user", 1); // 1 indicates the job is saved by the user

            return db.insert("applications", null, contentValues);
        }

        return -1; // Return -1 indicating failure
    } // Method to set a job as applied by a user
    public long applyJob(String user_email, int job_id) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        int user_id = getUserIdByEmail(user_email); // Get user ID from email

        if (user_id != -1) { // If user exists
            contentValues.put("user_id", user_id);
            contentValues.put("job_id", job_id);
            contentValues.put("applied_by_user", 1); // 1 indicates the job is applied by the user

            return db.insert("applications", null, contentValues);
        }

        return -1; // Return -1 indicating failure
    }

    // Method to get user ID by email
    private int getUserIdByEmail(String user_email) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT user_id FROM users WHERE email=?";
        Cursor cursor = db.rawQuery(query, new String[]{user_email});
        if (cursor.moveToFirst()) {
            int user_id = cursor.getInt(0);
            cursor.close();
            return user_id;
        }

        cursor.close();
        return -1; // Return -1 if user doesn't exist
    }

    // Method to get all saved jobs by a user
    public Cursor getSavedJobs(String user_email) {
        int user_id = getUserIdByEmail(user_email); // Get user ID from email

        if (user_id != -1) { // If user exists
            SQLiteDatabase db = getReadableDatabase();
            return db.rawQuery("SELECT * FROM applications WHERE user_id=? AND saved_by_user=1", new String[]{String.valueOf(user_id)});
        }

        return null; // Return null if user doesn't exist
    }

    // Method to get all applied jobs by a user
    public Cursor getAppliedJobs(String user_email) {
        int user_id = getUserIdByEmail(user_email); // Get user ID from email
        Log.d("test", "inside  DBHELPER getAppliedJobs ");
        if (user_id != -1) { // If user exists
            SQLiteDatabase db = getReadableDatabase();

           Cursor cursor= db.rawQuery("SELECT * FROM applications WHERE user_id=? AND applied_by_user=1", new String[]{String.valueOf(user_id)});
           String data="";


            while (cursor.moveToNext()){ // moves on each row
                // job_id,title,company,location,salary,date,description
                // moves on each column of single row we can use 0 but thats id in out table
                data +="Title: " +cursor.getString(1)+" Description: "+cursor.getString(2)+"\n";
                Log.d("test", "data = "+data);
            }


           Log.d("test", "inside  DBHELPER getAppliedJobs if statement data="+data);
            return cursor;
        }

        return null; // Return null if user doesn't exist
    }


    public boolean check_user_login(String user_email, String user_password) {

        SQLiteDatabase db = getReadableDatabase();

        // Corrected query to use parameters safely
        String query = "SELECT password FROM users WHERE email=?";


        Cursor cursor = db.rawQuery(query, new String[]{user_email});


        String result = null;
        if (cursor.moveToFirst()) {
            // Retrieve password from the cursor
            result = cursor.getString(0);
        }

        cursor.close(); // closing the cursor after using it



        // Check if result is not null before comparing
        if (result != null && result.equals(user_password)) {

            return true;
        }

        return false;
    }


    public Cursor display_all_user_Data(){

        SQLiteDatabase db= getReadableDatabase();

        return db.rawQuery("SELECT * FROM users",null);
    }
    public long delete_user_data(String email){

        SQLiteDatabase db= getWritableDatabase();


        return db.delete("users","email=?",new String[]{email});
    }

    public long edit_user_data(String email, String firstname, String lastname, String password){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("firstname",firstname);
        contentValues.put("lastname",lastname);
        contentValues.put("password",password);

        return db.update("users",contentValues,"email=?",new String[]{email});

    }


}