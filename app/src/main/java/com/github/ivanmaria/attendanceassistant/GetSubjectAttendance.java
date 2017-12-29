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

public class GetSubjectAttendance {
    Context context;
    String username;
    int subnum, pracnum;
    GetAttendance get1[] = new GetAttendance[25];
    GetAttendance get2[] = new GetAttendance[25];

    public GetSubjectAttendance(Context mcontext, String user) {
        context = mcontext;
        username = user;
        SavePref pref = new SavePref(context);
        subnum = pref.getInt("subnum");
        pracnum = pref.getInt("pracnum");
        int i = 0;
        int j = 0;
        for (i = 1; i <= subnum; i++) {
            String subkey = "sub" + i;
            String subject = pref.getVal(subkey);
            get1[i] = new GetAttendance(context, username, subject, subkey, i);
            get1[i].execute();
        }
        for (i = 1; i <= pracnum; i++) {
            String subkey = "prac" + i;
            String subject = pref.getVal(subkey);
            get2[i] = new GetAttendance(context, username, subject, subkey, i);
            get2[i].execute();
        }
    }

}

class GetAttendance extends AsyncTask<Void, Void, String> {
    Context context;
    String username, subject, subkey;

    public GetAttendance(Context mcontext, String user, String sub, String key, int i) {
        username = user;
        subject = sub;
        subkey = key;
        context = mcontext;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            JSONObject obj = new JSONObject(s);

            //creating a new user object
            SavePref pref = new SavePref(context);
            pref.saveInt(subkey + "_total", obj.getInt("total"));
            pref.saveInt(subkey + "_present", obj.getInt("present"));
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
        params.put("subject", subject);

        //returing the response
        return requestHandler.sendPostRequest(URLs.URL_GETSUBATT, params);
    }
}

