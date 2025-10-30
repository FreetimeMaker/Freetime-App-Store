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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class GW_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gw);

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
            Intent intent = new Intent(GW_Activity.this, DE_MainActivity.class);
            startActivity(intent);
        });

        Button GWDbtn = findViewById(R.id.GWDbtn);
        GWDbtn.setOnClickListener(v -> {
            String fileUrl = "https://github.com/FreetimeMaker/GeoWeather/releases/download/v1.0.3/GeoWeather.apk"; // Direct APK link

            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(fileUrl));
            request.setTitle("GeoWeather Download");
            request.setDescription("Downloading GeoWeather app...");
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

            // For Android 7 to Android 10
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "GeoWeather.apk");
            } else {
                // For Android 10+
                request.setDestinationInExternalFilesDir(getApplicationContext(), Environment.DIRECTORY_DOWNLOADS, "GeoWeather.apk");
            }

            DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            downloadManager.enqueue(request);
        });

        Button GWSource = findViewById(R.id.GWSource);
        GWSource.setOnClickListener(v -> {
            String url = "https://github.com/FreetimeMaker/GeoWeather/"; // URL of the GitHub repository
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });

        Button MAOFM = findViewById(R.id.MAOFM);
        MAOFM.setOnClickListener(v -> {
            Intent intent = new Intent(GW_Activity.this, FM_Activity.class);
            startActivity(intent);
        });
    }

    private void downloadLatestApk() {
    String apiUrl = "https://api.github.com/repos/FreetimeMaker/GeoWeather/releases/latest";

    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder().url(apiUrl).build();

    client.newCall(request).enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            e.printStackTrace(); // Fehlerbehandlung
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            if (response.isSuccessful()) {
                String json = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String tag = jsonObject.getString("tag_name"); // z.B. "v1.0.3"
                    String downloadUrl = "https://github.com/FreetimeMaker/GeoWeather/releases/download/" + tag + "/GeoWeather.apk";

                    runOnUiThread(() -> startDownload(downloadUrl));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    });
}

private void startDownload(String url) {
    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
    request.setTitle("GeoWeather Download");
    request.setDescription("Downloading the newest Version...");
    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "GeoWeather.apk");
    } else {
        request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS, "GeoWeather.apk");
    }

    DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
    manager.enqueue(request);
}

}