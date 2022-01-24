package com.example.retrofit_test.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;
import android.os.Debug;
import android.view.View;

import com.example.retrofit_test.MyApplication;
import com.example.retrofit_test.R;
import com.example.retrofit_test.View.Fragment.AskQuestionFragment;
import com.example.retrofit_test.View.Fragment.QuestionFragment;
import com.example.retrofit_test.View.Fragment.ProfileFragment;
import com.example.retrofit_test.View.Fragment.SearchFragment;
import com.example.retrofit_test.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private Fragment questionFragment;
    private Fragment searchFragment;
    private Fragment askFragment;
    private Fragment profileFragment;
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
            fragmentManager.beginTransaction().add(R.id.frame_layout_main,questionFragment,"Question").commit();
            activeFragment = questionFragment;
        }
        else {
            retrieveFragments(savedInstanceState.getString("ActiveFragment"));
        }

        init();

    }

    private void retrieveFragments(String fragmentTag) {
        questionFragment = fragmentManager.findFragmentByTag("Question");
        searchFragment = fragmentManager.findFragmentByTag("Search");
        askFragment = fragmentManager.findFragmentByTag("Ask");
        profileFragment = fragmentManager.findFragmentByTag("Profile");
        switch (fragmentTag) {
            case "Question" :
                activeFragment = questionFragment;
                break;
            case "Search" :
                activeFragment = searchFragment;
                break;
            case "Ask" :
                activeFragment = askFragment;
                break;
            case "Profile" :
                activeFragment = profileFragment;
                break;
        }
    }

    private void init() {
        BottomNavigationView bottomNavigation = binding.bottomNavigationMain;
        bottomNavigation.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.bottom_nav_home){
                if (questionFragment == activeFragment)
                    return false;
                if (questionFragment == null) {
                    questionFragment = new QuestionFragment();
                    fragmentManager.beginTransaction().hide(activeFragment).add(R.id.frame_layout_main,questionFragment,"Question").commit();
                    activeFragment = questionFragment;
                    return true;
                }
                fragmentManager.beginTransaction().hide(activeFragment).show(questionFragment).commit();
                activeFragment = questionFragment;
                return true;
            }
            else if (item.getItemId() == R.id.bottom_nav_search){
                if (searchFragment == activeFragment)
                    return false;
                if (searchFragment == null) {
                    searchFragment = new SearchFragment();
                    fragmentManager.beginTransaction().hide(activeFragment).add(R.id.frame_layout_main,searchFragment,"Search").commit();
                    activeFragment = searchFragment;
                    return true;
                }
                fragmentManager.beginTransaction().hide(activeFragment).show(searchFragment).commit();
                activeFragment = searchFragment;
                return true;
            }
            else if (item.getItemId() == R.id.bottom_nav_ask) {
                if (askFragment == activeFragment)
                    return false;
                if (askFragment == null) {
                    askFragment = new AskQuestionFragment();
                    fragmentManager.beginTransaction().hide(activeFragment).add(R.id.frame_layout_main,askFragment,"Ask").commit();
                    activeFragment = askFragment;
                    return true;
                }
                fragmentManager.beginTransaction().hide(activeFragment).show(askFragment).commit();
                activeFragment = askFragment;
                return true;
            }
            else if (item.getItemId() == R.id.bottom_nav_profile) {
                if (profileFragment == activeFragment)
                    return false;
                if (profileFragment == null) {
                    profileFragment = new ProfileFragment();
                    fragmentManager.beginTransaction().hide(activeFragment).add(R.id.frame_layout_main,profileFragment,"Profile").commit();
                    activeFragment = profileFragment;
                    return true;
                }
                fragmentManager.beginTransaction().hide(activeFragment).show(profileFragment).commit();
                activeFragment = profileFragment;
                return true;
            }

            else return false;
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("ActiveFragment",activeFragment.getTag());
    }
}