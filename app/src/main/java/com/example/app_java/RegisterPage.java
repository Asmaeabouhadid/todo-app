package com.example.app_java;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterPage extends AppCompatActivity {
EditText etusername, etpassword,etRepassword;
Button btnregister;
TextView btnlogin;
DbHelper dbhelper;
@Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register_page);
    etusername = findViewById(R.id.etusername);
    etpassword = findViewById(R.id.etpassword);
    etRepassword = findViewById(R.id.etRepassword);
    btnregister = findViewById(R.id.btnregister);
    btnlogin = findViewById(R.id.btnlogin);
    dbhelper = new DbHelper(this);
    btnlogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(RegisterPage.this, Main2Activity.class);
            startActivity(intent);

        }
    });
    btnregister.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String user, pwd, rePwd;
            user = etusername.getText().toString();
            pwd = etpassword.getText().toString();
            rePwd = etRepassword.getText().toString();
            if (user.equals("") || pwd.equals("") || rePwd.equals("")) {
                Toast.makeText(RegisterPage.this, "Please fill all the fields", Toast.LENGTH_LONG).show();

            } else {
                if (pwd.equals((rePwd))) {
                    if (dbhelper.checkUsername(user)) {
                        Toast.makeText(RegisterPage.this, "User Already Exists ", Toast.LENGTH_LONG).show();
                        return;
                    }
                    boolean registerSuccess = dbhelper.insertData(user, pwd);
                    if (registerSuccess)
                        Toast.makeText(RegisterPage.this, "user Ragister succeful", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(RegisterPage.this, "user Ragister Failed", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(RegisterPage.this, "Passwords do not match ", Toast.LENGTH_LONG).show();
                }


            }


        }
    });
}


}
