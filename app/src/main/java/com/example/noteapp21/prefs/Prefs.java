package com.example.noteapp21.prefs;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {
    private SharedPreferences preferences;

    public Prefs(Context context) {
        preferences = context.getSharedPreferences("setting", Context.MODE_PRIVATE);
    }
    public void saveIsShown(){
        preferences.edit()
                .putBoolean("isShown",true).apply();
    }
    public boolean isShown(){
        return  preferences
                .getBoolean("isShown",false);
    }
}
