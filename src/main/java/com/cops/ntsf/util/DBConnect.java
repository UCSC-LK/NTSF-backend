
package com.cops.ntsf.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
    private static String url = "jdbc:mysql://localhost:3306/ntsfdatabase" ;
    private static String userName = "root";
    private static String password = "" ;
    private static Connection dbConnection;
    public static Connection getConnection()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");

            dbConnection = DriverManager.getConnection(url, userName, password);
        }
        catch (Exception e)
        {
            System.out.println("Database Connection is not successful");
        }
        return dbConnection;
    }

}
