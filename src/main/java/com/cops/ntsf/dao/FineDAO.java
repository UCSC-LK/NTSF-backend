//package com.cops.ntsf.dao;
//
//import com.cops.ntsf.model.Fine;
//import com.cops.ntsf.util.Database;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class  FineDAO {
//
//
////    public ArrayList<Fine> fetchUserFinesInfo(Fine fine) throws SQLException {
////        Connection dbConn = Database.getConnection();
////
////        String sql = "SELECT * FROM fine WHERE user_id = ?";
////
////        PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
////
////        preparedStatement.setString(1, fine.getUserId());
////
////        ResultSet resultSet = preparedStatement.executeQuery();
////
////        ArrayList<Fine> finesList = new ArrayList<Fine>();
////
////        while (resultSet.next()) {
////            Fine nextFine;
////            nextFine = new Fine(fineNo, fine.getUserId(), userId, fineAmount, paymentStatus, policeId);
////
////            //nextFine.setTicketNo(Integer.valueOf(resultSet.getString("ticket_no")));
////            nextFine.setFineNo(Integer.valueOf(resultSet.getString("fine_no")));
//////            nextFine.setFineDate(resultSet.getDate("fine_date"));
//////            nextFine.setDueDate(resultSet.getDate("due_date"));
////            nextFine.setFineAmount(resultSet.getInt("fine_amount"));
////            nextFine.setPaymentStatus(resultSet.getString("payment_status"));
////
////            finesList.add(nextFine);
////        }
////        return finesList;
////    }
//
//
////    public void setOffenceInfo(Fine fine) {
////        Connection dbConn = Database.getConnection();
////
////        String sql = "INSERT INTO fine(fine_no, fine_type,user_id, fine_amount,payment_status,police_id ) VALUES (?, ?,?,?,?,?)";
////
////        try {
////            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
////
////            preparedStatement.setInt(1, fine.getFineNo());
////            preparedStatement.setString(2, fine.getFineType());
////            preparedStatement.setString(3, fine.getUserId());
//////            preparedStatement.setDate(4, (Date) fine.getFineDate());
//////            preparedStatement.setDate(5, fine.getDueDate());
////            preparedStatement.setInt(4, fine.getFineAmount());
////            preparedStatement.setString(5, fine.getPaymentStatus());
////            preparedStatement.setString(6, fine.getPoliceID());
//////            preparedStatement.setTime(7, fine.getFineTime());
////
////
////            preparedStatement.executeUpdate();
////        } catch (SQLException e) {
////            throw new RuntimeException(e);
////        }
////
////    }