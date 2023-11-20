package com.os.tenxapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_number);
        findViewById(R.id.button6).setOnClickListener(view -> {
            Intent myIntent = new Intent(MainActivity.this, OtpVerificationActivity.class);
            startActivity(myIntent);
        });
    }
}