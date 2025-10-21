package com.freetime.appstore;

import static com.freetime.appstore.R.id;
import static com.freetime.appstore.R.layout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class DE_MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_de_main);

        Button gwInfobtn = findViewById(id.gwInfobtn);
        gwInfobtn.setOnClickListener(v -> {
            Intent intent = new Intent(DE_MainActivity.this, GW_Activity.class);
            startActivity(intent);
        });

        Button donoInfobtn = findViewById(id.donoInfobtn);
        donoInfobtn.setOnClickListener(v -> {
            Intent intent = new Intent(DE_MainActivity.this, Dono_Activity.class);
            startActivity(intent);
        });

        Button fasInfobtn = findViewById(id.fasInfobtn);
        fasInfobtn.setOnClickListener(v -> {
            Intent intent = new Intent(DE_MainActivity.this, FAS_Activity.class);
            startActivity(intent);
        });

        Button catInfobtn = findViewById(id.catInfobtn);
        catInfobtn.setOnClickListener(v -> {
            Intent intent = new Intent(DE_MainActivity.this, CatClickerActivity.class);
            startActivity(intent);
        });

        Button PLCInfobtn = findViewById(id.PLCInfobtn);
        PLCInfobtn.setOnClickListener(v -> {
            Intent intent = new Intent(DE_MainActivity.this, PLC_Activity.class);
            startActivity(intent);
        });

        Button plc2Infobtn = findViewById(id.plc2Infobtn);
        plc2Infobtn.setOnClickListener(v -> {
            Intent intent = new Intent(DE_MainActivity.this, PLC2Activity.class);
            startActivity(intent);
        });

        Button platInfobtn = findViewById(id.platInfobtn);
        platInfobtn.setOnClickListener(v -> {
            Intent intent = new Intent(DE_MainActivity.this, PlatformerActivity.class);
            startActivity(intent);
        });

        Button osInfobtn = findViewById(id.osInfobtn);
        osInfobtn.setOnClickListener(v -> {
            Intent intent = new Intent(DE_MainActivity.this, OS_Activity.class);
            startActivity(intent);
        });

        Button cryptoInfobtn = findViewById(id.cryptoInfobtn);
        cryptoInfobtn.setOnClickListener(v -> {
            Intent intent = new Intent(DE_MainActivity.this, CryptoClickerActivity.class);
            startActivity(intent);
        });

        Button rpjInfobtn = findViewById(R.id.rpjInfobtn);
        rpjInfobtn.setOnClickListener(v -> {
            Intent intent = new Intent(DE_MainActivity.this, RadioPlayerJavaActivity.class);
            startActivity(intent);
        });

        Button ghInfobtn = findViewById(id.ghInfobtn);
        ghInfobtn.setOnClickListener(v -> {
            Intent intent = new Intent(DE_MainActivity.this, GH_Activity.class);
            startActivity(intent);
        });

        Button enInfobtn = findViewById(id.enInfobtn);
        enInfobtn.setOnClickListener(v -> {
            Intent intent = new Intent(DE_MainActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}