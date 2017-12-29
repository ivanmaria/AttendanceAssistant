package com.github.ivanmaria.attendanceassistant;

/**
 * Created by IsaacIvan on 16-12-2017.
 */


import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class GetClass extends AsyncTask<Void, Void, String> {
    Context context;

    public void getClass(Context mcontext) {
        context = mcontext;
        execute();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            //converting response to json object
            JSONObject obj = new JSONObject(s);
            //if no error in response
            JSONObject userJsonSub = obj.getJSONObject("class");

            //creating a new user object
            SavePref pref = new SavePref(context);
            pref.saveInt("classnum", obj.getInt("classnum"));
            int cnum = obj.getInt("classnum");
            for (int i = 1; i <= cnum; i++)
                pref.saveVal("class" + i, userJsonSub.getString("class" + i));

            Toast.makeText(context, "Data fetched Successfully", Toast.LENGTH_SHORT).show();

        } catch (JSONException e) {
            Toast.makeText(context, "An Error has occurred!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    protected String doInBackground(Void... voids) {
        //creating request handler object
        RequestHandler requestHandler = new RequestHandler();

        //creating request parameters
        HashMap<String, String> params = new HashMap<>();
        //params.put("regno", username);

        //returing the response
        return requestHandler.sendPostRequest(URLs.URL_GETCLASS, params);
    }
}

