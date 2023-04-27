package com.cops.ntsf.dao;

import com.cops.ntsf.model.Point;
import com.cops.ntsf.util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PointSystemDAO {
    public void getPointInfo(Point point) throws SQLException {
        Connection dbConn = Database.getConnection();

        String sql = "SELECT * FROM point_system WHERE nic = ?";

        try {
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, point.getNic());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                point.setMaxPointLimit(resultSet.getInt("max_point_limit"));
                point.setMinPointLimit(resultSet.getInt("min_point_limit"));
                point.setInitialPoints(resultSet.getInt("initial_points"));
                point.setRemainingPoints(resultSet.getInt("remaining_points"));
                point.setMaxRecoveryDate(resultSet.getDate("max_recovery_date"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCurrentPoints(Point point) {
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
}
