package com.kirikos.speedometer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.slider.Slider;

public class SettingsActivity extends AppCompatActivity {
    SharedPreferences preferences;
    TextView sliderVal;
    Slider slider;
    Button btnApply;
    Button btnCancel;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_menu);

        BottomNavigationView btv = findViewById(R.id.nav_view);
        btv.setSelectedItemId(R.id.settings_menu);

        btv.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.settings_menu) {
                return true;
            } else if (item.getItemId() == R.id.speed_menu) {
                startActivity(new Intent(getApplicationContext(), SpeedActivity.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.history_menu) {
                startActivity(new Intent(getApplicationContext(), HistoryActivity.class));
                finish();
                return true;
            }
            return false;
        });

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        sliderVal = findViewById(R.id.slider_val);
        slider = findViewById(R.id.slider);
        slider.setValue(Float.valueOf(preferences.getString("slider","0")));
        sliderVal.setText(Float.toString(slider.getValue()));
        btnApply = findViewById(R.id.btn_apply);
        btnCancel = findViewById(R.id.btn_cancel);

        slider.addOnChangeListener((slider, value, fromUser) -> sliderVal.setText(Float.toString(value)));
    }
    public void save(View view){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("slider",Float.toString(slider.getValue()));
//        editor.putString("key2",editText3.getText().toString());
        editor.apply();
        Toast.makeText(getApplicationContext(), "Changes Saved" , Toast.LENGTH_SHORT).show();
    }
    public void cancel(View view){
        startActivity(new Intent(getApplicationContext(), SpeedActivity.class));
        Toast.makeText(getApplicationContext(), "No changes made", Toast.LENGTH_SHORT).show();
    }

}