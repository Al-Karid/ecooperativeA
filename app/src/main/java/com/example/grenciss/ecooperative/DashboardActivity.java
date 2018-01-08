package com.example.grenciss.ecooperative;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.grenciss.ecooperative.Adapters.Assignation;
import com.example.grenciss.ecooperative.Adapters.AssignationAdapter;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
//        setContentView(R.layout.assignation_item);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle("Assignations");

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.cardList);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        AssignationAdapter assignationAdapter = new AssignationAdapter(this.getAssignations(10));
        recyclerView.setAdapter(assignationAdapter);

        /*Button btnAss = findViewById(R.id.btnAss);
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
        Button btnHist = (Button)findViewById(R.id.btnHist);
        btnHist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(DashboardActivity.this, ListeDesProduitsActivity.class);
                startActivity(I);
            }
        });*/
    }

    public List<Assignation> getAssignations(int n)
    {
        List<Assignation> assignations = new ArrayList<Assignation>();
        for (int i=0;i<n;i++)
        {
            Assignation assignation = new Assignation();
            assignation.id = i;
            assignation.nom = "Nom_"+i;
            assignation.tel = "5794742"+i;
            assignation.localite = "Divo_"+i;
            assignations.add(assignation);
        }
        return assignations;
    }
}
