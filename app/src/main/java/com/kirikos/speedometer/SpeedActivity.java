package com.kirikos.speedometer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SpeedActivity extends AppCompatActivity implements LocationListener {
    TextView speed_text;
    TextView loc;
    LocationManager locationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speed_menu);

        speed_text = findViewById(R.id.text_dashboard);
        loc = findViewById(R.id.textView2);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

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
    @Override
    public void onLocationChanged(@NonNull Location location) {
        loc.setText(String.valueOf(location.getLatitude()) + "," + String.valueOf(location.getLongitude()));
        float speed = location.getSpeed() * 3.6f;
        speed_text.setText(String.format("%.0f", speed));
    }
}