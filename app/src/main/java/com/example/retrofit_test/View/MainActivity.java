package com.example.retrofit_test.View;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.retrofit_test.R;
import com.example.retrofit_test.View.Fragment.HomeFragment;
import com.example.retrofit_test.View.Fragment.ProfileFragment;
import com.example.retrofit_test.View.Fragment.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigation;
    private HomeFragment homeFragment;
    private SearchFragment searchFragment;
    private ProfileFragment profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        bottomNavigation.setSelectedItemId(R.id.bottom_nav_home);
        getSupportFragmentManager().beginTransaction().add(R.id.frame_layout_main,homeFragment).commit();

        bottomNavigation.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.bottom_nav_home){
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_main,homeFragment).commit();
                return true;
            }
            else if (item.getItemId() == R.id.bottom_nav_search){
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_main,searchFragment).commit();
                return true;
            }
            else if (item.getItemId() == R.id.bottom_nav_profile) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_main,profileFragment).commit();
                return true;
            }
            else return false;
        });

    }

    private void init() {
        bottomNavigation = findViewById(R.id.bottom_navigation_main);
        homeFragment = new HomeFragment();
        searchFragment = new SearchFragment();
        profileFragment = new ProfileFragment();
    }

}