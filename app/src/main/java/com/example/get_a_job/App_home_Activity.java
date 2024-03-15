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
    }

    public void appliedFrag(View view){
        Applied_jobs applied_jobs = new Applied_jobs();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.frame, applied_jobs);
        ft.commit();
    }
    public void savedFrag(View view){
        Saved_jobs saved_jobs = new Saved_jobs();
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