package com.example.emergence_aids;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    SharedPreferences mySharedPrefrences;

    public SharedPref(Context context){
        mySharedPrefrences = context.getSharedPreferences("filename", context.MODE_PRIVATE);
    }
    public void setState(boolean state){
        SharedPreferences.Editor editor = mySharedPrefrences.edit();
        editor.putBoolean("StateMode", state);
        editor.commit();
    }
    public boolean LoadState() {
        boolean state= mySharedPrefrences.getBoolean("StateMode", false);
        return state;
    }
}
