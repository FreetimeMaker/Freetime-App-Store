package com.freetime.appstore;

import static com.freetime.appstore.R.*;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        );

        Button gwInfobtn = findViewById(R.id.gwInfobtn);
        gwInfobtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GW_Activity.class);
            startActivity(intent);
        });

        Button donoInfobtn = findViewById(R.id.donoInfobtn);
        donoInfobtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Dono_Activity.class);
            startActivity(intent);
        });

        Button fasInfobtn = findViewById(R.id.fasInfobtn);
        fasInfobtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FAS_Activity.class);
            startActivity(intent);
        });

        Button catInfobtn = findViewById(R.id.catInfobtn);
        catInfobtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CatClickerActivity.class);
            startActivity(intent);
        });

        Button PLCInfobtn = findViewById(R.id.PLCInfobtn);
        PLCInfobtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PLC_Activity.class);
            startActivity(intent);
        });

        Button plc2Infobtn = findViewById(R.id.plc2Infobtn);
        plc2Infobtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PLC2Activity.class);
            startActivity(intent);
        });

        Button platInfobtn = findViewById(R.id.platInfobtn);
        platInfobtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PlatformerActivity.class);
            startActivity(intent);
        });

        Button osInfobtn = findViewById(R.id.osInfobtn);
        osInfobtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, OS_Activity.class);
            startActivity(intent);
        });

        Button cryptoInfobtn = findViewById(R.id.cryptoInfobtn);
        cryptoInfobtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CryptoClickerActivity.class);
            startActivity(intent);
        });

        Button RPJInfobtn = findViewById(id.RPJInfobtn);
        RPJInfobtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RadioPlayerJavaActivity.class);
            startActivity(intent);
        });

        Button KPDHJInfobtn = findViewById(R.id.KPDHJInfobtn);
        KPDHJInfobtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, KPDHJ_Activity.class);
            startActivity(intent);
        });

        Button ghInfobtn = findViewById(R.id.ghInfobtn);
        ghInfobtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GH_Activity.class);
            startActivity(intent);
        });

        Button deInfobtn = findViewById(id.deInfobtn);
        deInfobtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DE_MainActivity.class);
            startActivity(intent);
        });
    }
}