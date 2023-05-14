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

        String sql = "SELECT * FROM vehicle WHERE register_no = ?";

        try {
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, vehicle.getVehicleNo());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                vehicle.setUserId(resultSet.getString("user_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertVehicleInfo(Vehicle vehicle) {
        Connection dbConn = Database.getConnection();

        String sql = "INSERT INTO vehicle(nic, register_no) VALUES (?, ?)";

        try {
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, vehicle.getNic());
            preparedStatement.setString(2, vehicle.getVehicleNo());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void getVehicleFromNic(Vehicle vehicle) {
        Connection dbConn = Database.getConnection();

        String sql = "SELECT * FROM vehicle WHERE nic = ?";

        try {
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, vehicle.getNic());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                vehicle.setUserId(resultSet.getString("user_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void fetchVehicleInfo(Vehicle vehicle) {
        Connection dbConn = Database.getConnection();

        String sql = "SELECT * FROM vehicle WHERE nic = ?";

        try {
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, vehicle.getNic());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                vehicle.setRegisterNo(resultSet.getString("register_no"));
                vehicle.setChassisNo(resultSet.getString("chassis_no"));
                vehicle.setCurrentOwnerName(resultSet.getString("current_owner_name"));
                vehicle.setCurrentOwnerAddress(resultSet.getString("current_owner_address"));
                vehicle.setCurrentOwnerId(resultSet.getString("current_owner_id"));
                vehicle.setEngineNo(resultSet.getString("engine_no"));
                vehicle.setVehicleClass(resultSet.getString("vehicle_class"));
                vehicle.setStateWhenRegister(resultSet.getString("state_when_register"));
                vehicle.setColor(resultSet.getString("color"));
                vehicle.setEngineCapacity(resultSet.getInt("engine_capacity"));
                vehicle.setFuelType(resultSet.getString("fuel_type"));
                vehicle.setYearOfManufacture(resultSet.getInt("year_of_manufacture"));
                vehicle.setPreviousOwners(resultSet.getString("previous_owners"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String fetchNICByVehicleNo(String vehicleNo) {
        Connection dbConn = Database.getConnection();
        String nic = null;

        String sql = "SELECT nic FROM vehicle WHERE register_no = ?";

        try {
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, vehicleNo);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
               nic = resultSet.getString("nic");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nic;
    }

    public boolean checkUser_IDasVehicleNo(String vehicle_no) {

        Connection dbConn = null;

        boolean alert = false;
        try{
            dbConn = Database.getConnection();
            String sql = "SELECT register_no FROM vehicle WHERE register_no = ?";
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, vehicle_no);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Duplicate Entry!!");
                alert = true;
            } else {
                System.out.println("New Entry!!");
                alert = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return alert;
    }
}
