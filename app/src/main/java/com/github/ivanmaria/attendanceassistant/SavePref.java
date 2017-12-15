package com.github.ivanmaria.attendanceassistant;

/**
 * Created by IsaacIvan on 13-12-2017.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

public class SavePref {
    private static final String SHARED_PREF_NAME = "attendanceassistant";
    private Context context;

    public SavePref(Context context){
        this.context = context;
    }

    public void saveVal(String key, String value) {

        SharedPreferences sharedPref = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getVal(String key) {

        SharedPreferences sharedPref = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPref.getString(key, "");
    }

    public void clearVal() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        context.startActivity(new Intent(context, LoginActivity.class));
        Toast.makeText(context,"Logged Out!", Toast.LENGTH_SHORT).show();

    }

}