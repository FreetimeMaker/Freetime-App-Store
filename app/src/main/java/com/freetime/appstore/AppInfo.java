package com.freetime.appstore;

import java.net.MalformedURLException;
import java.net.URL;

public class AppInfo {
    private final String name;
    private final String url;
    private final String fileName;

    public AppInfo(String name, String url, String fileName) {
        this.name = name;
        this.url = url;
        this.fileName = fileName;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setUrl(String downloadUrl) throws MalformedURLException {
        new URL("https://github.com/FreetimeMaker/Freetime-App-Store/releases/latest");
    }
}