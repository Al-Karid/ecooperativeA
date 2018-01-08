package com.example.grenciss.ecooperative.Utils;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.grenciss.ecooperative.Adapters.Assignation;
import com.example.grenciss.ecooperative.Adapters.AssignationAdapter;
import com.example.grenciss.ecooperative.HttpHandler;
import com.example.grenciss.ecooperative.ListeDesProduitsActivity;
import com.example.grenciss.ecooperative.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by grenciss on 1/8/18.
 */

public class AssignationsJsonLoader extends AsyncTask<Void, Void, Void> {

    protected Context context;
    protected RecyclerView recyclerView;
    protected List<Assignation> assignations = new ArrayList<>();
    protected static String TAG = "Runtime error";

    public AssignationsJsonLoader(Context context, RecyclerView recyclerView)
    {
        this.context = context;
        this.recyclerView = recyclerView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(this.context,"Json Data is downloading",Toast.LENGTH_LONG).show();

    }

    @Override
    protected Void doInBackground(Void... arg0) {
        HttpHandler sh = new HttpHandler();
        // Making a request to url and getting response
        String url = "http://9118fc59.ngrok.io/api/assignations";
        String jsonStr = sh.makeServiceCall(url);

//        Log.e(TAG, "Response from url: " + jsonStr);
        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                // Getting JSON Array node
                JSONArray a = jsonObj.getJSONArray("data");

                // looping through All Contacts
                for (int i = 0; i < a.length(); i++) {

                    JSONObject c = a.getJSONObject(i);
                    Assignation assignation = new Assignation();

                    assignation.id = Integer.parseInt(c.getString("id"));

                        JSONObject p = c.getJSONObject("planteurs_id");
                        assignation.nom = p.getString("nom");
                        assignation.tel = p.getString("tel");
                        assignation.localite = p.getString("localite");

                        this.assignations.add(assignation);
                }
            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
                    Toast.makeText(AssignationsJsonLoader.this.context,
                                "Json parsing error: " + e.getMessage(),
                                Toast.LENGTH_LONG).show();

            }

        } else {
            Log.e(TAG, "Couldn't get json from server.");
                Toast.makeText(AssignationsJsonLoader.this.context,
                            "Couldn't get json from server. Check LogCat for possible errors!",
                            Toast.LENGTH_LONG).show();

        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        AssignationAdapter assignationAdapter = new AssignationAdapter(this.assignations);
        this.recyclerView.setAdapter(assignationAdapter);
    }
}
