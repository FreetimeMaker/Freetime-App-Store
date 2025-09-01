package com.freetime.appstore;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.widget.Toast;
import androidx.core.content.ContextCompat;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import static android.content.Context.RECEIVER_NOT_EXPORTED;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private final Map<Long, String> downloadIdToFileNameMap = new HashMap<>();
    private AppInfo currentAppToDownload;

    private final ActivityResultLauncher<Intent> requestInstallPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    if (getPackageManager().canRequestPackageInstalls()) {
                        if (currentAppToDownload != null) {
                            startDownload(currentAppToDownload);
                        }
                    } else {
                        Toast.makeText(this, "Berechtigung zur Installation nicht erteilt.", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    

    private final BroadcastReceiver onDownloadComplete = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            if (downloadIdToFileNameMap.containsKey(id)) {
                String fileName = downloadIdToFileNameMap.get(id);
                Toast.makeText(MainActivity.this, "Download abgeschlossen: " + fileName, Toast.LENGTH_SHORT).show();
                installApk(fileName);
                downloadIdToFileNameMap.remove(id);
            }
        }
    };

// Receiver registration is handled in onCreate() below.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContextCompat.registerReceiver(
            this,
            onDownloadComplete,
            new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE),
            ContextCompat.RECEIVER_NOT_EXPORTED // Use _NOT_EXPORTED for security
        );

        RecyclerView appsRecyclerView = findViewById(R.id.appsRecyclerView);
        appsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<AppInfo> appList = createAppList();
        AppAdapter adapter = new AppAdapter(appList, this::checkInstallPermissionAndDownload);
        appsRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(onDownloadComplete);
    }

    private List<AppInfo> createAppList() {
        List<AppInfo> apps = new ArrayList<>();
        // Replace with direct APK URLs (not Google Drive share links!)
        apps.add(new AppInfo("GeoWeather", "https://github.com/FreetimeMaker/GeoWeather/releases/download/v1.0.0/GeoWeather.apk", "GeoWeather.apk"));
        apps.add(new AppInfo("Donation", "https://github.com/FreetimeMaker/Donation/releases/download/v1.0.0/Donation.apk", "Donation.apk"));
        apps.add(new AppInfo("Freetime App Store", "https://github.com/FreetimeMaker/Freetime-App-Store/releases/download/v1.0.1/FreetimeAppStore.apk", "FreetimeAppStore.apk"));
        return apps;
    }

    private void checkInstallPermissionAndDownload(AppInfo app) {
        this.currentAppToDownload = app;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (!getPackageManager().canRequestPackageInstalls()) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, Uri.parse("package:" + getPackageName()));
                requestInstallPermissionLauncher.launch(intent);
                return;
            }
        }
        startDownload(app);
    }

    // --- IMPLEMENTATION OF MISSING METHODS ---

    private void startDownload(AppInfo app) {
        String url = app.getUrl();
        String fileName = app.getFileName();

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setTitle(app.getName());
        request.setDescription("Lädt herunter " + app.getName());
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        long downloadId = manager.enqueue(request);
        downloadIdToFileNameMap.put(downloadId, fileName);

        Toast.makeText(this, "Download gestartet: " + fileName, Toast.LENGTH_SHORT).show();
    }

    private void installApk(String fileName) {
        File apkFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName);

        if (!apkFile.exists()) {
            Toast.makeText(this, "APK nicht gefunden: " + fileName, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri apkUri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            apkUri = FileProvider.getUriForFile(
                    this,
                    getApplicationContext().getPackageName() + ".fileprovider",
                    apkFile
            );
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            apkUri = Uri.fromFile(apkFile);
        }
        intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
    }
}