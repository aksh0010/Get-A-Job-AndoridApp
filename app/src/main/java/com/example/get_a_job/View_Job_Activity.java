package com.example.get_a_job;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class View_Job_Activity extends AppCompatActivity {
    TextView tv_view_title, tv_view_company, tv_view_location, tv_view_salary, tv_view_date, tv_view_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_job);
        tv_view_title = findViewById(R.id.tv_view_title);
        tv_view_company = findViewById(R.id.tv_view_company);
        tv_view_location = findViewById(R.id.tv_view_location);
        tv_view_salary = findViewById(R.id.tv_view_salary);
        tv_view_date = findViewById(R.id.tv_view_date);
        tv_view_description = findViewById(R.id.tv_view_description);

        String job_id = getIntent().getStringExtra("job_id");


        if (job_id != null) {
            DBHelper dbHelper = new DBHelper(View_Job_Activity.this, "test_db", null, 1);
            Cursor cursor = dbHelper.display_single_job_Data(job_id);

            if (cursor != null && cursor.moveToFirst()) {

                do {
                    // job_id,title,company,location,salary,date,description


                    String id = cursor.getString(0);
                    String title = cursor.getString(1);
                    String company = cursor.getString(2);
                    String location = cursor.getString(3);
                    String salary = cursor.getString(4);
//                    String date = cursor.getString(5);
                    String date = "Date";
                    String description = cursor.getString(6);
                    tv_view_title.setText(title);
                    tv_view_company.setText(company);
                    tv_view_location.setText(location);
                    tv_view_salary.setText(salary);
                    tv_view_date.setText(date);
                    tv_view_description.setText(description);
                } while (cursor.moveToNext());

                cursor.close();
            } else {
//                tv_noapplied_job_display.setText("You do not have any applied jobs yet :/");
                Log.d("test", "no data found: ");


            }
        } else {

            Log.d("test", "fetchDataFromDB: useremail is null ");
        }
    }
}