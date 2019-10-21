package com.itla.blogui.repositorio;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Sesion {

    private SharedPreferences sharedPreferences;

    public Sesion(Context context){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void set(String key, String value){
        sharedPreferences.edit().putString(key,value).commit();
    }

    public String get(String key){
        String value = sharedPreferences.getString(key,"");
        return value;
    }

}
