package com.example.StackOverFlow_App.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.StackOverFlow_App.Other.Constant;
import com.example.StackOverFlow_App.View.Custom.ThemeDialog;
import com.example.StackOverFlow_App.databinding.ActivitySettingsBinding;

public class SettingsActivity extends AppCompatActivity {

    private ActivitySettingsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        init();


    }


    private void init() {
        binding.tvSourceSettingsActivity.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constant.sourceCodeGithubUrl));
            startActivity(intent);
        });
        binding.tvThemeSettingsActivity.setOnClickListener(v -> {
            ThemeDialog themeDialog = new ThemeDialog();
            themeDialog.show(getSupportFragmentManager(),Constant.themeDialogTag);
        });
        binding.tvApiSettingsActivity.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constant.stackExchangeApiWebSiteUrl));
            startActivity(intent);
        });
        binding.arrowBackSettingsActivity.setOnClickListener(v -> {
            finish();
            overridePendingTransition(0,0);
        });
    }
}