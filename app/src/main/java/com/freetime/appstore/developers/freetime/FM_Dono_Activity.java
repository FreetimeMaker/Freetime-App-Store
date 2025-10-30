package com.freetime.appstore.developers.freetime;

import static com.freetime.appstore.R.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.freetime.appstore.donations.freetime.BNB_Activity;
import com.freetime.appstore.donations.freetime.BitcoinActivity;
import com.freetime.appstore.donations.freetime.CoinbaseActivity;
import com.freetime.appstore.donations.freetime.DogeActivity;
import com.freetime.appstore.donations.freetime.EthereumActivity;
import com.freetime.appstore.donations.freetime.LTC_Activity;
import com.freetime.appstore.donations.freetime.OxaPayActivity;
import com.freetime.appstore.donations.freetime.PEPE_Activity;
import com.freetime.appstore.R;
import com.freetime.appstore.donations.freetime.SOL_Activity;
import com.freetime.appstore.donations.freetime.ShibActivity;
import com.freetime.appstore.donations.freetime.TronActivity;
import com.freetime.appstore.donations.freetime.USDC_Activity;
import com.freetime.appstore.donations.freetime.USDT_Activity;


public class FM_Dono_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_fm_dono);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        );

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