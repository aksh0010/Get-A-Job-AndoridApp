package com.example.get_a_job;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

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
        DBHelper_user dbHelper = new DBHelper_user(getApplicationContext(),"test_db",null,1);

//        dbHelper.add_user("Aksh","Patel","admin","admin");
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(btn_switch.isChecked()){
                    saveUserData();
                }
                String email = et_username.getText().toString();
                String password = et_password.getText().toString();

                boolean success =dbHelper.check_login(email,password);

                if(success){
                    Log.d("test", "Hello 7 Inside if part");
                    Toast.makeText(getApplicationContext(),"Logged in Successfully",Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(MainActivity.this,App_home_Activity.class);
                    startActivity(intent);

                }
                else {
                    Log.d("test", "Hello 8 Inside else part");
                    Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_LONG).show();

                }


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

}