package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SpacActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spac2);
        int secondsDelayed = 1;
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SpacActivity2.this, MainActivity.class));
            finish();
        }, secondsDelayed * 1000);
    }
    }
