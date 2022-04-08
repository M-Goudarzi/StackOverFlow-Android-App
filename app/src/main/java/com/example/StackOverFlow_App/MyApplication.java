package com.example.StackOverFlow_App;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatDelegate;

import com.example.StackOverFlow_App.Other.Constant;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(Constant.applicationSharedPreferencesKey,MODE_PRIVATE);
        boolean darkTheme = preferences.getBoolean(Constant.darkThemeSharedPreferencesKey,false);

        if (darkTheme)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }
}
