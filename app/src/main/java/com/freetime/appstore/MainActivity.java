package com.freetime.appstore;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
                        Toast.makeText(this, "Permission for Installing not given.", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    private final BroadcastReceiver onDownloadComplete = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            if (downloadIdToFileNameMap.containsKey(id)) {
                String fileName = downloadIdToFileNameMap.get(id);
                Toast.makeText(MainActivity.this, "Download completed: " + fileName, Toast.LENGTH_SHORT).show();
                installApk(fileName);
                downloadIdToFileNameMap.remove(id);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContextCompat.registerReceiver(
                this,
                onDownloadComplete,
                new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE),
                ContextCompat.RECEIVER_NOT_EXPORTED
        );

        RecyclerView appsRecyclerView = findViewById(R.id.appsRecyclerView);
        appsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<AppInfo> appList = createAppList();
        AppAdapter adapter = new AppAdapter(appList, this::checkGitHubForUpdate);
        appsRecyclerView.setAdapter(adapter);
    }

    private static String getAppVersionName(Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pInfo = pm.getPackageInfo(context.getPackageName(), 0);
            return pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "0.0.0";
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(onDownloadComplete);
    }

    private List<AppInfo> createAppList() {
        List<AppInfo> apps = new ArrayList<>();
        // Name, GitHub Owner, Repo, Dateiname
        apps.add(new AppInfo("GeoWeather", "FreetimeMaker",  "GeoWeather.apk"));
        apps.add(new AppInfo("Donation", "FreetimeMaker", "Donation.apk"));
        apps.add(new AppInfo("Freetime App Store", "FreetimeMaker", "FAS.apk"));
        return apps;
    }

    private void checkGitHubForUpdate(AppInfo app) {
        this.currentAppToDownload = app;
        new Thread(() -> {
            try {
                URL url = new URL("https://api.github.com/repos/FreetimeMaker/Freetime-App-Store/releases/latest");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Accept", "application/vnd.github.v3+json");

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) sb.append(line);
                reader.close();

                JSONObject release = new JSONObject(sb.toString());
                String latestVersion = release.getString("tag_name").replace("v", "");

                // Für eigene App: lokale Version prüfen
                String currentVersion = app.getName().equals("Freetime App Store")
                        ? getAppVersionName(this)
                        : "0.0.0"; // Bei Fremd-Apps ggf. anders prüfen

                if (latestVersion.compareTo(currentVersion) > 0) {
                    JSONArray assets = release.getJSONArray("assets");
                    for (int i = 0; i < assets.length(); i++) {
                        JSONObject asset = assets.getJSONObject(i);
                        if (asset.getString("name").endsWith(".apk")) {
                            String downloadUrl = asset.getString("browser_download_url");
                            app.setUrl(downloadUrl);
                            runOnUiThread(() -> checkInstallPermissionAndDownload(app));
                            return;
                        }
                    }
                } else {
                    runOnUiThread(() -> Toast.makeText(this, app.getName() + " is up to date", Toast.LENGTH_SHORT).show());
                }

            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(this, "Update-Check failed", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }

    private void checkInstallPermissionAndDownload(AppInfo app) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (!getPackageManager().canRequestPackageInstalls()) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, Uri.parse("package:" + getPackageName()));
                requestInstallPermissionLauncher.launch(intent);
                return;
            }
        }
        startDownload(app);
    }

    private void startDownload(AppInfo app) {
        String url = app.getUrl();
        String fileName = app.getFileName();

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setTitle(app.getName());
        request.setDescription("Downloading " + app.getName() + " now");
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        long downloadId = manager.enqueue(request);
        downloadIdToFileNameMap.put(downloadId, fileName);

        Toast.makeText(this, "Download started: " + fileName, Toast.LENGTH_SHORT).show();
    }

    private void installApk(String fileName) {
        File apkFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName);

        if (!apkFile.exists()) {
            Toast.makeText(this, "APK not found: " + fileName, Toast.LENGTH_SHORT).show();
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
