package com.cops.ntsf.dao;

import com.cops.ntsf.model.Fine;
import com.cops.ntsf.util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class  FineDAO {
    public ArrayList<Fine> fetchUserFinesInfo(Fine fine) throws SQLException {
        Connection dbConn = Database.getConnection();

        String sql = "SELECT * FROM fine WHERE user_id = ?";

        PreparedStatement preparedStatement = dbConn.prepareStatement(sql);

        preparedStatement.setString(1, fine.getUserId());

        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<Fine> finesList = new ArrayList<Fine>();

        while (resultSet.next()) {
            Fine nextFine;
            nextFine = new Fine(fine.getUserId());
            nextFine.setTicketNo(Integer.valueOf(resultSet.getString("ticket_no")));
            nextFine.setFineNo(Integer.valueOf(resultSet.getString("fine_no")));
            nextFine.setDate(resultSet.getDate("date"));
            nextFine.setDueDate(resultSet.getDate("due_date"));
            nextFine.setFineAmount(resultSet.getBigDecimal("fine_amount"));
            nextFine.setPaymentStatus(resultSet.getString("payment_status"));

            finesList.add(nextFine);
        }
        return finesList;
    }
}