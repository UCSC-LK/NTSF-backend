package com.cops.ntsf.dao;

import com.cops.ntsf.model.Vehicle;
import com.cops.ntsf.util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleDAO {

    public void getVehicleFromVehicleNo(Vehicle vehicle) {
        Connection dbConn = Database.getConnection();

        String sql = "SELECT * FROM vehicle WHERE vehicle_no = ?";

        try {
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, vehicle.getVehicleNo());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // driver.setLicenceExpiryDate(LocalDate.parse(resultSet.getString("licence_expire_date")));
                // driver.setLicenceIssueDate(LocalDate.parse(resultSet.getString("licence_issue_date")));
                vehicle.setUserId(resultSet.getString("user_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertVehicleInfo(Vehicle vehicle) {
        Connection dbConn = Database.getConnection();

        String sql = "INSERT INTO vehicle(user_id, vehicle_no) VALUES (?, ?)";

        try {
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, vehicle.getUserId());
            preparedStatement.setString(2, vehicle.getVehicleNo());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
