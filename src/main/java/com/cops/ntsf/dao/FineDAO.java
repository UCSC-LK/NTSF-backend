package com.cops.ntsf.dao;

import com.cops.ntsf.constants.OffenceType;
import com.cops.ntsf.constants.PaymentStatus;
import com.cops.ntsf.model.Fine;
import com.cops.ntsf.util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FineDAO {
    public ArrayList<Fine> fetchUserFinesInfo(Fine fine) throws SQLException {
        Connection dbConn = Database.getConnection();

        String sql = "SELECT * FROM fine WHERE user_id = ?";

        PreparedStatement preparedStatement = dbConn.prepareStatement(sql);

        preparedStatement.setString(1, String.valueOf(fine.getUserId()));

        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<Fine> finesList = new ArrayList<Fine>();

        while (resultSet.next()) {
            Fine nextFine;
            nextFine = new Fine(fine.getUserId());
            nextFine.setTicketNo(Integer.valueOf(resultSet.getString("ticket_no")));
            nextFine.setFineNo(Integer.valueOf(resultSet.getString("fine_no")));
            nextFine.setDate(resultSet.getDate("date"));
            nextFine.setDueDate(resultSet.getDate("due_date"));
//            nextFine.setFineAmount(resultSet.getString("fine_amount"));
            nextFine.setPaymentStatus(PaymentStatus.valueOf(resultSet.getString("payment_status")));
            nextFine.setOffenceType(OffenceType.valueOf(resultSet.getString("offence_type")));
            nextFine.setAmount(resultSet.getString("amount"));


            finesList.add(nextFine);
        }
        return finesList;
    }

    public void insertFineInfo(Fine fine) {
        Connection dbConn = Database.getConnection();

        String sql = "INSERT INTO fine (ticket_no, fine_no, date, due_date, amount, payment_status, offence_type) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(fine.getTicketNo()));
            preparedStatement.setString(2, String.valueOf(fine.getFineNo()));
            preparedStatement.setString(3, String.valueOf(fine.getDate()));
            preparedStatement.setString(4, String.valueOf(fine.getDueDate()));
            preparedStatement.setString(5, fine.getAmount());
            preparedStatement.setString(6, String.valueOf(fine.getPaymentStatus()));
            preparedStatement.setString(7, String.valueOf(fine.getOffenceType()));
            preparedStatement.setString(8, String.valueOf(fine.getPointWeight()));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}