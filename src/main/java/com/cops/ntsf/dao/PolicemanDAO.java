package com.cops.ntsf.dao;

import com.cops.ntsf.util.Database;

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
}
