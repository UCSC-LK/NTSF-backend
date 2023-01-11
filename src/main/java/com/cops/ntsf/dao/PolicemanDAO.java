package com.cops.ntsf.dao;

import com.cops.ntsf.model.Complaint;
import com.cops.ntsf.model.Policeman;
import com.cops.ntsf.util.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PolicemanDAO {
    public String insert(Policeman policeman)
    {
        Connection dbConnect = null;
        try {
            dbConnect = DBConnect.getConnection();
            String sql = "INSERT into policeman (name, police_id, nic, rank, police_station) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = dbConnect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, policeman.getName());
            preparedStatement.setString(2, policeman.getPolice_id());
            preparedStatement.setString(3, policeman.getNic());
            preparedStatement.setString(4, policeman.getRank());
            preparedStatement.setString(5, policeman.getPolice_station());

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
}