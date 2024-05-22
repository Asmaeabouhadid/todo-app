package com.example.app_java;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button buttonUserLogin = findViewById(R.id.buttonUserLogin);
        Button buttonAdminLogin = findViewById(R.id.buttonAdminLogin);

        buttonUserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ouvrir l'activité de login utilisateur
                Intent intent = new Intent(HomeActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });

        buttonAdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ouvrir l'activité de login admin
                Intent intent = new Intent(HomeActivity.this, LoginAdmin.class);
                startActivity(intent);
            }
        });
    }
}
