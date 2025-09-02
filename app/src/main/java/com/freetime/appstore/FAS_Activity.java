package com.freetime.appstore;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class FAS_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_weather);

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(FAS_Activity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        Button btnInstall = findViewById(R.id.btnInstall);
        btnInstall.setOnClickListener(v -> {
            String url = "https://github.com/FreetimeMaker/Freetime-App-Store/releases/download/v1.0.2/FAS.apk";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });

        Button btnDonate = findViewById(R.id.btnDonate);
        btnDonate.setOnClickListener(v -> {
            Intent intent = new Intent(FAS_Activity.this, FASD_Activity.class);
            startActivity(intent);
            finish();
        });
    }
}
