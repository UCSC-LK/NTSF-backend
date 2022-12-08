package com.cops.ntsf.model;

public class Complaint {
    private String title;
    private String description;

    public Complaint(String title, String description) {
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
