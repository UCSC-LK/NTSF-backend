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
            preparedStatement.setString(4, policeman.getPoliceRank());
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

        String sql = "INSERT INTO policeman (police_id, name, nic, email, mobileNo, password) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, policeman.getPoliceId());
            preparedStatement.setString(2, policeman.getName());
            preparedStatement.setString(3, policeman.getNic());
            preparedStatement.setString(4, policeman.getEmail());
            preparedStatement.setString(5, policeman.getMobileNo());
            preparedStatement.setString(6, policeman.getPassword());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePolicemanRankStation(Policeman policeman) {
        Connection dbConn = Database.getConnection();

        String sql = "UPDATE policeman SET police_rank = ?, police_station = ? WHERE police_id = ?";

        try{
            PreparedStatement preparedStatement =dbConn.prepareStatement(sql);
            preparedStatement.setString(1, policeman.getPoliceId());
            preparedStatement.setString(2, policeman.getPoliceRank());
            preparedStatement.setString(3, policeman.getPoliceStation());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void fetchPolicemanInfo(Policeman policeman) {
        Connection dbConn = Database.getConnection();

        String sql = "SELECT * FROM policeman WHERE police_id = ?";

        try {
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, policeman.getPoliceId());
            preparedStatement.setString(2, String.valueOf(policeman.getPoliceRank()));
            preparedStatement.setString(3, policeman.getName());
            preparedStatement.setString(4, policeman.getNic());
            preparedStatement.setString(5, policeman.getEmail());
            preparedStatement.setString(6, policeman.getMobileNo());
            preparedStatement.setString(7, policeman.getPoliceStation());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                policeman.setPoliceId(resultSet.getString("police_id"));
//                policeman.setPoliceRank(resultSet.getPoliceRank("police_rank"));
                policeman.setName(resultSet.getString("name"));
                policeman.setName(resultSet.getString("nic"));
                policeman.setEmail(resultSet.getString("email"));
                policeman.setMobileNo(resultSet.getString("mobile_no"));
                policeman.setPoliceStation(resultSet.getString("police_station"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}