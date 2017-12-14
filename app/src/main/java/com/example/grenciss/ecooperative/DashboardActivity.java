package com.example.grenciss.ecooperative;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle("Dashboard");

        Button btnAss = findViewById(R.id.btnAss);
        btnAss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(DashboardActivity.this, AssignationsActivity.class);
                startActivity(I);
            }
        });
        Button btnAcheter = (Button)findViewById(R.id.btnAcheter);
        btnAcheter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(DashboardActivity.this, AcheterActivity.class);
                startActivity(I);
            }
        });
    }
}
