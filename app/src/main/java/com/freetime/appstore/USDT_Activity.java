package com.freetime.appstore;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.freetime.appstore.FM_Dono_Activity;

public class USDT_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usdt);

        WebView webView = findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://ncwallet.net/pay/19tacit");

        Button btnGoBack = findViewById(R.id.btnGoBack);
        btnGoBack.setOnClickListener(v -> {
            Intent intent = new Intent(USDT_Activity.this, FM_Dono_Activity.class);
            startActivity(intent);
        });
    }
}