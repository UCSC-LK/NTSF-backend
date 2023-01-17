package com.cops.ntsf.dao;

import com.cops.ntsf.model.Offence;
import com.cops.ntsf.util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OffenceDAO {
    public void setOffenceInfo(Offence offence) {
        Connection dbConn = Database.getConnection();

        String sql = "INSERT INTO offence(offence_no, offence_type,description,point_weight,amount) VALUES (?, ?,?,?,?)";

        try {
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setInt(1, offence.getOffenceNo());
            preparedStatement.setString(2, offence.getOffenceType());
            preparedStatement.setString(3, offence.getDescription());
            preparedStatement.setInt(4, offence.getPointWeight());
            preparedStatement.setInt(5, offence.getAmount());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }







    


    
}
