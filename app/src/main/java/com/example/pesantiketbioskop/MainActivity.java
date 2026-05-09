package com.example.pesantiketbioskop;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation =
                findViewById(R.id.bottomNavigation);

        // DEFAULT FRAGMENT
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout,
                        new HomeFragment())
                .commit();

        bottomNavigation.setOnItemSelectedListener(item -> {

            Fragment selectedFragment = null;

            if (item.getItemId() == R.id.menu_home) {

                selectedFragment = new HomeFragment();

            } else if (item.getItemId()
                    == R.id.menu_profile) {

                selectedFragment = new ProfileFragment();
            }

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameLayout,
                            selectedFragment)
                    .commit();

            return true;
        });
    }
}