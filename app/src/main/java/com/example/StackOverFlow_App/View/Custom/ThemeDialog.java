package com.example.StackOverFlow_App.View.Custom;

import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.DialogFragment;

import com.example.StackOverFlow_App.Other.Constant;

public class ThemeDialog extends DialogFragment {


    private static final String TAG = "ThemeDialog";

    public ThemeDialog() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        CharSequence[] themes = new CharSequence[2];
        themes[0] = "Light";
        themes[1] = "Dark";
        int themeMode = AppCompatDelegate.getDefaultNightMode();
        SharedPreferences preferences = requireContext().getApplicationContext().getSharedPreferences(Constant.applicationSharedPreferencesKey,MODE_PRIVATE);
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle("Theme")
                .setSingleChoiceItems(themes, (themeMode == AppCompatDelegate.MODE_NIGHT_YES) ? 1 : 0, (dialogInterface, i) -> {
                    if (i == 0) {
                        preferences.edit().putBoolean(Constant.darkThemeSharedPreferencesKey,false).apply();
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    }
                    else if (i == 1) {
                        preferences.edit().putBoolean(Constant.darkThemeSharedPreferencesKey,true).apply();
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    }
                });
        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
