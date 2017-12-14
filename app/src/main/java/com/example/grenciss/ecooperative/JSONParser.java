package com.example.grenciss.ecooperative;

import org.json.JSONObject;

import java.io.InputStream;
import java.util.List;
//import org.apache.http.client.

/**
 * Created by grenciss on 12/13/17.
 */

public class JSONParser {
    static InputStream in = null;
    static JSONObject jsonObject = null;
    static String json = "";

    public JSONParser(){}

    // Function get json from url by making http get or post request
    public JSONObject getJsonObject(String url, String method, List<String> param) {

        try
        {
            if (method == "POST")
            {
//                DefaultHttpClient httpClient = new
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return jsonObject;
    }
}
