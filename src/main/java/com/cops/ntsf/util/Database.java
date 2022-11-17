package com.cops.ntsf.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database
{
    private static Connection INSTANCE;

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
                        INSTANCE = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "pass");
                    }
                    catch (SQLException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
        return INSTANCE;
    }
}
