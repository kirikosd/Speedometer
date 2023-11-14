package com.kirikos.speedometer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.slider.Slider;
import com.kirikos.speedometer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView btv = findViewById(R.id.nav_view);
        btv.setSelectedItemId(R.id.speed_menu);

        btv.setOnItemSelectedListener(item -> {
           if (item.getItemId() == R.id.speed_menu) {
               return true;
           } else if (item.getItemId() == R.id.settings_menu) {
               startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
               finish();
               return true;
           } else if (item.getItemId() == R.id.history_menu) {
               startActivity(new Intent(getApplicationContext(), HistoryActivity.class));
               finish();
               return true;
           }
           return false;
        });
    }
}