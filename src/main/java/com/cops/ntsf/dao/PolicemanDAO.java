package com.cops.ntsf.dao;

import com.cops.ntsf.model.Policeman;
import com.cops.ntsf.util.DBConnect;
import com.cops.ntsf.util.Database;

import java.sql.*;

public class PolicemanDAO {
    public String insert(Policeman policeman)
    {
        Connection dbConnect = null;
        try {
            dbConnect = DBConnect.getConnection();
            String sql = "INSERT into policeman (police_id, name, nic, rank, police_station) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = dbConnect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, policeman.getPoliceId());
            preparedStatement.setString(2, policeman.getName());
            preparedStatement.setString(3, policeman.getNic());
            preparedStatement.setString(4, policeman.getRank());
            preparedStatement.setString(5, policeman.getPoliceStation());

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (dbConnect != null) try
            {
                dbConnect.close();
            } catch (Exception ignore){}
        }
        return null;
    }

    public void insertPolicemanInfo(Policeman policeman) {

        Connection dbConn = Database.getConnection();

        String sql = "INSERT INTO policeman (police_id, nic, email) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1,policeman.getPoliceId());
            preparedStatement.setString(2,policeman.getName());
            preparedStatement.setString(2,policeman.getNic());
            preparedStatement.setString(3,policeman.getEmail());
            preparedStatement.setString(4,policeman.getMobileNo());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePolicemanRankStation(Policeman policeman) {
        Connection dbConn = Database.getConnection();

        String sql = "UPDATE policeman SET rank = ?, police_station = ? WHERE policeId = ?";

        try{
            PreparedStatement preparedStatement =dbConn.prepareStatement(sql);
            preparedStatement.setString(1,policeman.getRank());
            preparedStatement.setString(2,policeman.getPoliceStation());
            preparedStatement.setString(3,policeman.getPoliceId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}