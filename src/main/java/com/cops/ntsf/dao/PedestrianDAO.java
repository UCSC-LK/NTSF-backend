package com.cops.ntsf.dao;

import com.cops.ntsf.model.Pedestrian;
import com.cops.ntsf.util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PedestrianDAO {
    public void getPedestrianFromNic(Pedestrian pedestrian) {
        Connection dbConn = Database.getConnection();

        String sql = "SELECT * FROM pedestrian WHERE nic = ?";

        try {
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, pedestrian.getNic());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                pedestrian.setUserId(resultSet.getString("user_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertPedestrianInfo(Pedestrian pedestrian) {
        Connection dbConn = Database.getConnection();

        String sql = "INSERT INTO pedestrian(user_id, nic) VALUES (?, ?)";

        try {
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, pedestrian.getUserId());
            preparedStatement.setString(2, pedestrian.getNic());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
