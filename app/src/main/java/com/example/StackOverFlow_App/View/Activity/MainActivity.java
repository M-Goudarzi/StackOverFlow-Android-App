package com.example.StackOverFlow_App.View.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.StackOverFlow_App.Other.Constant;
import com.example.StackOverFlow_App.R;
import com.example.StackOverFlow_App.View.Fragment.QuestionFragment;
import com.example.StackOverFlow_App.View.Fragment.SearchFragment;
import com.example.StackOverFlow_App.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private QuestionFragment questionFragment;
    private SearchFragment searchFragment;
    private final FragmentManager fragmentManager = getSupportFragmentManager();
    //Current fragment shown on screen
    private Fragment activeFragment;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        if (savedInstanceState == null) {
            questionFragment = new QuestionFragment();
            fragmentManager.beginTransaction().add(R.id.frame_layout_main, questionFragment, Constant.questionFragmentTag).commit();
            activeFragment = questionFragment;
        } else {
            retrieveFragments(savedInstanceState.getString(Constant.activeFragmentBundleKey));
        }

        init();

    }

    private void retrieveFragments(String fragmentTag) {
        questionFragment = (QuestionFragment) fragmentManager.findFragmentByTag(Constant.questionFragmentTag);
        searchFragment = (SearchFragment) fragmentManager.findFragmentByTag(Constant.searchFragmentTag);
        switch (fragmentTag) {
            case Constant.questionFragmentTag:
                activeFragment = questionFragment;
                break;
            case Constant.searchFragmentTag:
                activeFragment = searchFragment;
                break;
        }
    }

    private void init() {
        binding.settingButtonMain.setOnClickListener(v -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        });
        BottomNavigationView bottomNavigation = binding.bottomNavigationMain;
        bottomNavigation.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.bottom_nav_home) {
                if (questionFragment == activeFragment)
                    return false;
                if (questionFragment == null) {
                    questionFragment = new QuestionFragment();
                    fragmentManager.beginTransaction().hide(activeFragment).add(R.id.frame_layout_main, questionFragment, Constant.questionFragmentTag).commit();
                    activeFragment = questionFragment;
                    return true;
                }
                fragmentManager.beginTransaction().hide(activeFragment).show(questionFragment).commit();
                activeFragment = questionFragment;
                return true;
            } else if (item.getItemId() == R.id.bottom_nav_search) {
                if (searchFragment == activeFragment)
                    return false;
                if (searchFragment == null) {
                    searchFragment = new SearchFragment();
                    fragmentManager.beginTransaction().hide(activeFragment).add(R.id.frame_layout_main, searchFragment, Constant.searchFragmentTag).commit();
                    activeFragment = searchFragment;
                    return true;
                }
                fragmentManager.beginTransaction().hide(activeFragment).show(searchFragment).commit();
                activeFragment = searchFragment;
                return true;
            } else return false;
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Constant.activeFragmentBundleKey, activeFragment.getTag());
    }
}