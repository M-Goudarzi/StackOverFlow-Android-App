package com.example.retrofit_test.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;
import com.example.retrofit_test.R;
import com.example.retrofit_test.View.Fragment.AskQuestionFragment;
import com.example.retrofit_test.View.Fragment.HomeFragment;
import com.example.retrofit_test.View.Fragment.ProfileFragment;
import com.example.retrofit_test.View.Fragment.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigation;

    private Fragment homeFragment;
    private Fragment searchFragment;
    private Fragment askFragment;
    private Fragment profileFragment;

    private final FragmentManager fragmentManager = getSupportFragmentManager();

    //Current fragment shown on screen
    private Fragment activeFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null){
            initFragments();
            addAllFragments();
        }
        else {
            retrieveFragments(savedInstanceState.getString("ActiveFragment"));
        }

        init();

        bottomNavigation.setOnItemSelectedListener(listener);

    }

    private void init() {
        bottomNavigation = findViewById(R.id.bottom_navigation_main);
    }

    private void initFragments() {
        homeFragment = new HomeFragment();
        searchFragment = new SearchFragment();
        askFragment = new AskQuestionFragment();
        profileFragment = new ProfileFragment();
        activeFragment = homeFragment;
    }

    private void retrieveFragments(String fragmentTag) {
        homeFragment = fragmentManager.findFragmentByTag("home");
        searchFragment = fragmentManager.findFragmentByTag("search");
        askFragment = fragmentManager.findFragmentByTag("ask");
        profileFragment = fragmentManager.findFragmentByTag("profile");
        switch (fragmentTag) {
            case "home" :
                activeFragment = homeFragment;
                break;
            case "search" :
                activeFragment = searchFragment;
                break;
            case "ask" :
                activeFragment = askFragment;
                break;
            case "profile" :
                activeFragment = profileFragment;
                break;
        }
    }

    private final NavigationBarView.OnItemSelectedListener listener = item -> {
        if (item.getItemId() == R.id.bottom_nav_home){
            fragmentManager.beginTransaction().hide(activeFragment).show(homeFragment).commit();
            activeFragment = homeFragment;
            return true;
        }
        else if (item.getItemId() == R.id.bottom_nav_search){
            fragmentManager.beginTransaction().hide(activeFragment).show(searchFragment).commit();
            activeFragment = searchFragment;
            return true;
        }
        else if (item.getItemId() == R.id.bottom_nav_ask) {
            fragmentManager.beginTransaction().hide(activeFragment).show(askFragment).commit();
            activeFragment = askFragment;
            return true;
        }
        else if (item.getItemId() == R.id.bottom_nav_profile) {
            fragmentManager.beginTransaction().hide(activeFragment).show(profileFragment).commit();
            activeFragment = profileFragment;
            return true;
        }
        else return false;
    };



    private void addAllFragments() {
        fragmentManager.beginTransaction().add(R.id.frame_layout_main,profileFragment,"profile").hide(profileFragment).commit();
        fragmentManager.beginTransaction().add(R.id.frame_layout_main,askFragment,"ask").hide(askFragment).commit();
        fragmentManager.beginTransaction().add(R.id.frame_layout_main,searchFragment,"search").hide(searchFragment).commit();
        fragmentManager.beginTransaction().add(R.id.frame_layout_main,homeFragment,"home").commit();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("ActiveFragment",activeFragment.getTag());
    }
}