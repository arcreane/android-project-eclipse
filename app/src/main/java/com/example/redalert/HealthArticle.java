package com.example.redalert;

public class HealthArticle {
    private String title;
    private String description;

    public HealthArticle(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
