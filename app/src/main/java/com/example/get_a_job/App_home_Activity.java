package com.example.get_a_job;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class App_home_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("test", "Inside app home acitivity");
        setContentView(R.layout.activity_app_home);

        // Retrieve extra string from intent
        String userEmail = getIntent().getStringExtra("user_email");
        Log.d("test", "User email: " + userEmail);

        // By default view for the apphome
        About_Myself about_myself = new About_Myself();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame, about_myself);
        ft.commit();
    }

    public void appliedFrag(View view){
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

    public void savedFrag(View view){

        String userEmail = getIntent().getStringExtra("user_email");

        // Create a new instance of Applied_jobs fragment with the user email
        Saved_jobs saved_jobs = Saved_jobs.newInstance(userEmail);



//        Saved_jobs saved_jobs = new Saved_jobs();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame, saved_jobs);
        ft.commit();
    }
    public void myselfFrag(View view) {
        About_Myself about_myself = new About_Myself();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame, about_myself);
        ft.commit();

    }

}