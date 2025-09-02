package com.freetime.appstore;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DonationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_weather);

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(DonationActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        Button btnInstall = findViewById(R.id.btnInstall);
        btnInstall.setOnClickListener(v -> {
            String url = "https://github.com/FreetimeMaker/Donation/releases/download/v1.0.1/Donation.apk";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });

        Button btnDonate = findViewById(R.id.btnDonate);
        btnDonate.setOnClickListener(v -> {
            Intent intent = new Intent(DonationActivity.this, DD_Activity.class);
            startActivity(intent);
            finish();
        });
    }
}