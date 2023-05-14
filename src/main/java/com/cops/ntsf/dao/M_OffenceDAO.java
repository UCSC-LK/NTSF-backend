package com.cops.ntsf.dao;

import com.cops.ntsf.model.M_Offence;
import com.cops.ntsf.util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class M_OffenceDAO {
    public void setOffenceInfo(M_Offence offence) {
        Connection dbConn = Database.getConnection();

        String sql = "INSERT INTO offence(offence_no, offence_type,description,point_weight,amount) VALUES (?, ?,?,?,?)";

        try {
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, offence.getOffenceNo());
            preparedStatement.setString(2, offence.getOffenceType());
            preparedStatement.setString(3, offence.getDescription());
            preparedStatement.setString(4, offence.getPointWeight());
            preparedStatement.setString(5, offence.getAmount());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public static int updateOffenceInfo(M_Offence offence) {
        Connection dbConn = Database.getConnection();

        String sql = "UPDATE offence set  offence_type=?, description=?, point_weight=?, amount=? where offence_no=?";

        try {
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, offence.getOffenceNo());
            preparedStatement.setString(2, offence.getOffenceType());
            preparedStatement.setString(3, offence.getDescription());
            preparedStatement.setString(4, offence.getPointWeight());
            preparedStatement.setString(5, offence.getAmount());

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


    public static List<M_Offence> getAllOffences(){
        List<M_Offence> list=new ArrayList<M_Offence>();

        String sql = "select * from offence";

        try{
            Connection dbConn = Database.getConnection();
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);

            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                M_Offence offence=new M_Offence();
                offence.setOffenceNo(resultSet.getString(1));
                offence.setOffenceType(resultSet.getString(2));
                offence.setDescription(resultSet.getString(3));
                offence.setPointWeight(resultSet.getString(5));
                offence.setAmount(resultSet.getString(4));
                list.add(offence);

            }
//            dbConn.close();
        }catch(Exception offence){offence.printStackTrace();}

        return list;
    }



}
