package com.freetime.appstore;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class DE_CatClickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_de_cat_clicker);

        Button backbtn = findViewById(R.id.backbtn);
        backbtn.setOnClickListener(v -> {
            Intent intent = new Intent(DE_CatClickerActivity.this, DE_MainActivity.class);
            startActivity(intent);
        });

        Button CatDbtn = findViewById(R.id.CatDbtn);
        CatDbtn.setOnClickListener(v -> {
            String fileUrl = "https://github.com/FreetimeMaker/Cat-Clicker/releases/download/v1.0.7/Cat.apk"; // Direct APK link

            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(fileUrl));
            request.setTitle("Cat Clicker herunterladen");
            request.setDescription("lade Cat Clicker Spiel herunter...");
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

            // For Android 7 to Android 10
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "Cat.apk");
            } else {
                // For Android 10+
                request.setDestinationInExternalFilesDir(getApplicationContext(), Environment.DIRECTORY_DOWNLOADS, "Cat.apk");
            }

            DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            downloadManager.enqueue(request);
        });

        Button CatSource = findViewById(R.id.CatSource);
        CatSource.setOnClickListener(v -> {
            String url = "https://github.com/FreetimeMaker/Cat-Clicker/"; // URL of the GitHub repository
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });

        Button MAOFM = findViewById(R.id.MAOFM);
        MAOFM.setOnClickListener(v -> {
            Intent intent = new Intent(DE_CatClickerActivity.this, FM_Activity.class);
            startActivity(intent);
        });
    }
}