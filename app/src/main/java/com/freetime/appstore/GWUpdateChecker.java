package com.freetime.appstore;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GWUpdateChecker {

    public interface UpdateListener {
        void onUpdateAvailable(String downloadUrl);
    }

    public static void checkForUpdate(String currentVersion, String user, String repo, UpdateListener listener) {
        OkHttpClient client = new OkHttpClient();
        String url = "https://api.github.com/repos/" + user + "/" + repo + "/releases/latest";

        new Thread(() -> {
            try (Response response = client.newCall(new Request.Builder().url(url).build()).execute()) {
                if (response.isSuccessful() && response.body() != null) {
                    JSONObject json = new JSONObject(response.body().string());
                    String latestVersion = json.getString("tag_name");
                    JSONArray assets = json.getJSONArray("assets");
                    String downloadUrl = assets.getJSONObject(0).getString("browser_download_url");

                    if (!latestVersion.equals(currentVersion)) {
                        listener.onUpdateAvailable(downloadUrl);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
