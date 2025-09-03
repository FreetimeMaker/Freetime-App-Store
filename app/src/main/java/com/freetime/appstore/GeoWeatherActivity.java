package com.freetime.appstore;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class GeoWeatherActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_weather);

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(GeoWeatherActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        Button btnInstall = findViewById(R.id.btnInstall);
        GWUpdateChecker.checkForUpdate("1.0.1", "FreetimeMaker", "GeoWeather", downloadUrl -> {
            runOnUiThread(() -> {
                btnInstall.setText("Update available! Click to download.");
                btnInstall.setOnClickListener(v -> {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(downloadUrl));
                    startActivity(intent);
                });
            });
        });


        Button btnDonate = findViewById(R.id.btnDonate);
        btnDonate.setOnClickListener(v -> {
            Intent intent = new Intent(GeoWeatherActivity.this, GWD_Activity.class);
            startActivity(intent);
            finish();
        });
    }
}
