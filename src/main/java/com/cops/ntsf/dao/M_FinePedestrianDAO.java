package com.cops.ntsf.dao;

import com.cops.ntsf.model.M_FinePedestrian;
import com.cops.ntsf.util.Database;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class M_FinePedestrianDAO {

    public Integer setFineInfo(M_FinePedestrian fine) {
        Integer fine_number;
        Connection dbConn = Database.getConnection();
        JSONObject setFineResponse = new JSONObject();

        try {
            String sql = "SELECT name FROM people WHERE nic = ?";
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);


            // Set the value of the parameter
            preparedStatement.setString(1, fine.getNic());

            // Execute the query and process the result set
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                System.out.println("Dont have licence number");
                return 0;
            }else {

                String sql2 = "INSERT INTO fine_new(nic, location, offence_no , description , fine_type,police_id) VALUES (?,?,?,?,?,?)";
                PreparedStatement preparedStatement2 = dbConn.prepareStatement(sql2);

                // Set the values of the parameters
                preparedStatement2.setString(1, fine.getNic());
                preparedStatement2.setString(2, fine.getLocation());
                preparedStatement2.setString(3, fine.getFineNo());
                preparedStatement2.setString(4, fine.getDescripton());
                preparedStatement2.setString(5, fine.getFineType());
                preparedStatement2.setString(6, fine.getPoliceId());


                System.out.println("Here executing");

                // Execute the update
                int rowsAffected = preparedStatement2.executeUpdate();
                if (rowsAffected == 0) {
                    System.out.println("Hi");
                    throw new RuntimeException("Failed to insert the fine information");
                }

                String sql3 = "SELECT fine_no FROM fine_new ORDER BY fine_no DESC LIMIT 1";
                PreparedStatement preparedStatement3 = dbConn.prepareStatement(sql3);
                // Execute the query and process the result set
                System.out.println("Select query before");
                ResultSet resultSet3 = preparedStatement3.executeQuery();
                System.out.println("Select query after");

                if (!resultSet3.next()) {
                    System.out.println("Dont have licence number 2");
                    return 0;
                }else {
                    String fine_no = resultSet3 .getString("fine_no");
                    fine_number = Integer.parseInt(fine_no);
                }

                preparedStatement2.close();
            }
            resultSet.close();
            preparedStatement.close();
            return fine_number;


//            dbConn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}