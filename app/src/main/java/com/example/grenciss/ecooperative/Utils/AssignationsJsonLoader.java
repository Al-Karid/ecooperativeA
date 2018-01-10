package com.example.grenciss.ecooperative.Utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.res.AssetManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.grenciss.ecooperative.Adapters.Assignation;
import com.example.grenciss.ecooperative.Adapters.AssignationAdapter;
import com.example.grenciss.ecooperative.Adapters.AssignationErrorAdapter;
import com.example.grenciss.ecooperative.Adapters.LoadingAdapter;
import com.example.grenciss.ecooperative.Adapters.NetError;
import com.example.grenciss.ecooperative.Binders.MultiListAdapter;
import com.example.grenciss.ecooperative.HttpHandler;
import com.example.grenciss.ecooperative.MainActivity;
import com.example.grenciss.ecooperative.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
    public int code;
    public boolean DataError;

    public AssignationsJsonLoader(Context context, RecyclerView recyclerView)
    {
        this.context = context;
        this.recyclerView = recyclerView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(this.context,"Téléchargement de données",Toast.LENGTH_SHORT).show();
        LoadingAdapter loadingAdapter = new LoadingAdapter(this.context);
        this.recyclerView.setAdapter(loadingAdapter);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        HttpHandler sh = new HttpHandler();
        // Making a request to url and getting response
        String url = "http://0167006d.ngrok.io/api/assignations";
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
                        assignation.idp = c.getInt("id");
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
//                    new GlobalHelper(this.context).dataError = true;
                    publishProgress(1);

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
            this.getOfflineData();

//            AssignationErrorAdapter assignationErrorAdapter = new AssignationErrorAdapter(this.context, this.recyclerView);
//            this.recyclerView.setAdapter(assignationErrorAdapter);

            NetError error = new NetError();
            error.nom = "Oups!!";
            error.tel = "Problème de connexion au serveur";
            error.localite = "Veuillez vérifier votre connexion";
            MultiListAdapter multiListAdapter = new MultiListAdapter(this.context);
            multiListAdapter.addError(error);
            multiListAdapter.addAssignation(this.assignations);
            this.recyclerView.setAdapter(multiListAdapter);

//            AssignationAdapter assignationAdapter = new AssignationAdapter(this.context,this.assignations);
//            this.recyclerView.setAdapter(assignationAdapter);

            Notification.Builder builder = new Notification.Builder(this.context);
            builder.setSmallIcon(R.drawable.ic_cloud_off_black_24dp);
            builder.setContentTitle("Djamo djamo dja");
            builder.setContentText("Chargement de données impossible");
            NotificationManager manager = (NotificationManager) this.context.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(0, builder.build());
        }
        else
        {
            AssignationAdapter assignationAdapter = new AssignationAdapter(this.context,this.assignations);
            this.recyclerView.setAdapter(assignationAdapter);
        }
    }

    public void getOfflineData()
    {
        String data = "";
        try
        {
            AssetManager assetManager = context.getAssets();
            InputStream in = assetManager.open("offlinedata.json");
            InputStreamReader isr = new InputStreamReader(in);
            char [] inputBuffer = new char[100];

            int charRead;
            while((charRead = isr.read(inputBuffer))>0)
            {
                String readString = String.copyValueOf(inputBuffer,0,charRead);
                data += readString;
            }
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }

        JSONObject jsonObj;

        try {
            jsonObj = new JSONObject(data);
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
        } catch (JSONException e) {
            Log.e(TAG, "Json parsing error: " + e.getMessage());
            Toast.makeText(AssignationsJsonLoader.this.context,
                    "Json parsing error: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
