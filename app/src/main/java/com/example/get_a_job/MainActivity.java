package com.example.get_a_job;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    EditText et_username, et_password;
    Button btn_submit;
    Switch btn_switch;
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor sp_editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_username = findViewById(R.id.et_username);
        et_password=findViewById(R.id.et_password);
        btn_submit=findViewById(R.id.btn_submit);
        btn_switch = findViewById(R.id.btn_switch);
        get_userData();

        /*
        *
        * :o
        * */
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(btn_switch.isChecked()){
                    saveUserData();
                }
                Intent intent = new Intent(MainActivity.this,App_home_Activity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onPause() {
        if(btn_switch.isChecked()){
            saveUserData();
        }
        super.onPause();
    }
    public  void saveUserData(){
        sharedPreferences = getPreferences(MODE_PRIVATE);
        sp_editor = sharedPreferences.edit();

        sp_editor.putString("key_username",et_username.getText().toString());
        sp_editor.putString("key_password",et_password.getText().toString());
        sp_editor.putBoolean("key_switch",btn_switch.isChecked());
        sp_editor.commit();

    }

    public void get_userData(){
        sharedPreferences = getPreferences(MODE_PRIVATE);
        et_username.setText(sharedPreferences.getString("key_username",null));
        et_password.setText(sharedPreferences.getString("key_password",null));
        btn_switch.setChecked(sharedPreferences.getBoolean("key_switch",false));

    }

    /*Code for fragement functions
    *
    * */
    /*
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

     */
}