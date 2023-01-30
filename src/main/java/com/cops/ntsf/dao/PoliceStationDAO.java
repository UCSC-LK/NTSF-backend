package com.cops.ntsf.dao;

import com.cops.ntsf.util.Database;

import java.sql.Connection;
import java.sql.Statement;

/*import com.cops.ntsf.model.Complaint;
import com.cops.ntsf.util.DBConnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;*/

public class PoliceStationDAO
{
    private static Connection dbConn = null;

    public static boolean AddPoliceStation(/*String station_id,*/ String name) {
        boolean isSuccess = false;

        try {
            dbConn = Database.getConnection();
            Statement stmt = dbConn.createStatement();
            String sql = "insert into complaint values ('" + name + "')";
            int rs = stmt.executeUpdate(sql); //returns 1 if insertion is successful(since only 1 row is affected)

            if (rs > 0) {
                isSuccess = true;
            } else {
                isSuccess = false;
            }
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            return isSuccess;
        }

    /*public static boolean ViewPoliceStation(){

    }*/
}
