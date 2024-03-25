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
        DBHelper dbHelper = new DBHelper(getApplicationContext(),"test_db",null,1);

        // Add 10 jobs without the date_posted parameter
     /*  dbHelper.add_new_Job("Software Engineer", "Exciting opportunity for software engineers!", "Tech Solutions Inc.", "Toronto, ON", "Competitive");
        dbHelper.add_new_Job("Data Scientist", "Join our team to analyze and interpret complex data.", "Data Insights Co.", "Vancouver, BC", "Negotiable");
        dbHelper.add_new_Job("Network Administrator", "Manage and maintain computer networks.", "Connectivity Services Ltd.", "Calgary, AB", "Competitive");
        dbHelper.add_new_Job("Security Analyst", "Identify and prevent security breaches.", "SecureTech Solutions", "Ottawa, ON", "Negotiable");
        dbHelper.add_new_Job("IT Technician", "Provide technical support to users and troubleshoot issues.", "Resolve IT Services", "Edmonton, AB", "Negotiable");
        dbHelper.add_new_Job("Web Developer", "Develop and maintain websites and web applications.", "Digital Innovations Corp.", "Montreal, QC", "Competitive");
        dbHelper.add_new_Job("Systems Analyst", "Analyze and design information systems.", "TechPro Systems", "Winnipeg, MB", "Competitive");
        dbHelper.add_new_Job("Database Administrator", "Manage and maintain databases.", "DataWare Corporation", "Halifax, NS", "Negotiable");
        dbHelper.add_new_Job("UI/UX Designer", "Create user-friendly interfaces and design interactive experiences.", "DesignTech Solutions", "Quebec City, QC", "Competitive");

        dbHelper.add_user("Aksh","Patel","admin","admin");
        */
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(btn_switch.isChecked()){
                    saveUserData();
                }
                String email = et_username.getText().toString();
                String password = et_password.getText().toString();

                boolean success =dbHelper.check_user_login(email,password);

                if(success){
                    Log.d("test", "Hello 7 Inside if part");
                    Toast.makeText(getApplicationContext(),"Logged in Successfully",Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(MainActivity.this,App_home_Activity.class);
                    intent.putExtra("user_email", email);
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