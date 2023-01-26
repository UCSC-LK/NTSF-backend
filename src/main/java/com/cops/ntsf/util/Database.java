package com.cops.ntsf.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database
{
    private static volatile Connection INSTANCE;

    public static Connection getConnection()
    {
        if(INSTANCE == null)
        {
            synchronized (Database.class)
            {
                if(INSTANCE == null)
                {
                    try
                    {
                        Class.forName("com.mysql.jdbc.Driver");
                        INSTANCE = DriverManager.getConnection("jdbc:mysql://localhost:3306/ntsfdatabase", "root", "");
                    }
                    catch (SQLException e)
                    {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return INSTANCE;
    }
}
