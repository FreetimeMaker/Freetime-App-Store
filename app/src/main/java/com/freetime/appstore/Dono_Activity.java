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


public class Dono_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dono);

        Button backbtn = findViewById(R.id.backbtn);
        backbtn.setOnClickListener(v -> {
            Intent intent = new Intent(Dono_Activity.this, MainActivity.class);
            startActivity(intent);
        });

        Button DonationDbtn = findViewById(R.id.DonationDbtn);
        DonationDbtn.setOnClickListener(v -> {
            String fileUrl = "https://github.com/FreetimeMaker/Donation/releases/download/v1.0.3/Donation.apk"; // Direct APK link

            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(fileUrl));
            request.setTitle("Donation Download");
            request.setDescription("Downloading Donation app...");
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

            // For Android 7 to Android 10
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "Donation.apk");
            } else {
                // For Android 10+
                request.setDestinationInExternalFilesDir(getApplicationContext(), Environment.DIRECTORY_DOWNLOADS, "Donation.apk");
            }

            DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            downloadManager.enqueue(request);
        });

        Button DonationSource = findViewById(R.id.DonationSource);
        DonationSource.setOnClickListener(v -> {
            String url = "https://github.com/FreetimeMaker/Donation/"; // URL of the GitHub repository
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });

        Button MAOFM = findViewById(R.id.MAOFM);
        MAOFM.setOnClickListener(v -> {
            Intent intent = new Intent(Dono_Activity.this, FM_Activity.class);
            startActivity(intent);
        });
    }
}