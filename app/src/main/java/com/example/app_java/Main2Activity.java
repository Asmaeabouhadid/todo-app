package com.example.app_java;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {
DbHelper dbHelper;
    EditText etusername , etpassword;
    Button btnLogin;
    TextView signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etusername=findViewById(R.id.etusername);
        etpassword=findViewById(R.id.etPassword);
        btnLogin=findViewById(R.id.btnLogin);
        signup=findViewById(R.id.signup);
        dbHelper = new DbHelper(this);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, RegisterPage.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               boolean isLoggedId = dbHelper.checkUsername(etusername.getText().toString(),etpassword.getText().toString());
               if (isLoggedId){

                   Intent intent =new Intent(Main2Activity.this,SplashActivity.class);
                   startActivity(intent);
               }
               else{
                       Toast.makeText(Main2Activity.this, "Login Failed", Toast.LENGTH_LONG).show();
               }


            }
        });


    }
}
