package com.cops.ntsf.model;

import com.cops.ntsf.dao.ComplaintDAO;
import org.json.JSONArray;

import java.sql.SQLException;
import java.util.ArrayList;

public class Complaint {
    private String userId;
    private String title;
    private String description;
    private String complaintNo;

    public Complaint(String userId, String title, String description, String complaintNo) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.complaintNo = complaintNo;
    }

    public Complaint() {
    }

    public Complaint(String userId) {
        this.userId = userId;
    }

    public Complaint(String title, String description, String userId) {
        this.title = title;
        this.description = description;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getComplaintNo() {
        return complaintNo;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setComplaintNo(String complaintNo) {
        this.complaintNo = complaintNo;
    }

    public void complaintAdded() {
        ComplaintDAO complaintDAO = new ComplaintDAO();
        complaintDAO.insert(this);
    }

    public ArrayList<Complaint> getUserComplaintInfo() throws SQLException {
        ComplaintDAO complaintDAO = new ComplaintDAO();
        return complaintDAO.fetchUserComplaintInfo(this);
    }

    public JSONArray getComplaintsDetailsAsInvestigationOfficer() {
        ComplaintDAO complaintDAO = new ComplaintDAO();
        return complaintDAO.viewComplaintDetailsAsInvestigationOfficer();
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
