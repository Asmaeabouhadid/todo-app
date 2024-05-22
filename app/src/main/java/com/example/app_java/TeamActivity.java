package com.example.app_java;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class TeamActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private DatabaseHelpers databaseHelpers;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.team:
                        Toast.makeText(TeamActivity.this, "Équipe ", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.setting:
                        Intent settingIntent = new Intent(TeamActivity.this, GroupTask.class);
                        startActivity(settingIntent);
                        Toast.makeText(TeamActivity.this, "tache de l'equipe", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.out:
                        Intent outIntent = new Intent(TeamActivity.this, HomeActivity.class);
                        startActivity(outIntent);
                        Toast.makeText(TeamActivity.this, "Log Out", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.home:
                        Intent outIntents = new Intent(TeamActivity.this, SingleActivity.class);
                        startActivity(outIntents);
                        Toast.makeText(TeamActivity.this, "Liste des taches", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        databaseHelpers = new DatabaseHelpers(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Insert dummy users (this should be done only once, not every time the app runs)
        if (databaseHelpers.getAllUsers().isEmpty()) {
            databaseHelpers.addUser(new User(0, "Asmae", "asmae@example.com", "Asmae", "Ab"));
            databaseHelpers.addUser(new User(0, "Aziza", "aziza@example.com", "Aziza", "at"));
            databaseHelpers.addUser(new User(0, "Douaa", "DOUAE@example.com", "Douae", "EL"));
            databaseHelpers.addUser(new User(0, "Aya", "AYA@example.com", "Aya", "EL"));
        }

        List<User> userList = databaseHelpers.getAllUsers();
        userAdapter = new UserAdapter(userList);
        recyclerView.setAdapter(userAdapter);
    }
}
