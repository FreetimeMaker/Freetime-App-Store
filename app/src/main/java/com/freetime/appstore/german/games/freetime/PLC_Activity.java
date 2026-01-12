package com.freetime.appstore.german.games.freetime;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.freetime.appstore.R;
import com.freetime.appstore.developers.freetime.FM_Activity;
import com.freetime.appstore.german.main.DE_MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PLC_Activity extends AppCompatActivity {

    private TextView versionText;
    private long downloadId;
    private BroadcastReceiver downloadReceiver;
    private String latestDownloadUrl; // Speichere die URL für späteren Download

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plc);

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
            Intent intent = new Intent(PLC_Activity.this, DE_MainActivity.class);
            startActivity(intent);
        });

        versionText = findViewById(R.id.versionText);
        loadLatestReleaseData(); // Daten beim Start laden und anzeigen

        Button PLCDbtn = findViewById(R.id.PLCDbtn);
        PLCDbtn.setOnClickListener(v -> {
            if (latestDownloadUrl != null) {
                startDownload(latestDownloadUrl);
            } else {
                Toast.makeText(this, "Keine Download-URL verfügbar", Toast.LENGTH_SHORT).show();
            }
        });

        // Receiver für Download-Abschluss registrieren
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

        Button PLCSource = findViewById(R.id.PLCSource);
        PLCSource.setOnClickListener(v -> {
            String url = "https://github.com/FreetimeMaker/PLC/"; // URL of the GitHub repository
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });

        Button MAOFM = findViewById(R.id.MAOFM);
        MAOFM.setOnClickListener(v -> {
            Intent intent = new Intent(PLC_Activity.this, FM_Activity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (downloadReceiver != null) {
            unregisterReceiver(downloadReceiver);
        }
    }

    private void loadLatestReleaseData() {
        String apiUrl = "https://api.github.com/repos/FreetimeMaker/PLC/releases/latest";

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(apiUrl).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(() -> versionText.setText("Fehler beim Laden der Daten"));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String json = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(json);
                        String tag = jsonObject.getString("tag_name");
                        String description = jsonObject.optString("body", "Keine Beschreibung verfügbar");
                        latestDownloadUrl = "https://github.com/FreetimeMaker/PLC/releases/download/" + tag + "/PLC.apk";

                        runOnUiThread(() -> versionText.setText("Version: " + tag + "\nBeschreibung: " + description));
                    } catch (JSONException e) {
                        runOnUiThread(() -> versionText.setText("Fehler beim Parsen"));
                    }
                }
            }
        });
    }

    private void startDownload(String url) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setTitle("PLC Download");
        request.setDescription("Downloading PLC Game...");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "PLC.apk");
        } else {
            request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS, "PLC.apk");
        }

        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        downloadId = manager.enqueue(request);
    }

    private void installApk() {
        File apkFile = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "PLC.apk");
        if (apkFile.exists()) {
            Uri apkUri = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", apkFile);
            Intent intent = new Intent(Intent.ACTION_INSTALL_PACKAGE);
            intent.setData(apkUri);
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(intent);
        } else {
            Toast.makeText(this, "APK-Datei nicht gefunden", Toast.LENGTH_SHORT).show();
        }
    }
}