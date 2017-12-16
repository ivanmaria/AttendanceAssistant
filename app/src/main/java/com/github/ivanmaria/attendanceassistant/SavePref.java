package com.github.ivanmaria.attendanceassistant;

/**
 * Created by IsaacIvan on 13-12-2017.
 */

import android.content.Context;
import android.content.SharedPreferences;

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
    }

    public void saveInt(String key, int value) {

        SharedPreferences sharedPref = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public int getInt(String key) {

        SharedPreferences sharedPref = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPref.getInt(key, 0);
    }
}