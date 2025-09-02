package com.freetime.appstore;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class GeoWeatherActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_detail);

        TextView nameView = findViewById(R.id.appName);
        TextView descriptionView = findViewById(R.id.appDescription);

        String name = getIntent().getStringExtra("name");
        String description = getIntent().getStringExtra("description");

        nameView.setText(name);
        descriptionView.setText(description);
    }
}
