package com.example.grenciss.ecooperative.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by grenciss on 1/9/18.
 */

public class GlobalHelper {

    public Context context;

    public GlobalHelper(Context context){
        this.context = context;
    }

    public boolean isConnected()
    {
        ConnectivityManager check = (ConnectivityManager)  this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] info = check.getAllNetworkInfo();

        for (int i = 0; i<info.length; i++)
        {
            if (info[i].getState() == NetworkInfo.State.CONNECTED)
            {
                return true;
            }
            else
            {
//                    Toast.makeText(context, "Aucune connexion internet", Toast.LENGTH_SHORT).show();
            }
        }

        return false;
    }


}
