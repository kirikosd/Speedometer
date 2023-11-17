package com.kirikos.speedometer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.preference.PreferenceManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SpeedActivity extends AppCompatActivity implements LocationListener {
    MyTts myTts;
    Boolean sl_exceeded_check; // speed limit exceeded
    Float sl; // speed limit
    View view;
    SharedPreferences preferences;
    TextView warning;
    TextView speed_text;
    LocationManager locationManager;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speed_menu);

        view = this.getWindow().getDecorView();
        myTts = new MyTts(this);
        warning = findViewById(R.id.warning);
        speed_text = findViewById(R.id.text_dashboard);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        sl = Float.valueOf(preferences.getString("slider","0"));
        sl_exceeded_check = false;

        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(
                    this,new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},123);
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        //locationManager.removeUpdates(this);

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
    @SuppressLint("DefaultLocale")
    @Override
    public void onLocationChanged(@NonNull Location location) {
        //loc.setText(String.valueOf(location.getLatitude()) + "," + String.valueOf(location.getLongitude()));
        float speed = location.getSpeed() * 3.6f;
        speed_text.setText(String.format("%.0f", speed));
        sl_exceeded(speed, sl);
    }

    public void sl_exceeded(float cur_speed, float speed_limit){
        if (cur_speed > speed_limit){
            if(!sl_exceeded_check) {
                view.setBackgroundColor(Color.parseColor("#FA3232"));
                warning.setVisibility(View.VISIBLE);
                myTts.speak("SPEED LIMIT EXCEEDED.SLOW DOWN");
                sl_exceeded_check = true;
            }
        } else {
            sl_exceeded_check = false;
            warning.setVisibility(View.INVISIBLE);
            view.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
        }
    }
}