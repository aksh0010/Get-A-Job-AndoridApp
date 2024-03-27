package com.example.get_a_job;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

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
    }
    public Cursor getSavedJobs2(String userEmail){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT jobs.title, jobs.company, jobs.location, jobs.date_posted, applications.date_applied ,applications.application_id,applications.applied_by_user " +
                "FROM jobs " +
                "INNER JOIN applications ON jobs.job_id = applications.job_id " +
                "INNER JOIN users ON applications.user_id = users.user_id " +
                "WHERE users.email = ? AND applications.saved_by_user = 1";

        return db.rawQuery(query,new String[]{userEmail});
    }

    // Method to set a job as applied by a user
    public Cursor getAppliedJobs2(String userEmail){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT jobs.title, jobs.company, jobs.location, jobs.date_posted, applications.date_applied , applications.job_id, applications.application_id, applications.applied_by_user " +
                "FROM jobs " +
                "INNER JOIN applications ON jobs.job_id = applications.job_id " +
                "INNER JOIN users ON applications.user_id = users.user_id " +
                "WHERE users.email = ? AND applications.applied_by_user = 1";

        return db.rawQuery(query,new String[]{userEmail});
    }
    public void markJob(View context,String user_email,String app_id){
        TextView name = context.findViewById(R.id.tv_job_id);
        SQLiteDatabase db = getReadableDatabase();
        ContentValues contentValues = new ContentValues();

        int user_id = getUserIdByEmail(user_email);
        String query = "SELECT applications.saved_by_user " +
                "FROM applications " +
                "WHERE applications.user_id = ? AND applications.application_id = ?";

        /**
         * CREATE TABLE applications (application_id INTEGER PRIMARY KEY AUTOINCREMENT,
         * applied_by_user INTEGER DEFAULT 0,saved_by_user INTEGER DEFAULT 0,
         * date_applied TIMESTAMP DEFAULT CURRENT_TIMESTAMP , user_id INTEGER,
         * job_id INTEGER, FOREIGN KEY(user_id) REFERENCES users(user_id),
         * FOREIGN KEY(job_id) REFERENCES jobs(job_id))";
         */
        Cursor cursor = db.rawQuery(query,new String[]{String.valueOf(user_id),app_id});
        if (cursor.moveToFirst()) {
            int is_saved = cursor.getInt(0);
            Log.d("Gettingg", "is applied? "+is_saved);
            if (is_saved>=1) {
                String query2 = "UPDATE applications " +
                        "SET saved_by_user = 0 " +
                        "WHERE user_id = ? AND application_id = ?";
                db.execSQL(query2, new String[]{String.valueOf(user_id), app_id});
                Toast.makeText(context.getContext(),"The application #" + app_id + " is unsaved now! ",Toast.LENGTH_SHORT).show();
                Log.d("Gettingg", "The application #" + app_id + " is unsaved now! ");
            }
            else{
                String query3 = "UPDATE applications " +
                        "SET saved_by_user = 1 " +
                        "WHERE user_id = ? AND application_id = ?";
                db.execSQL(query3, new String[]{String.valueOf(user_id), app_id});
                Toast.makeText(context.getContext(),"The application #" + app_id + " is saved now! ",Toast.LENGTH_SHORT).show();
                Log.d("Gettingg","The application #"+app_id+" is saved now! ");
            }

            //Toast.makeText(context.getContext(),"Hello from"+is_applied+"",Toast.LENGTH_SHORT).show();
        }
    }
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

    @Deprecated
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
    @Deprecated
    public Cursor getAppliedJobs(String user_email) {
        int user_id = getUserIdByEmail(user_email); // Get user ID from email
        Log.d("test", "inside  DBHELPER getAppliedJobs ");
        if (user_id != -1) { // If user exists
            SQLiteDatabase db = getReadableDatabase();

           Cursor cursor= db.rawQuery("SELECT * FROM applications WHERE user_id=? AND applied_by_user=1", new String[]{String.valueOf(user_id)});
           String data="";
           Cursor cursor_test = db.rawQuery("SELECT * FROM applications",null);

           while(cursor_test.moveToNext()){
               Log.d("testñ", cursor_test.getString(0));
           }
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

    public Cursor display_all_job_Data(){

        SQLiteDatabase db= getReadableDatabase();

        return db.rawQuery("SELECT * FROM jobs",null);
    }
    public Cursor display_user_search_Data(String query) {
        SQLiteDatabase db = getReadableDatabase();

        // Use the provided query string with the LIKE statement to search for matching titles or descriptions
        String sqlQuery = "SELECT * FROM jobs WHERE title LIKE '%" + query + "%' OR description LIKE '%" + query + "%'";

        return db.rawQuery(sqlQuery, null);
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
