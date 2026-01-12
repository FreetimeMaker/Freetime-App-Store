package com.freetime.appstore.german.apps.freetime;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.freetime.appstore.R;
import com.freetime.appstore.developers.freetime.FM_Activity;
import com.freetime.appstore.german.main.DE_MainActivity;


public class Dono_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dono);

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
            Intent intent = new Intent(Dono_Activity.this, DE_MainActivity.class);
            startActivity(intent);
        });

        Button DonationDbtn = findViewById(R.id.DonationDbtn);
        DonationDbtn.setOnClickListener(v -> {
            String fileUrl = "https://api.github.com/repos/FreetimeMaker/Donation/releases/latest"; // Direct APK link

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