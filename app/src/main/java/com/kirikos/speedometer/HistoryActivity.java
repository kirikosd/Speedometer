package com.kirikos.speedometer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TableLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HistoryActivity extends AppCompatActivity {
    SQLiteDatabase database;
    TableLayout table;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_menu);

        BottomNavigationView btv = findViewById(R.id.nav_view);
        btv.setSelectedItemId(R.id.history_menu);

        btv.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.history_menu) {
                return true;
            } else if (item.getItemId() == R.id.settings_menu) {
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.speed_menu) {
                startActivity(new Intent(getApplicationContext(), SpeedActivity.class));
                finish();
                return true;
            }
            return false;
        });

        table = findViewById(R.id.dataTable);
        //database = openOrCreateDatabase("mydb.db",MODE_PRIVATE,null);
        //database.execSQL("Insert or ignore into SavedCases Values(? , ?, 90,'20.54545,45.545454')");
    }
}