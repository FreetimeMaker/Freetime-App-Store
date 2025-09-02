package com.freetime.appstore;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DD_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button btnDonateOxa = findViewById(R.id.btnDonateOxa);
        btnDonateOxa.setOnClickListener(v -> {
            Intent intent = new Intent(DD_Activity.this, DDOxa_Activity.class);
            startActivity(intent);
        });

        Button btnDonateCoin = findViewById(R.id.btnDonateCoin);
        btnDonateCoin.setOnClickListener(v -> {
            Intent intent = new Intent(DD_Activity.this, DDCoin_Activity.class);
            startActivity(intent);
        });
    }
}