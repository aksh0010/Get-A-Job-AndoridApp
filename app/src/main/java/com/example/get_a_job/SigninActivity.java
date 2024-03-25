package com.example.get_a_job;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SigninActivity extends AppCompatActivity {
    EditText et_signin_firstname , et_signin_lastname,et_signin_email,et_signin_password,et_signin_re_password;
    Button btn_signin_submit,btn_sigin_gohome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        et_signin_firstname = findViewById(R.id.et_signin_firstname);
        et_signin_lastname = findViewById(R.id.et_signin_lastname);
        et_signin_email = findViewById(R.id.et_signin_email);
        et_signin_password = findViewById(R.id.et_signin_password);
        et_signin_re_password = findViewById(R.id.et_signin_re_password);
        btn_signin_submit = findViewById(R.id.btn_signin_submit);
        btn_sigin_gohome = findViewById(R.id.btn_sigin_gohome);

        btn_sigin_gohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SigninActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btn_signin_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass1 = et_signin_password.getText().toString();
                String pass2 = et_signin_re_password.getText().toString();



                if(pass1.equals(pass2)){

                    DBHelper dbHelper = new DBHelper(getApplicationContext(),"test_db",null,1);

                    String firstname=et_signin_firstname.getText().toString();
                    String lastname=et_signin_lastname.getText().toString();
                    String email=et_signin_email.getText().toString();
                    String password=pass1;

                   long row= dbHelper.add_user(firstname,lastname,email,password);

                   if( row <0 ){
                       Toast.makeText(getApplicationContext(),"Error : Type details again",Toast.LENGTH_LONG).show();

                   }
                    else {

                        Toast.makeText(getApplicationContext(),"Account  Created",Toast.LENGTH_LONG).show();

                       Intent intent = new Intent(SigninActivity.this,MainActivity.class);
                       startActivity(intent);

                   }





                }
                else {
                    Toast.makeText(getApplicationContext(),"Passwords are different",Toast.LENGTH_LONG).show();

                }
            }
        });


    }
}