package com.example.get_a_job;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Search_Job_Activity extends AppCompatActivity {
    RecyclerView recylerView;
    ArrayAdaptor_JobDisplayObject myAdapter;
    ArrayList<JobDisplayObject> dataSets= new ArrayList<>();
    Button btn_search;
    EditText et_search_job;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_job);
        recylerView = findViewById(R.id.recyler_job_search_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recylerView.setLayoutManager(linearLayoutManager);
        btn_search =findViewById(R.id.btn_search);
        et_search_job = findViewById(R.id.tv_search_job);

        myAdapter = new ArrayAdaptor_JobDisplayObject(dataSets);
        recylerView.setAdapter(myAdapter);
        fetchDataFromDB();

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//              dataSets.clear();
              get_my_jobs(et_search_job.getText().toString());
              myAdapter.notifyDataSetChanged();
            }
        });
    }


    private void get_my_jobs(String query){
        Log.d("test", "fetchDataFromDB: ");
        String userEmail = getIntent().getStringExtra("user_email");

        if (query != null) {
            Log.d("test", "fetchDataFromDB: inside 1 if ");
            DBHelper dbHelper = new DBHelper(Search_Job_Activity.this, "test_db", null, 1);
            //Cursor cursor = dbHelper.getAppliedJobs(userEmail);

            //SQLiteDatabase db = getReadableDatabase();

            //db.rawQuery(query, new String[]{userEmail});
            Cursor cursor = dbHelper.display_user_search_Data(query);

            /*while(join.moveToNext()){
                Log.d("testñ",join.getString(0)+join.getString(1)+join.getString(2)+join.getString(3)+join.getString(4));
            }*/

            if (cursor != null && cursor.moveToFirst()) {
                dataSets.clear();
                Log.d("test", "fetchDataFromDB: inside 2 if ");
                do {
                    // job_id,title,company,location,salary,date,description


                    String title = cursor.getString(1);
                    //String title = dbHelper.execRawQuery("SELECT title FROM jobs WHERE job_id=?",null);


                    String company = cursor.getString(2);
                    String location = cursor.getString(3);
                    String date = cursor.getString(5);
                    Log.d("test", "adding title "+title);
                    Log.d("test", "adding comp "+company);
                    Log.d("test", "adding loc "+location);
                    Log.d("test", "adding date "+date);

                    JobDisplayObject job = new JobDisplayObject(title, company, location, date);
                    dataSets.add(job);
                    Log.d("test", "adding data "+job);
                } while (cursor.moveToNext());

                cursor.close();
                myAdapter.notifyDataSetChanged();
            } else {
//                tv_noapplied_job_display.setText("You do not have any applied jobs yet :/");
                Log.d("test", "No jobs found ");
                Toast.makeText(getApplicationContext(),"No such jobs found",Toast.LENGTH_LONG).show();


            }
        }
        else {


            Toast.makeText(getApplicationContext(),"Write something to search",Toast.LENGTH_LONG).show();

            Log.d("test", "Write something");
        }


    }

    private void fetchDataFromDB() {
        Log.d("test", "fetchDataFromDB: ");
        String userEmail = getIntent().getStringExtra("user_email");

        if (userEmail != null) {
            Log.d("test", "fetchDataFromDB: inside 1 if ");
            DBHelper dbHelper = new DBHelper(Search_Job_Activity.this, "test_db", null, 1);
            //Cursor cursor = dbHelper.getAppliedJobs(userEmail);

            //SQLiteDatabase db = getReadableDatabase();

            //db.rawQuery(query, new String[]{userEmail});
            Cursor cursor = dbHelper.display_all_job_Data();

            /*while(join.moveToNext()){
                Log.d("testñ",join.getString(0)+join.getString(1)+join.getString(2)+join.getString(3)+join.getString(4));
            }*/

            if (cursor != null && cursor.moveToFirst()) {
                Log.d("test", "fetchDataFromDB: inside 2 if ");
                do {
                    // job_id,title,company,location,salary,date,description


                    String title = cursor.getString(1);
                    //String title = dbHelper.execRawQuery("SELECT title FROM jobs WHERE job_id=?",null);


                    String company = cursor.getString(2);
                    String location = cursor.getString(3);
                    String date = cursor.getString(5);
                    Log.d("test", "adding title "+title);
                    Log.d("test", "adding comp "+company);
                    Log.d("test", "adding loc "+location);
                    Log.d("test", "adding date "+date);

                    JobDisplayObject job = new JobDisplayObject(title, company, location, date);
                    dataSets.add(job);
                    Log.d("test", "adding data "+job);
                } while (cursor.moveToNext());

                cursor.close();
                myAdapter.notifyDataSetChanged();
            } else {
//                tv_noapplied_job_display.setText("You do not have any applied jobs yet :/");
                Log.d("test", "no data found: ");


            }
        }
        else {

            Log.d("test", "fetchDataFromDB: useremail is null ");
        }
    }

}