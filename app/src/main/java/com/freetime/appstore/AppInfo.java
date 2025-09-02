package com.freetime.appstore;

public class AppInfo {
    private String name;
    private String url;
    private String fileName;
    private String description;

    public AppInfo(String name, String url, String fileName, String description) {
        this.name = name;
        this.url = url;
        this.fileName = fileName;
        this.description = description;
    }

    // Getters
    public String getName() { return name; }
    public String getUrl() { return url; }
    public String getFileName() { return fileName; }
    public String getDescription() { return description; }
}
