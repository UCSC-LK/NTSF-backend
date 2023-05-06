package com.cops.ntsf.dao;

import com.cops.ntsf.util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PolicemanAuthDAO {

    //If the policeman is logging in for first time, return true, else false
    public boolean FirstLoginOrNotCheck(String police_id) {
        Connection dbConn = null;
        boolean alert = false; // Policeman not logging in for the first time

        try {
            dbConn = Database.getConnection();

            String sql = "SELECT login_flag FROM police_auth WHERE police_id = ?";
            System.out.println("Hi from FirstLoginOrNotCheck method in PolicemanAuthDAO.java");
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            System.out.println("kjfe kee ejr ekre" + police_id);
            preparedStatement.setString(1, police_id);

            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("resultSet " + resultSet);
            while (resultSet.next()) {
                System.out.print("Hi before getting login flag from resultset");
                int login_flag = resultSet.getInt("login_flag");
                System.out.println("login_flag in DAO: " + login_flag);
                System.out.println("login_flag is  set to 0");
                System.out.println("Hi after getting ===");

                if (login_flag == 0) { //If the policeman is logging in for first time, return true, else false
                    alert = true;
                } else {
                    alert = false;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("alert in DAO: " + alert);
        return alert;
    }

    public boolean changeFirstTimePassword(String police_id, String hashedPassword) {
        Connection dbConn = null;
        boolean passwordChanged = false;

        try {
            dbConn = Database.getConnection();
            System.out.println("Hi from changeFirstTimePassword method in PolicemanAuthDAO.java");
            System.out.println("police_id: " + police_id);
            System.out.println("hashedPassword: " + hashedPassword);
            String sql = "UPDATE police_auth SET password = ?, otp = '', login_flag = ? WHERE police_id = ?";
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, hashedPassword);
            preparedStatement.setInt(2, 1);
            preparedStatement.setString(3, police_id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                passwordChanged = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return passwordChanged;
    }
}
