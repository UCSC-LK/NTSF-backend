package com.cops.ntsf.dao;

import com.cops.ntsf.util.Database;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PolicemanDAO {
    public String getEmail(String police_id) {
        Connection dbConn = null;
        String email = null;

        try{
            dbConn = Database.getConnection();
            System.out.println("Hi from getEmail method in PolicemanAuthDAO.java");
            System.out.println("police_id: " + police_id);
            String sql = "SELECT email FROM policeman WHERE police_id = ?";
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, police_id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                email = resultSet.getString("email");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return email;
    }

    public JSONArray getProfileDetails(String profile_police_id) {
        Connection dbConn = null;

        JSONArray jsonArray = new JSONArray();

        try{
            dbConn = Database.getConnection();

            String sql = "SELECT * FROM policeman WHERE police_id = ?";
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, profile_police_id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String police_id = resultSet.getString("police_id");
                String nic = resultSet.getString("nic");
                String mobile_number = resultSet.getString("mobile_number");
                String email = resultSet.getString("email");
                String rank = resultSet.getString("rank");
                String position = resultSet.getString("position");
                String grade = resultSet.getString("grade");
                String police_station = resultSet.getString("police_station");
                String profile_picture = resultSet.getString("profile_picture");

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", name);
                jsonObject.put("police_id", police_id);
                jsonObject.put("nic", nic);
                jsonObject.put("mobile_number", mobile_number);
                jsonObject.put("email", email);
                jsonObject.put("rank", rank);
                jsonObject.put("position", position);
                jsonObject.put("grade", grade);
                jsonObject.put("police_station", police_station);
                jsonObject.put("profile_picture", profile_picture);

                jsonArray.put(jsonObject);

            }

            resultSet.close();
            preparedStatement.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }
}
