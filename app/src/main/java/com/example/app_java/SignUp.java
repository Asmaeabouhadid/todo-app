package com.example.app_java;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {

    EditText t_fullname,t_user,t_email,t_password,t_phone;
    Button btn_register;
    RadioButton rmale,rfemale;
    String gender ="";
    DatbaseHelper db;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().setTitle("Sign Up");

        t_fullname= (EditText)findViewById(R.id.fullname);
        t_user=(EditText)findViewById(R.id.user);
        t_email=(EditText)findViewById(R.id.email);
        t_password=(EditText)findViewById(R.id.password);
        t_phone=(EditText)findViewById(R.id.phone);
        btn_register=(Button)findViewById(R.id.register);
        rmale=(RadioButton)findViewById(R.id.male);
        rfemale=(RadioButton)findViewById(R.id.female);


        /*sqlite */

        db = new DatbaseHelper(this);

        /* FireBAse
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("customer");
        firebaseAuth =FirebaseAuth.getInstance();
        /* firebase */


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String fname=t_fullname.getText().toString().trim();
                final String uname=t_user.getText().toString().trim();
                final String email=t_email.getText().toString().trim();
                final String phone=t_phone.getText().toString().trim();
                String pass=t_password.getText().toString().trim();

                if(rmale.isChecked()){
                    gender="male";
                }
                if(rfemale.isChecked()){
                    gender="female";
                }

                if(TextUtils.isEmpty(fname)){
                    Toast.makeText(SignUp.this,"Enter your name",Toast.LENGTH_SHORT).show();

                }
                if(TextUtils.isEmpty(uname)){
                    Toast.makeText(SignUp.this,"Enter your Username",Toast.LENGTH_SHORT).show();

                }
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(SignUp.this,"Enter your email",Toast.LENGTH_SHORT).show();

                }
                if(TextUtils.isEmpty(phone)){
                    Toast.makeText(SignUp.this,"Enter your phone",Toast.LENGTH_SHORT).show();

                }
                if(TextUtils.isEmpty(pass)){
                    Toast.makeText(SignUp.this,"Enter your password",Toast.LENGTH_SHORT).show();

                }

                if(true){
                    long val =db.addUser(fname,uname,email,phone,pass,gender);
                    if(val>0){
                        Toast.makeText(SignUp.this,"Succesfully Registered",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),LoginAdmin.class));

                    }else{
                        Toast.makeText(SignUp.this,"Registration Error",Toast.LENGTH_SHORT).show();

                    }

                }



            }
        });


    }
}