package com.cops.ntsf.dao;

import com.cops.ntsf.model.PoliceStation;
import com.cops.ntsf.util.Database;

import java.sql.*;

public class PoliceStationDAO {
    public String createPoliceStation(PoliceStation policeStation) {
        Connection dbConn = null;
        try {
            dbConn = Database.getConnection();
            String sql = "INSERT into police_station (branch_name, address, district, province, contact_number, email) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, policeStation.getBranch_name());
            preparedStatement.setString(2, policeStation.getAddress());
            preparedStatement.setString(3, policeStation.getDistrict());
            preparedStatement.setString(4, policeStation.getProvince());
            preparedStatement.setString(5, policeStation.getContact_number());
            preparedStatement.setString(6, policeStation.getEmail());

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean getPoliceStationBranch_NameCheckResult(String branch_nameCheck) {
        Connection dbConn = null;

        boolean alert = false;
        try {
            dbConn = Database.getConnection();
            String sql = "SELECT branch_name from police_station WHERE branch_name = ?";

            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, branch_nameCheck);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Duplicate Entry!!");
                return true;
            } else {
                System.out.println("New Entry!!");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alert;
    }

    public boolean getPoliceStationContact_NumberCheckResult(String contact_numberCheck) {
        Connection dbConn = null;

        boolean alert = false;

        try {
            dbConn = Database.getConnection();
            String sql = "SELECT contact_number from police_station WHERE contact_number = ?";

            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, contact_numberCheck);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Duplicate Entry!!");
                alert = true;
            } else {
                System.out.println("New Entry!!");
                alert = false;
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alert;
    }

    public boolean getPoliceStationEmailCheckResult(String emailCheck) {
        Connection dbConn = null;

        boolean alert = false;
        try {
            dbConn = Database.getConnection();

            String sql = "SELECT email from police_station WHERE email = ?";

            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, emailCheck);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Duplicate Entry!!");
                alert = true;
            } else {
                System.out.println("New Entry!!");
                alert = false;
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alert;
    }


}
