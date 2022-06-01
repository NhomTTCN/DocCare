package com.example.doccare.DoctorSpace;

import android.os.Bundle;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.doccare.DoctorSpace.fragments.DoctorProfileFragment;
import com.example.doccare.Fragment.ChatFragment;
import com.example.doccare.Fragment.HomeFragment;
import com.example.doccare.Fragment.ProfileFragment;
import com.example.doccare.Fragment.SearchFragment;
import com.example.doccare.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class DoctorMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottomnavigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(new DoctorProfileFragment());
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment1 = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment1 = new HomeFragment();
                    break;
                case R.id.navigation_search:
                    fragment1 = new HomeFragment();
                    break;
                case R.id.navigation_chat:
                    fragment1 = new HomeFragment();
                    break;
                case R.id.navigation_profile:
                    fragment1 = new DoctorProfileFragment();
                    break;
            }
            loadFragment(fragment1);
            return false;
        }
    };
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}