package com.cops.ntsf.dao;

import com.cops.ntsf.model.User;
import com.cops.ntsf.util.Database;

import java.sql.*;
//import java.time.LocalDate;

@SuppressWarnings("ALL")
public class UserDAO {
    private String userId;
    private String licenceNo;

    /**
     * Function to insert data into user table
     */
    public void insertUserInfo(User user) {
        Connection dbConn = Database.getConnection();

//        String sql = "INSERT INTO user (nic, email, mobile_no, user_type) VALUES (?, ?, ?, ?)";
        String sql = "INSERT INTO user (nic, email, mobile_no, user_type) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, user.getNic());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getMobileNo());
            preparedStatement.setString(4, user.getUserType().toString());

            preparedStatement.executeUpdate();

            this.getUserInfo(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void getUserInfo(User user) {
        Connection dbConn = Database.getConnection();

        String sql = "SELECT * FROM user WHERE nic = ? && user_type = ?";

        try {
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, user.getNic());
            preparedStatement.setString(2, user.getUserType().toString());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user.setUserId(resultSet.getString("user_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
