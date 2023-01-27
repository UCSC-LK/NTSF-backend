package com.cops.ntsf.dao;

import com.cops.ntsf.model.Policeman;
import com.cops.ntsf.model.TrafficPolice;
import com.cops.ntsf.util.DBConnect;
import com.cops.ntsf.util.Database;

import java.sql.*;

public class PolicemanDAO {
    public String insert(Policeman policeman)
    {
        Connection dbConnect = null;
        try {
            dbConnect = DBConnect.getConnection();
            String sql = "INSERT into policeman (name, police_id, nic, rank, police_station) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = dbConnect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, policeman.getName());
            preparedStatement.setString(2, policeman.getPoliceId());
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

    public void insertTrafficPoliceInfo(TrafficPolice trafficPolice) {
        Connection dbConn = Database.getConnection();

//        String sql = "INSERT INTO user (nic, email, mobile_no, user_type) VALUES (?, ?, ?, ?)";
        String sql = "INSERT INTO taffic_police (name, police_id, nic, rank, police_station) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, trafficPolice.getName());
            preparedStatement.setString(2, trafficPolice.getPoliceId());
            preparedStatement.setString(3,trafficPolice.getNic());
            preparedStatement.setString(3,trafficPolice.getRank());
            preparedStatement.setString(3,trafficPolice.getPoliceStation());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertPolicemanInfo(Policeman policeman) {

        Connection dbConn = Database.getConnection();

        String sql = "INSERT INTO policeman (police_id, nic, email) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1,policeman.getPoliceId());
            preparedStatement.setString(2,policeman.getNic());
            preparedStatement.setString(3,policeman.getEmail());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePolicemanRank(Policeman policeman) {

        Connection dbConn = Database.getConnection();

        String sql = "UPDATE policeman SET rank = ? WHERE policeId = ?";

        try{
            PreparedStatement preparedStatement =dbConn.prepareStatement(sql);
            preparedStatement.setString(1,policeman.getRank());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}