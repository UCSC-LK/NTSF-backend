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
                // driver.setLicenceExpiryDate(LocalDate.parse(resultSet.getString("licence_expire_date")));
                // driver.setLicenceIssueDate(LocalDate.parse(resultSet.getString("licence_issue_date")));
                pedestrian.setUserId(resultSet.getString("user_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
