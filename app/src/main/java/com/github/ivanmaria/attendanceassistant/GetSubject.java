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


public class GetSubject extends AsyncTask<Void, Void, String> {
    Context context;
    String username;

    public void getSubject(Context mcontext, String user) {
        username = user;
        context = mcontext;
        execute();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(context, "Fetching Data from Server...", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            //converting response to json object
            JSONObject obj = new JSONObject(s);
            //if no error in response
            if (!obj.getBoolean("error")) {
                JSONObject userJsonSub = obj.getJSONObject("subject");
                JSONObject userJsonPrac = obj.getJSONObject("practical");

                //creating a new user object
                SavePref pref = new SavePref(context);
                pref.saveInt("subnum", obj.getInt("subnum"));
                pref.saveInt("pracnum", obj.getInt("pracnum"));
                int snum = obj.getInt("subnum");
                int pnum = obj.getInt("pracnum");
                for (int i = 1; i <= snum; i++)
                    pref.saveVal("sub" + i, userJsonSub.getString("sub" + i));
                for (int i = 1; i <= pnum; i++)
                    pref.saveVal("prac" + i, userJsonPrac.getString("prac" + i));

                Toast.makeText(context, "Data fetched Successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, obj.getString("message"), Toast.LENGTH_SHORT).show();
            }
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
        params.put("regno", username);

        //returing the response
        return requestHandler.sendPostRequest(URLs.URL_GETSUB, params);
    }
}

