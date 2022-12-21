package com.cops.ntsf.dao;

import com.cops.ntsf.model.Fine;
import com.cops.ntsf.util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FineDAO {
    public void fetchUserFinesInfo(Fine fine) throws SQLException {
        Connection dbConn = Database.getConnection();

        String sql = "SELECT * FROM fine WHERE user_id = ?";

        PreparedStatement preparedStatement = dbConn.prepareStatement(sql);

        preparedStatement.setString(1, fine.getUserId());

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            fine.setTicketNo(Integer.valueOf(resultSet.getString("ticket_no")));
            fine.setFineNo(Integer.valueOf(resultSet.getString("fine_no")));
            fine.setDate(resultSet.getDate("date"));
            fine.setDueDate(resultSet.getDate("due_date"));
            fine.setFineAmount(resultSet.getBigDecimal("fine_amount"));
            fine.setPaymentStatus(resultSet.getString("payment_status"));
        }
    }
}
