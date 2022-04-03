package com.example.StackOverFlow_App.View.Custom;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.DialogFragment;

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
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle("Theme")
                .setSingleChoiceItems(themes, (themeMode == AppCompatDelegate.MODE_NIGHT_YES) ? 1 : 0, (dialogInterface, i) -> {
                    Log.e(TAG, "onCreateDialog:   " + i );
                    if (i == 0) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        dialogInterface.dismiss();
                    }
                    else if (i == 1) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        dialogInterface.dismiss();
                    }
                });
        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
