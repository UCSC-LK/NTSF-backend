package com.cops.ntsf.model;

import com.cops.ntsf.dao.ComplaintDAO;
import org.json.JSONArray;

import java.sql.SQLException;
import java.util.ArrayList;

public class Complaint {
    private String filePath;
    private String fineNo;
    private String userId;
    private String title;
    private String description;
    private String complaintNo;

    public Complaint(String fineNo, String userId, String title, String description) {
        this.fineNo = fineNo;
        this.userId = userId;
        this.title = title;
        this.description = description;
    }

    public Complaint() {
    }

    public Complaint(String userId) {
        this.userId = userId;
    }

    public Complaint(String fineNo, String userId, String title, String description, String filePath) {
        this.fineNo = fineNo;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.filePath = filePath;
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

    public String getFineNo() {
        return fineNo;
    }

    public void setFineNo(String fineNo) {
        this.fineNo = fineNo;
    }

    public String getFilePath() {
        return filePath;
    }

    public JSONArray fetchAppealsAsInvestigationOfficer(String police_station) {
        ComplaintDAO complaintDAO = new ComplaintDAO();
        return complaintDAO.getAppealsAsInvestigationOfficer(police_station);
    }

    public boolean rejectAppealAsInvestigationOfficer(String complaint_no) {
        ComplaintDAO complaintDAO = new ComplaintDAO();
        return complaintDAO.rejectAppealAsInvestigationOfficer(complaint_no);
    }
}
