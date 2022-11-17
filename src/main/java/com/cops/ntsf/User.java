package com.cops.ntsf;

import com.cops.ntsf.util.Database;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class User extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(resp.getOutputStream()));
        writer.write("TESTING IT!");
        writer.flush();

        Connection connection = Database.getConnection();
        Statement statement;
        try
        {
            statement = connection.createStatement();
            statement.execute("SELECT * from user");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}

