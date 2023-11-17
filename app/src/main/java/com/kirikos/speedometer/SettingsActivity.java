package com.kirikos.speedometer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

        preferences = getPreferences(MODE_PRIVATE);
        sliderVal = findViewById(R.id.slider_val);
        slider = findViewById(R.id.slider);
        slider.setValue(preferences.getFloat("slider",0));
        sliderVal.setText(Float.toString(preferences.getFloat("slider",0)));
        btnApply = findViewById(R.id.btn_apply);
        btnCancel = findViewById(R.id.btn_cancel);

        slider.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                sliderVal.setText(Float.toString(value));
            }
        });

        //  Shared Preferences Code
        btnApply.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("slider",slider.toString());
                //editor.putString("key2",editText3.getText().toString());
                editor.apply();
                Toast.makeText(getApplicationContext(), "Changes Saved" , Toast.LENGTH_SHORT).show();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SpeedActivity.class));
                Toast.makeText(getApplicationContext(), "No changes made", Toast.LENGTH_SHORT).show();
            }
        });
    }
}