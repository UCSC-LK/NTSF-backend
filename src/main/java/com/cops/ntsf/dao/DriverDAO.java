package com.cops.ntsf.dao;

import com.cops.ntsf.model.Driver;
import com.cops.ntsf.util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DriverDAO {
    /**
     * Function to query driver table with license no
     */
    public void getDriverFromLicense(Driver driver) {
        Connection dbConn = Database.getConnection();

        String sql = "SELECT * FROM driver WHERE licence_no = ?";

        try {
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, driver.getLicenceNo());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                driver.setUserId(resultSet.getString("user_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
//
//    public void insertDriverInfo(Driver driver) {
//        Connection dbConn = Database.getConnection();
//
//        String sql = "INSERT INTO driver(user_id, licence_no) VALUES (?, ?)";
//
//        try {
//            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
//            preparedStatement.setString(1, driver.getUserId());
//            preparedStatement.setString(2, driver.getLicenceNo());
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public void fetchDriverInfo(Driver driver) {
        Connection dbConn = Database.getConnection();

        String sql = "SELECT * FROM driver WHERE nic = ?";

        try {
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, driver.getNic());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                driver.setLicenceNo(resultSet.getString("licence_no"));
                driver.setExpireDate(resultSet.getDate("issue_date"));
                driver.setExpireDate(resultSet.getDate("expire_date"));
                driver.setAdministrativeNo(resultSet.getString("administrative_no"));
                driver.setHoldersSignature(resultSet.getString("holders_signature"));
                driver.setAdministrativeNo(resultSet.getString("vehicle_categories"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
