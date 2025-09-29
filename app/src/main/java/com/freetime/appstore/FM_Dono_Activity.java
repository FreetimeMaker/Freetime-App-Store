package com.freetime.appstore;

import static com.freetime.appstore.R.*;

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


public class FM_Dono_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_fm_dono);

        Button btnDonateOxa = findViewById(R.id.btnDonateOxa);
        btnDonateOxa.setOnClickListener(v -> {
            Intent intent = new Intent(FM_Dono_Activity.this, OxaPayActivity.class);
            startActivity(intent);
        });

        Button btnDonateCoin = findViewById(R.id.btnDonateCoin);
        btnDonateCoin.setOnClickListener(v -> {
            Intent intent = new Intent(FM_Dono_Activity.this, CoinbaseActivity.class);
            startActivity(intent);
        });

        Button btnDonateBTC = findViewById(R.id.btnDonateBTC);
        btnDonateBTC.setOnClickListener(v -> {
            Intent intent = new Intent(FM_Dono_Activity.this, BitcoinActivity.class);
            startActivity(intent);
        });

        Button btnDonateETH = findViewById(R.id.btnDonateETH);
        btnDonateETH.setOnClickListener(v -> {
            Intent intent = new Intent(FM_Dono_Activity.this, EthereumActivity.class);
            startActivity(intent);
        });

        Button btnDonateUSDT = findViewById(R.id.btnDonateUSDT);
        btnDonateUSDT.setOnClickListener(v -> {
            Intent intent = new Intent(FM_Dono_Activity.this, USDT_Activity.class);
            startActivity(intent);
        });

        Button btnDonateUSDC = findViewById(R.id.btnDonateUSDC);
        btnDonateUSDC.setOnClickListener(v -> {
            Intent intent = new Intent(FM_Dono_Activity.this, USDC_Activity.class);
            startActivity(intent);
        });

        Button btnDonateSHIB = findViewById(R.id.btnDonateSHIB);
        btnDonateSHIB.setOnClickListener(v -> {
            Intent intent = new Intent(FM_Dono_Activity.this, ShibActivity.class);
            startActivity(intent);
        });

        Button btnDonateDOGE = findViewById(R.id.btnDonateDOGE);
        btnDonateDOGE.setOnClickListener(v -> {
            Intent intent = new Intent(FM_Dono_Activity.this, DogeActivity.class);
            startActivity(intent);
        });

        Button btnDonateTRON = findViewById(R.id.btnDonateTRON);
        btnDonateTRON.setOnClickListener(v -> {
            Intent intent = new Intent(FM_Dono_Activity.this, TronActivity.class);
            startActivity(intent);
        });

        Button btnDonateLTC = findViewById(R.id.btnDonateLTC);
        btnDonateLTC.setOnClickListener(v -> {
            Intent intent = new Intent(FM_Dono_Activity.this, LTC_Activity.class);
            startActivity(intent);
        });

        Button btnDonateBNB = findViewById(R.id.btnDonateBNB);
        btnDonateBNB.setOnClickListener(v -> {
            Intent intent = new Intent(FM_Dono_Activity.this, BNB_Activity.class);
            startActivity(intent);
        });

        Button btnDonateSOL = findViewById(R.id.btnDonateSOL);
        btnDonateSOL.setOnClickListener(v -> {
            Intent intent = new Intent(FM_Dono_Activity.this, SOL_Activity.class);
            startActivity(intent);
        });

        Button btnDonatePEPE = findViewById(R.id.btnDonatePEPE);
        btnDonatePEPE.setOnClickListener(v -> {
            Intent intent = new Intent(FM_Dono_Activity.this, PEPE_Activity.class);
            startActivity(intent);
        });

        Button btnGoBack = findViewById(R.id.btnGoBack);
        btnGoBack.setOnClickListener(v -> {
            Intent intent = new Intent(FM_Dono_Activity.this, FM_Activity.class);
            startActivity(intent);
        });
    }
}