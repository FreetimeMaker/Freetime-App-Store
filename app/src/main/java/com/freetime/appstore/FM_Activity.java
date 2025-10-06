package com.freetime.appstore;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;


public class FM_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fm);

        Button fmDonobtn = findViewById(R.id.fmDonobtn);
        fmDonobtn.setOnClickListener(v -> {
            Intent intent = new Intent(FM_Activity.this, FM_Dono_Activity.class);
            startActivity(intent);
        });

        Button gwInfobtn = findViewById(R.id.gwInfobtn);
        gwInfobtn.setOnClickListener(v -> {
            Intent intent = new Intent(FM_Activity.this, GW_Activity.class);
            startActivity(intent);
        });

        Button donoInfobtn = findViewById(R.id.donoInfobtn);
        donoInfobtn.setOnClickListener(v -> {
            Intent intent = new Intent(FM_Activity.this, Dono_Activity.class);
            startActivity(intent);
        });

        Button fasInfobtn = findViewById(R.id.fasInfobtn);
        fasInfobtn.setOnClickListener(v -> {
            Intent intent = new Intent(FM_Activity.this, FAS_Activity.class);
            startActivity(intent);
        });

        Button catInfobtn = findViewById(R.id.catInfobtn);
        catInfobtn.setOnClickListener(v -> {
            Intent intent = new Intent(FM_Activity.this, CatClickerActivity.class);
            startActivity(intent);
        });

        Button PLC2Infobtn = findViewById(R.id.plc2Infobtn);
        PLC2Infobtn.setOnClickListener(v -> {
            Intent intent = new Intent(FM_Activity.this, PLC2Activity.class);
            startActivity(intent);
        });

        Button backBtn = findViewById(R.id.backbtn);
        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(FM_Activity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}