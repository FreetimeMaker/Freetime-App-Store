package com.freetime.appstore.german.games.freetime;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.freetime.appstore.R;
import com.freetime.appstore.developers.freetime.FM_Activity;
import com.freetime.appstore.german.main.DE_MainActivity;

import org.json.JSONObject;

import java.io.File;

import okhttp3.OkHttpClient;


public class PLC2Activity extends AppCompatActivity {

    private TextView versionText;
    private long downloadId;
    private BroadcastReceiver downloadReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plc2);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        );

        Button backbtn = findViewById(R.id.backbtn);
        backbtn.setOnClickListener(v -> {
            Intent intent = new Intent(PLC2Activity.this, DE_MainActivity.class);
            startActivity(intent);
        });

        versionText = findViewById(R.id.versionText);

        Button PLC2Dbtn = findViewById(R.id.PLC2Dbtn);
        PLC2Dbtn.setOnClickListener(v -> downloadLatestApk());

        downloadReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                if (id == downloadId) {
                    Toast.makeText(context, "Download abgeschlossen. Installiere...", Toast.LENGTH_SHORT).show();
                    installApk();
                }
            }
        };
        registerReceiver(downloadReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        Button PLC2Source = findViewById(R.id.PLC2Source);
        PLC2Source.setOnClickListener(v -> {
            String url = "https://github.com/FreetimeMaker/PLC2/"; // URL of the GitHub repository
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });

        Button MAOFM = findViewById(R.id.MAOFM);
        MAOFM.setOnClickListener(v -> {
            Intent intent = new Intent(PLC2Activity.this, FM_Activity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (downloadReceiver != null) unregisterReceiver(downloadReceiver);
    }

    private void downloadLatestApk() {
        String apiUrl = "https://api.github.com/repos/FreetimeMaker/PLC2/releases/latest";

        OkHttpClient client = new OkHttpClient();
        // ...existing code (GET-Anfrage an apiUrl, Version anzeigen)...
    }

    private void startDownload(String url) {
        String fileUrl = url; // Direct APK link

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(fileUrl));
        request.setTitle("PLC 2.0 Download");
        request.setDescription("Downloading PLC 2.0 Game...");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

        // For Android 7 to Android 10
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "PLC2.0.apk");
        } else {
            // For Android 10+
            request.setDestinationInExternalFilesDir(getApplicationContext(), Environment.DIRECTORY_DOWNLOADS, "PLC2.0.apk");
        }

        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        downloadId = downloadManager.enqueue(request);
    }

    private void installApk() {
        File apkFile = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "PLC2.0.apk");
        // ...existing code (Intent zum Installieren der APK)...
    }
}