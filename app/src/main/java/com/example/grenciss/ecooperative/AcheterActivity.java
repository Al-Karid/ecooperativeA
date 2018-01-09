package com.example.grenciss.ecooperative;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.grenciss.ecooperative.Utils.JsonTransmitter;

import org.json.JSONException;
import org.json.JSONObject;

public class AcheterActivity extends AppCompatActivity {
    String[] names = {"Blaise Okou","Asanon Aziz","Cissé Ali"};
    String[] products = {"Cacao","Atiéké","Banane","Tomate","Arahide"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acheter);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle("Acheter");
        final Bundle bundle = getIntent().getExtras();

//        AutoCompleteTextView P = (AutoCompleteTextView)findViewById(R.id.fournisseur);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_item, names);
//        P.setThreshold(1);
//        P.setAdapter(adapter);

        final AutoCompleteTextView PD = (AutoCompleteTextView)findViewById(R.id.produit);
        ArrayAdapter<String> pd_adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_item, products);
        PD.setThreshold(1);
        PD.setAdapter(pd_adapter);

        final EditText weight = (EditText) findViewById(R.id.poids);
        final TextView price = (TextView)findViewById(R.id.prix);
        TextView PlanteurNom = (TextView)findViewById(R.id.txtNom);
        double s = Double.parseDouble(weight.getHint().toString()) * 375;
        String v = String.valueOf(s);//.substring(-2);
        price.setText(v);
        int id = bundle.getInt("assignation_id");
        PlanteurNom.setText(bundle.getString("planteur_nom"));

        weight.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Double s = Math.ceil(Double.parseDouble(weight.getText().toString()) * 375);
                String V = String.valueOf(s.intValue());//.substring(-2);
                price.setText(V);
//                price.setText(String.valueOf(Integer.parseInt(String.valueOf(s).split(".")[0])));
                return false;
            }
        });

        Button btnValider = (Button)findViewById(R.id.btnValider);
        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    JSONObject toSend = new JSONObject();
                    toSend.put("produit", PD.getText());
                    toSend.put("poids", weight.getText());
                    toSend.put("montant", price.getText());
                    toSend.put("acheteur_id", "1");
                    toSend.put("planteur_id", String.valueOf(bundle.getInt("planteur_id")));

                    JsonTransmitter transmitter = new JsonTransmitter();
                    transmitter.execute(new JSONObject[] {toSend});

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
