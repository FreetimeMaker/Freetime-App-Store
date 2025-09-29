package com.freetime.appstore;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.freetime.appstore.FM_Dono_Activity;
import com.freetime.appstore.R;

public class EthereumActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ethereum);

        WebView webView = findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://ncwallet.net/pay/86fremd");

        Button btnGoBack = findViewById(R.id.btnGoBack);
        btnGoBack.setOnClickListener(v -> {
            Intent intent = new Intent(EthereumActivity.this, FM_Dono_Activity.class);
            startActivity(intent);
        });
    }
}