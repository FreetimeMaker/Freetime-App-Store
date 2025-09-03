package com.freetime.appstore;

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
}