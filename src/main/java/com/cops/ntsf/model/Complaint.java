package com.cops.ntsf.model;

import com.cops.ntsf.dao.ComplaintDAO;
import org.json.JSONArray;

import java.sql.SQLException;
import java.util.ArrayList;

public class Complaint {
    private String user_id;
    private String title;
    private String description;
    private String complaint_no;

    public Complaint(String user_id, String title, String description, String complaint_no) {
        this.user_id = user_id;
        this.title = title;
        this.description = description;
        this.complaint_no = complaint_no;
    }

    public Complaint() {

    }

    public Complaint(String user_id) {
        this.user_id = user_id;
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

//    public void setDescription(String description) {
//        this.description = description;
//    }

    public void setComplaint_no(String complaint_no) {
        this.complaint_no = complaint_no;
    }

    public void complaintAdded() {
        ComplaintDAO complaintDAO = new ComplaintDAO();
        complaintDAO.insert(this);
    }

//    public JSONArray getComplaintDetails() {
//        ComplaintDAO complaintDAO = new ComplaintDAO();
//        JSONArray ComplaintDetails = complaintDAO.viewComplaintDetails();
//        return ComplaintDetails;
//    }

    public ArrayList<Complaint> getUserComplaintInfo() throws SQLException {
        ComplaintDAO complaintDAO = new ComplaintDAO();
        return complaintDAO.fetchUserComplaintInfo(this);
    }

//    public String getUserId() {
//        return userId;
//    }

    public JSONArray getComplaintsDetailsAsInvestigationOfficer() {
        ComplaintDAO complaintDAO = new ComplaintDAO();
        return complaintDAO.viewComplaintDetailsAsInvestigationOfficer();
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
