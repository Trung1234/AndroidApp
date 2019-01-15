package com.betterlife.pronunciationjourney.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by nguyen on 08/03/2018.
 */

public class MyPreferences {
    private SharedPreferences sharedPreferences;
    private static MyPreferences instance;

    public MyPreferences(Activity context) {
        if (sharedPreferences == null){
            sharedPreferences = context.getSharedPreferences(Constants.MY_PREF,Context.MODE_PRIVATE);
        }
    }

    public static MyPreferences getInstance(Activity context){
        if(instance == null){
            instance = new MyPreferences(context);
        }
        return instance;
    }

    public void saveIntObject(String key,int value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key,value);
        editor.commit();
    }

    public int getIntObject(String key){
        return sharedPreferences.getInt(key,0);
    }

    public void saveStringObject(String key,String value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.commit();
    }

    public String getString(String key){
        return sharedPreferences.getString(key,"");
    }
}
