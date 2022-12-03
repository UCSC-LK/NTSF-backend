package com.cops.ntsf.dao;

import com.cops.ntsf.model.User;
import com.cops.ntsf.util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        String sql = "INSERT INTO user (name, address, nic, email, mobile_no, user_type) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(1, user.getAddress());
            preparedStatement.setString(1, user.getNic());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getMobileNo());
            preparedStatement.setString(4, user.getUserType().toString());

            preparedStatement.executeUpdate();

            this.fetchUserInfo(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void fetchUserInfo(User user) {
        Connection dbConn = Database.getConnection();

        String sql = "SELECT * FROM user WHERE nic = ? && user_type = ?";

        try {
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, user.getNic());
            preparedStatement.setString(2, user.getUserType().toString());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user.setUserId(resultSet.getString("user_id"));
                user.setName(resultSet.getString("name"));
                user.setAddress(resultSet.getString("address"));
                user.setNic(resultSet.getString("nic"));
                user.setEmail(resultSet.getString("email"));
                user.setMobileNo(resultSet.getString("mobile_no"));
                user.setUserType(resultSet.getString("user_type"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
