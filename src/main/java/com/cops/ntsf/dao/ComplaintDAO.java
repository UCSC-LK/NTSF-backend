package com.cops.ntsf.dao;

import com.cops.ntsf.model.Complaint;
import com.cops.ntsf.util.Database;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.util.ArrayList;

public class ComplaintDAO {
    public String insert(Complaint complaint) {
        Connection dbConn = null;
        try {
            dbConn = Database.getConnection();
            String sql = "INSERT into complaint (fine_no, user_id, title, description, footage) VALUES (?, ?, ?, ?)";
//            String sql = "INSERT into complaint (fine_no, user_id, title, description, footage) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, complaint.getFineNo());
            preparedStatement.setString(2, complaint.getUserId());
            preparedStatement.setString(3, complaint.getTitle());
            preparedStatement.setString(4, complaint.getDescription());
//            preparedStatement.setString(5, complaint.getFilePath());

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

//            resultSet.close();
//            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
//            if (dbConn != null) try {
//                dbConn.close();
//            } catch (Exception ignore) {
//            }
        }
        return null;
    }

    public ArrayList<Complaint> fetchUserComplaintInfo(Complaint complaint) throws SQLException {
        Connection dbConn = Database.getConnection();

        String sql = "SELECT * FROM complaint WHERE user_id = ?";

        PreparedStatement preparedStatement = dbConn.prepareStatement(sql);

        preparedStatement.setString(1, complaint.getUserId());

        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<Complaint> complaintsList = new ArrayList<Complaint>();

        while (resultSet.next()) {
            Complaint nextComplaint;
            nextComplaint = new Complaint(complaint.getUserId());
            nextComplaint.setComplaintNo(resultSet.getString("complaint_no"));
            nextComplaint.setFineNo(resultSet.getString("fine_no"));
            nextComplaint.setTitle(resultSet.getString("title"));
            nextComplaint.setDescription(resultSet.getString("description"));

            complaintsList.add(nextComplaint);
        }
        return complaintsList;
    }

    public JSONArray viewComplaintDetailsAsInvestigationOfficer() {
        Connection dbConn = null;
        JSONArray jsonArray = new JSONArray();
        try {
            dbConn = Database.getConnection();
            String sql = "SELECT * from complaint where status = 'pending'";
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String user_id = resultSet.getString("user_id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String complaint_no = resultSet.getString("complaint_no");

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("user_id", user_id);
                jsonObject.put("title", title);
                jsonObject.put("description", description);
                jsonObject.put("complaint_no", complaint_no);

                jsonArray.put(jsonObject);

                System.out.println("user_id: " + user_id);
                System.out.println("title: " + title);
                System.out.println("description: " + description);
                System.out.println("complaint_no: " + complaint_no);
            }
            resultSet.close();
            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public JSONArray getAppealsAsInvestigationOfficer(String police_station) {
        Connection dbConn = null;

        JSONArray jsonArray = new JSONArray();

        try{
            dbConn = Database.getConnection();
            String sql = "SELECT * FROM complaint JOIN fine on complaint.fine_no = fine.fine_no WHERE fine.police_station_name = ? AND complaint.status = 'pending'";

            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, police_station);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

                int complaint_no = resultSet.getInt("complaint_no");
                String user_id = String.valueOf(resultSet.getInt("user_id"));
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String fine_no = resultSet.getString("fine_no");


                JSONObject jsonObject = new JSONObject();
                jsonObject.put("complaint_no", complaint_no);
                jsonObject.put("user_id", user_id);
                jsonObject.put("title", title);
                jsonObject.put("description", description);
                jsonObject.put("fine_no", fine_no);

                jsonArray.put(jsonObject);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;

    }

    public boolean rejectAppealAsInvestigationOfficer(String complaint_no) {
        Connection dbConn = null;

        boolean alert = false;

        try{
            dbConn = Database.getConnection();
            String sql = "UPDATE complaint SET status = 'rejected' WHERE complaint_no = ?";
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, complaint_no);

            int result = preparedStatement.executeUpdate();

            if (result > 0){
                alert = true;
            }
            else {
                alert = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return alert;
    }

    /*
      Suraif's code for view complaints
     */
    //    public JSONArray viewComplaintDetails() {
//        Connection dbConn = null;
//        JSONArray jsonArray = new JSONArray();
//
//        try {
//            dbConn = Database.getConnection();
//            String sql = "SELECT * from complaint";
//            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//                String user_id = resultSet.getString("user_id");
//                String title = resultSet.getString("title");
//                String description = resultSet.getString("description");
//                String complaint_no = resultSet.getString("complaint_no");
//
//                JSONObject jsonObject = new JSONObject();
//                jsonObject.put("user_id", user_id);
//                jsonObject.put("title", title);
//                jsonObject.put("description", description);
//                jsonObject.put("complaint_no", complaint_no);
//
//                jsonArray.put(jsonObject);
//            }
//            resultSet.close();
//            preparedStatement.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (dbConn != null) try {
//                dbConn.close();
//            } catch (Exception ignore) {
//                ignore.printStackTrace();
//            }
//        }
//        return jsonArray;
//    }

}