package com.example.get_a_job;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class App_home_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("test", "Inside app home acitivity");
        setContentView(R.layout.activity_app_home);
    }

}