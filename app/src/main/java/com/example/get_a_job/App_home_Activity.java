package com.example.get_a_job;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class App_home_Activity extends AppCompatActivity {


    Button btn_search,saved_btn,applied_btn,myself_btn;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("test", "Inside app home acitivity");
        setContentView(R.layout.activity_app_home);
        btn_search = findViewById(R.id.btn_search);
        saved_btn = findViewById(R.id.btn_saved_jobs);
        applied_btn = findViewById(R.id.btn_applied_jobs);
        myself_btn = findViewById(R.id.btn_myself);
        myself_btn.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.act_btn)));
        // Retrieve extra string from intent
        String userEmail = getIntent().getStringExtra("user_email");
        DBHelper dbHelper = new DBHelper(this, "test_db", null, 1);
        Cursor cursor = dbHelper.getUsername(userEmail);//Get the user first name by its email
        if (cursor.moveToFirst()){
            username = cursor.getString(0);
        }
        Bundle bundle = new Bundle();
        bundle.putString("username",username);
        Log.d("test", "User email: " + userEmail);

        // By default view for the apphome
        About_Myself about_myself = new About_Myself();
        about_myself.setArguments(bundle);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame, about_myself);
        ft.commit();
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_search.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.act_btn)));
                applied_btn.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.deselc_btn)));
                saved_btn.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.deselc_btn)));
                myself_btn.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.deselc_btn)));
            }
        });

        applied_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_search.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.deselc_btn)));
                applied_btn.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.act_btn)));
                saved_btn.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.deselc_btn)));
                myself_btn.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.deselc_btn)));

                // Retrieve user email from intent
                String userEmail = getIntent().getStringExtra("user_email");

                // Create a new instance of Applied_jobs fragment with the user email
                Applied_jobs applied_jobs = Applied_jobs.newInstance(userEmail);

                // Replace the fragment in the frame layout
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frame, applied_jobs);
                ft.commit();

            }
        });

        saved_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_search.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.deselc_btn)));
                applied_btn.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.deselc_btn)));
                saved_btn.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.act_btn)));
                myself_btn.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.deselc_btn)));

                String userEmail = getIntent().getStringExtra("user_email");

                // Create a new instance of Applied_jobs fragment with the user email
                Saved_jobs saved_jobs = Saved_jobs.newInstance(userEmail);



//        Saved_jobs saved_jobs = new Saved_jobs();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frame, saved_jobs);
                ft.commit();
            }
        });

        myself_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_search.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.deselc_btn)));
                applied_btn.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.deselc_btn)));
                saved_btn.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.deselc_btn)));
                myself_btn.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.act_btn)));

                Bundle bundle = new Bundle();
                bundle.putString("username",username);
                About_Myself about_myself = new About_Myself();
                about_myself.setArguments(bundle);
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frame, about_myself);
                ft.commit();
            }
        });
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(App_home_Activity.this,Search_Job_Activity.class);
                intent.putExtra("user_email", userEmail);
                startActivity(intent);
            }
        });


    }

    public void appliedFrag(View view){
    }

    public void savedFrag(View view){


    }
    public void myselfFrag(View view) {



    }

}