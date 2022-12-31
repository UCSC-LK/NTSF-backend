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
    public static List<Policeman> viewPoliceman(){
        ArrayList<Policeman> policemanInformation = new ArrayList<>();

        try{
            dbConnect = DBConnect.getConnection();
            Statement statement = dbConnect.createStatement();
            String sql = "select * from policeman";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next())
            {
                String police_station = rs.getString(1);
                String police_id = rs.getString(2);
                String name = rs.getString(3);
                String nic = rs.getString(4);

                Policeman policemanInfo = new Policeman(police_station, police_id, name, nic);
                policemanInformation.add(policemanInfo);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return policemanInformation;
    }
}
