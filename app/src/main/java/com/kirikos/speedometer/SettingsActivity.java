package com.kirikos.speedometer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.slider.Slider;

public class SettingsActivity extends AppCompatActivity {
    TextView sliderVal;
    Slider slider;
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


        Button btnApply = findViewById(R.id.btn_apply);
        Button btnCancel = findViewById(R.id.btn_cancel);

        SharedPreferences sp = getSharedPreferences("sharedPreferences", MODE_PRIVATE);

        sliderVal = findViewById(R.id.slider_val);
        slider = findViewById(R.id.slider);
        slider.setValue(80);

        slider.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                sliderVal.setText(Float.toString(value));
            }
        });

        //        Shared Preferences Code
//        Button btn_apply = findViewById(R.id.btn_apply);
//        btn_apply.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences sharedPreferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("unit", unit_variable);
//                etc...
//                editor.apply();
//                successful toast message
//            }
//        });
//
//        reading from shared preferances
//        SharedPreferences sharedPreferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE);
//        String unit_variable;
//        unit_variable = sharedPreferences.getString("unit","");
//        unitTextbox = setText(unit_variable) ;
    }
}