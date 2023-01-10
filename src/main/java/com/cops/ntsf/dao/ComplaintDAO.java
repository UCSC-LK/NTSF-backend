package com.cops.ntsf.dao;

import com.cops.ntsf.model.Complaint;
import com.cops.ntsf.util.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComplaintDAO {
    public String insert(Complaint complaint)
    {
        Connection dbConnect = null;
        try {
            dbConnect = DBConnect.getConnection();

            preparedStatement.setString(1, complaint.getUser_id());
            preparedStatement.setString(2, complaint.getTitle());
            preparedStatement.setString(3, complaint.getDescription());
            preparedStatement.setString(4, complaint.getComplaint_no());

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();


            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            if (dbConnect != null) try
            {
                dbConnect.close();
            } catch (Exception ignore){}
        }
        return null;
    }
    }