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

        String sql = "SELECT * FROM driver WHERE license_no = ?";

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

    public void fetchDriverInfo(Driver driver) {
        Connection dbConn = Database.getConnection();

        String sql = "SELECT * FROM driver WHERE nic = ?";

        try {
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, driver.getNic());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                driver.setLicenseNo(resultSet.getString("license_no"));
                driver.setExpireDate(resultSet.getDate("issue_date"));
                driver.setIssueDate(resultSet.getDate("expire_date"));
                driver.setAdministrativeNo(resultSet.getString("administrative_no"));
                driver.setHoldersSignature(resultSet.getString("holders_signature"));
                driver.setVehicleCategories(resultSet.getString("vehicle_categories"));
                driver.setName(resultSet.getString("name"));
                driver.setDob(resultSet.getDate("dob"));
                driver.setAddress(resultSet.getString("address"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String fetchNICByLicenseNo(String licenseNo) {
        Connection dbConn = Database.getConnection();
        String nic = null;
        String sql = "SELECT nic FROM driver WHERE license_no = ?";

        try {
            System.out.println("License Number entered to Query: " + licenseNo);
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, licenseNo);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                nic = resultSet.getString("nic");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nic;
    }
}
