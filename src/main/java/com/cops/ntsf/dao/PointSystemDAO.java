package com.cops.ntsf.dao;

import com.cops.ntsf.model.PointData;
import com.cops.ntsf.util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PointSystemDAO {
    public void getPointInfo(PointData pointData) {
        Connection dbConn = Database.getConnection();

        String sql = "SELECT * FROM point_system WHERE nic = ?";

        try {
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, pointData.getNic());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                pointData.setMaxPointLimit(resultSet.getInt("max_point_limit"));
                pointData.setMinPointLimit(resultSet.getInt("min_point_limit"));
                pointData.setInitialPoints(resultSet.getInt("initial_points"));
                pointData.setCurrentPoints(resultSet.getInt("current_points"));
                pointData.setMaxRecoveryDate(resultSet.getDate("max_recovery_date"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCurrentPoints(PointData point) {
        Connection dbConn = Database.getConnection();

        String sql = "UPDATE point_system SET current_points = ? WHERE nic = ?";

        try {
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);

            preparedStatement.setInt(1, point.getCurrentPoints());
            preparedStatement.setString(2, point.getNic());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                point.setCurrentPoints(resultSet.getInt("current_points"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int fetchCurrentPointsByNic(PointData pointData) {
        Connection dbConn = Database.getConnection();

        String sql = "SELECT current_points FROM point_system WHERE nic = ?";

        try {
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, pointData.getNic());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                pointData.setCurrentPoints(resultSet.getInt("current_points"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pointData.getCurrentPoints();
    }
}
