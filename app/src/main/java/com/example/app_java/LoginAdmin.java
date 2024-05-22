package com.example.app_java;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginAdmin extends AppCompatActivity {

    EditText t_user,t_password;
    Button btn_login;
    DatbaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);

        t_user =(EditText)findViewById(R.id.userid);
        t_password=(EditText)findViewById(R.id.pass);
        btn_login=(Button)findViewById(R.id.signin);

        /* sqlite */
        db =new DatbaseHelper(this);

        /* sqlite*/

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=t_user.getText().toString().trim();
                String pass=t_password.getText().toString().trim();
                Boolean res=db.checkUsers(user,pass);
                if(res == true){
                    Toast.makeText(LoginAdmin.this,"Succesfully loggedin",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }else{
                    Toast.makeText(LoginAdmin.this,"Login Error",Toast.LENGTH_SHORT).show();
                }
            }
        });







    }

    public void gotoSignup(View view) {

        startActivity(new Intent(getApplicationContext(),SignUp.class));
    }
}