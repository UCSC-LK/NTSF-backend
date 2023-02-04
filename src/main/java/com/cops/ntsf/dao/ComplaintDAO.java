package com.cops.ntsf.dao;

import com.cops.ntsf.model.Complaint;
import com.cops.ntsf.util.Database;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;

public class ComplaintDAO {
    public String insert(Complaint complaint) {
        Connection dbConn = null;
        try {
            dbConn = Database.getConnection();
            String sql = "INSERT into complaint (user_id, title, description, complaint_no) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, complaint.getUser_id());
            preparedStatement.setString(2, complaint.getTitle());
            preparedStatement.setString(3, complaint.getDescription());
            preparedStatement.setString(4, complaint.getComplaint_no());

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (dbConn != null) try {
                dbConn.close();
            } catch (Exception ignore) {
            }
        }
        return null;
    }

    public JSONArray viewComplaintDetails() {
        Connection dbConn = null;
        JSONArray jsonArray = new JSONArray();

        try {
            dbConn = Database.getConnection();
            String sql = "SELECT * from complaint";
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
            }
            resultSet.close();
            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (dbConn != null) try {
                dbConn.close();
            } catch (Exception ignore) {
                ignore.printStackTrace();
            }
        }
        return jsonArray;
    }
}