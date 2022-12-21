package com.cops.ntsf.model;

public class Complaint {
    private String user_id;
    private String title;
    private String description;
    private String complaint_no;

    public Complaint(String user_id, String title, String description, String complaint_no)
    {
        this.user_id = user_id;
        this.title = title;
        this.description = description;
        this.complaint_no = complaint_no;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getComplaint_no() {
        return complaint_no;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setComplaint_no(String complaint_no) {
        this.complaint_no = complaint_no;
    }
}
