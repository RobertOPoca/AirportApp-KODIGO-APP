package com.kodigo.airportapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kodigo.airportapp.view.AirlineFragment;
import com.kodigo.airportapp.view.AirplaneFragment;
import com.kodigo.airportapp.view.FlightFragment;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity implements
    BottomNavigationView.OnNavigationItemSelectedListener {


  private BottomNavigationView bottomNavigationView;
  private Fragment fragment;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    bottomNavigationView = findViewById(R.id.navigation);
    bottomNavigationView.setOnNavigationItemSelectedListener(this);

    loadFragment(new FlightFragment());
  }

  private boolean loadFragment(Fragment fragment) {
    if (fragment != null) {
      getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment)
          .addToBackStack(null).commit();
      return true;
    }
    return false;
  }

  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()) {
      case R.id.flightItem:
        fragment = new FlightFragment();
        break;
      case R.id.airplaneItem:
        fragment = new AirplaneFragment();
        break;
      case R.id.airlineItem:
        fragment = new AirlineFragment();
        break;
    }
    return loadFragment(fragment);
  }
}