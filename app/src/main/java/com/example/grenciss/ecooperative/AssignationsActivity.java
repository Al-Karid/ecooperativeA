package com.example.grenciss.ecooperative;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.grenciss.ecooperative.Adapters.CustomAdapter;


public class AssignationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignations);
        getSupportActionBar().setElevation(0);

        getSupportActionBar().setTitle("Assignations");
//        getSupportActionBar().setIcon(R.drawable.ic_local_florist_black_24dp);

        ListView lstAss = (ListView)findViewById(R.id.lstAss);
        lstAss.setAdapter(new CustomAdapter(this));
    }
}
