package com.cops.ntsf.util;

import java.sql.Connection;
import java.sql.Statement;

public class ComplaintDAO {

    private static Connection dbConnect = null;

    public static boolean addComplaint(String title, String description) {
        boolean isSuccess = false;

        //create db Connection
//        String url = "jdbc:mysql://localhost:3306/ntsfdatabase";
//        String user = "root";
//        String pass = "";

        try {
            dbConnect = DBConnect.getConnection();
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con = DriverManager.getConnection(url, user, pass);
            Statement stmt = dbConnect.createStatement();

            String sql = "insert into complaint values ('" + title + "', '" + description + "')";
            int rs = stmt.executeUpdate(sql); //returns 1 if insertion is successful

            if (rs > 0) {
                isSuccess = true;
            } else {
                isSuccess = false;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return isSuccess;
    }
}