package com.freetime.appstore;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnGeoWeather = findViewById(R.id.btnGeoWeather);
        btnGeoWeather.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GeoWeatherActivity.class);
            startActivity(intent);
        });

        Button btnDonation = findViewById(R.id.btnDonation);
        btnDonation.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DonationActivity.class);
            startActivity(intent);
        });

        Button btnFAS = findViewById(R.id.btnFAS);
        btnFAS.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FAS_Activity.class);
            startActivity(intent);
        });
    }
}