package com.cops.ntsf.dao;

import com.cops.ntsf.model.Footage;
import com.cops.ntsf.util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FootageDAO {
    public void uploadFootage(Footage footage) {
        Connection dbConn = Database.getConnection();

        String sql = "INSERT INTO footage (complaint_no, footage_file) VALUES (?, ?)";

        try {
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, footage.getComplaintNo());
            preparedStatement.setString(2, footage.getFootageFile());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
