package com.example.retrofit_test.View;

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

    private final Fragment homeFragment = new HomeFragment();
    private final Fragment searchFragment = new SearchFragment();
    private final Fragment askFragment = new AskQuestionFragment();
    private final Fragment profileFragment = new ProfileFragment();

    private final FragmentManager fragmentManager = getSupportFragmentManager();

    //Current fragment shown on screen
    private Fragment activeFragment = homeFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        bottomNavigation.setOnItemSelectedListener(listener);

        addAllFragments();

    }

    private void init() {
        bottomNavigation = findViewById(R.id.bottom_navigation_main);
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

}