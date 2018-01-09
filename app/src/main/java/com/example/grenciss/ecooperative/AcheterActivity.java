package com.example.grenciss.ecooperative;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

public class AcheterActivity extends AppCompatActivity {
    String[] names = {"Blaise Okou","Asanon Aziz","Cissé Ali"};
    String[] products = {"Cacao","Atiéké","Banane","Tomate","Arahide"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acheter);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle("Acheter");
        Bundle bundle = getIntent().getExtras();

//        AutoCompleteTextView P = (AutoCompleteTextView)findViewById(R.id.fournisseur);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_item, names);
//        P.setThreshold(1);
//        P.setAdapter(adapter);

        AutoCompleteTextView PD = (AutoCompleteTextView)findViewById(R.id.produit);
        ArrayAdapter<String> pd_adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_item, products);
        PD.setThreshold(1);
        PD.setAdapter(pd_adapter);

        final EditText weight = (EditText) findViewById(R.id.poids);
        final TextView price = (TextView)findViewById(R.id.prix);
        TextView PlanteurNom = (TextView)findViewById(R.id.txtNom);
        double s = Double.parseDouble(weight.getHint().toString()) * 375;
        price.setText(String.valueOf(s));
        int id = bundle.getInt("assignation_id");
        PlanteurNom.setText(bundle.getString("planteur_nom"));

        weight.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                double s = Double.parseDouble(weight.getText().toString()) * 375;
                price.setText(String.valueOf(s));
                return false;
            }
        });
    }
}
