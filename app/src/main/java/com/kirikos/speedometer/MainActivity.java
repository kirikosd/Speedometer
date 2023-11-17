package com.kirikos.speedometer;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(getApplicationContext(), SpeedActivity.class));
        database = openOrCreateDatabase("mydb.db",MODE_PRIVATE,null);
        database.execSQL("Create table if not exists SavedCases(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "timestamp TIMESTAMP," +
                "speed INTEGER ," +
                "location Text)");
        database.execSQL("Insert or ignore into SavedCases Values(? , ?, 90,'20.54545,45.545454')");
    }
}