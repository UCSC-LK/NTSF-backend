package com.cops.ntsf.dao;

import com.cops.ntsf.model.Policeman;
import com.cops.ntsf.util.DBConnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PolicemanDAO {
    private static Connection dbConnect = null;

    public static boolean addPoliceman(String name, String police_id, String nic, String rank, String police_station) {
        boolean isSuccess = false;

        return isSuccess;
    }




    
    public static List<Policeman> viewPoliceman () {

        ArrayList<Policeman> policemanInformation = new ArrayList<>();

        try {
            dbConnect = DBConnect.getConnection();
            Statement statement = dbConnect.createStatement();
            String sql = "select * from policeman";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                String name = rs.getString(1);
                String police_id = rs.getString(2);
                String nic = rs.getString(3);
                String rank = rs.getString(4);
                String police_station = rs.getString(5);

                Policeman policemanInfo = new Policeman(name, police_id, nic, rank, police_station);
                policemanInformation.add(policemanInfo);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return policemanInformation;

    }
}
