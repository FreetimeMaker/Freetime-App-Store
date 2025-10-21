package com.freetime.appstore;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;


public class RadioPlayerJavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_player_java);

        Button backbtn = findViewById(R.id.backbtn);
        backbtn.setOnClickListener(v -> {
            Intent intent = new Intent(RadioPlayerJavaActivity.this, MainActivity.class);
            startActivity(intent);
        });

        Button RPJDownBtn = findViewById(R.id.RPJDownBtn);
        RPJDownBtn.setOnClickListener(v -> {
            String fileUrl = "https://github.com/FreetimeMaker/Radio-Player-Java/releases/download/v1.0.2/Radio-Player.apk"; // Direct APK link

            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(fileUrl));
            request.setTitle("Radio Player (Java) Download");
            request.setDescription("Downloading Radio Player (Java)...");
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

            // For Android 7 to Android 10
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "Radio-Player.apk");
            } else {
                // For Android 10+
                request.setDestinationInExternalFilesDir(getApplicationContext(), Environment.DIRECTORY_DOWNLOADS, "Radio-Player.apk");
            }

            DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            downloadManager.enqueue(request);
        });

        Button RPJSource = findViewById(R.id.RPJSource);
        RPJSource.setOnClickListener(v -> {
            String url = "https://github.com/FreetimeMaker/Radio-Player-Java/"; // URL of the GitHub repository
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });

        Button MAOFM = findViewById(R.id.MAOFM);
        MAOFM.setOnClickListener(v -> {
            Intent intent = new Intent(RadioPlayerJavaActivity.this, FM_Activity.class);
            startActivity(intent);
        });
    }
}