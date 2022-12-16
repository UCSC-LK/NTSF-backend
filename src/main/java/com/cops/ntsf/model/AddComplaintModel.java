package com.cops.ntsf.model;
import com.cops.ntsf.util.DBConnect;
import com.cops.ntsf.util.Database;


import java.sql.Connection;
import java.sql.Statement;

public class AddComplaintModel {

    private static Connection dbConnect = null;
    public static boolean addComplaint(String title, String description)
    {
        boolean isSuccess = false;

        try{
            dbConnect = DBConnect.getConnection();
            Statement stmt = dbConnect.createStatement();
            String sql = "insert into complaint values ('"+title+"', '"+description+"')";
            int rs = stmt.executeUpdate(sql); //returns 1 if insertion is successful

            if (rs > 0)
            {
                isSuccess = true;
            }
            else
            {
                isSuccess = false;
            }


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return isSuccess;
    }

    public static boolean updateComplaint(String title, String description)
    {
        boolean isSuccess = false;

        try{
            dbConnect = DBConnect.getConnection();
            Statement stmt = dbConnect.createStatement();
            String sql = "update complaint set title='"+title+"', description='"+description+"' " ;
            int rs = stmt.executeUpdate(sql);


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        return isSuccess;
    }
}
