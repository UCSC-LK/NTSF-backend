package com.cops.ntsf.dao;

import com.cops.ntsf.model.Offence;
import com.cops.ntsf.util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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


    public static int updateOffenceInfo(Offence offence) {
        Connection dbConn = Database.getConnection();

        String sql = "UPDATE offence set  offence_type=?, description=?, point_weight=?, amount=? where offence_no=?";

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
        return 0;
    }

    public static void deleteOffenceInfo(int offenceN) {
        Connection dbConn = Database.getConnection();

        String sql = "delete from offence where offence_no=?";

        try {
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setInt(1, offenceN);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static List<Offence> getAllOffences(){
        List<Offence> list=new ArrayList<Offence>();

        String sql = "select * from offence";

        try{
            Connection dbConn = Database.getConnection();
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);

            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                Offence offence=new Offence();
                offence.setOffenceNo(resultSet.getInt(1));
                offence.setOffenceType(resultSet.getString(2));
                offence.setDescription(resultSet.getString(3));
                offence.setPointWeight(resultSet.getInt(4));
                offence.setAmount(resultSet.getInt(5));
                list.add(offence);
            }
            dbConn.close();
        }catch(Exception offence){offence.printStackTrace();}

        return list;
    }



}
