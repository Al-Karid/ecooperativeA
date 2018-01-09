package com.example.grenciss.ecooperative.Utils;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.grenciss.ecooperative.Adapters.Assignation;
import com.example.grenciss.ecooperative.Adapters.AssignationAdapter;
import com.example.grenciss.ecooperative.Adapters.AssignationErrorAdapter;
import com.example.grenciss.ecooperative.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by grenciss on 1/8/18.
 */

public class AssignationsJsonLoader extends AsyncTask<Void, Integer, Void> {

    protected Context context;
    protected RecyclerView recyclerView;
    protected List<Assignation> assignations = new ArrayList<>();
    protected static String TAG = "Runtime error";
    protected int code;

    public AssignationsJsonLoader(Context context, RecyclerView recyclerView)
    {
        this.context = context;
        this.recyclerView = recyclerView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(this.context,"Téléchargement de données",Toast.LENGTH_SHORT).show();

    }

    @Override
    protected Void doInBackground(Void... voids) {
        HttpHandler sh = new HttpHandler();
        // Making a request to url and getting response
        String url = "http://d018d46e.ngrok.io/api/assignations";
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
            publishProgress(1);

        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
//        super.onProgressUpdate(values);
        if (values[0]==0)
        {
            this.code  = 0;
        }
        else
        {
            this.code = values[0];
        }
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        if (this.code != 0)
        {
            this.code=1;
            AssignationErrorAdapter assignationErrorAdapter = new AssignationErrorAdapter(this.context, this.recyclerView);
            this.recyclerView.setAdapter(assignationErrorAdapter);
        }
        else
        {
            AssignationAdapter assignationAdapter = new AssignationAdapter(this.context,this.assignations);
            this.recyclerView.setAdapter(assignationAdapter);
        }
    }
}
