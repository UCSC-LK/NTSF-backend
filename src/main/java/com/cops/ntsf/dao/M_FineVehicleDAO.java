package com.cops.ntsf.dao;

import com.cops.ntsf.model.M_FineVehicle;
import com.cops.ntsf.util.Database;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class M_FineVehicleDAO {

    public Integer setFineInfo(M_FineVehicle fine) {
        Integer fine_number;
        Connection dbConn = Database.getConnection();
        JSONObject setFineResponse = new JSONObject();
        String policeStation = null;
        Double Latitude = 6.902042;
        Double Longitude = 79.86133;

        try {



            ////////
            String sql4 = "SELECT police_station FROM policeman WHERE police_id = ?";
            PreparedStatement preparedStatement4 = dbConn.prepareStatement(sql4);

            // Set the value of the parameter
            preparedStatement4.setString(1, fine.getPoliceId());

            // Execute the query and process the result set
            ResultSet resultSet4 = preparedStatement4.executeQuery();
            if (resultSet4.next()) {
                policeStation = resultSet4.getString("police_station");
            }

            resultSet4.close();
            preparedStatement4.close();




            ///////


            String sql = "SELECT nic FROM vehicle WHERE register_no = ?";
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);


            // Set the value of the parameter
            preparedStatement.setString(1, fine.getVehicleNo());

            // Execute the query and process the result set
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                System.out.println("Dont have nic number");
                return 0;
            }else {
                String nic = resultSet.getString("nic");

                System.out.println("Spot Description: " + fine.getDescripton());



                String sql2 = "INSERT INTO fine(vehicle_no, location, offence_no , spot_description , offence_type,police_id, imposed_date_time , due_date_time ,nic , police_station_name , longitude , latitude ) VALUES (?, ?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement preparedStatement2 = dbConn.prepareStatement(sql2);

                // Set the values of the parameters
              //  preparedStatement2.setString(1, fine.getNic());
                preparedStatement2.setString(1, fine.getVehicleNo());
                preparedStatement2.setString(2, fine.getLocation());
                preparedStatement2.setString(3, fine.getFineNo());
                preparedStatement2.setString(4, fine.getDescripton());
                preparedStatement2.setString(5, fine.getFineType());
                preparedStatement2.setString(6, fine.getPoliceId());
                preparedStatement2.setObject(7, fine.getImposedDateTime());
                preparedStatement2.setObject(8, fine.getDueDateTime());
                preparedStatement2.setObject(9, nic);
                preparedStatement2.setObject(10, policeStation);
                preparedStatement2.setObject(11, Longitude);
                preparedStatement2.setObject(12, Latitude);


                System.out.println("Here executing");

                // Execute the update
                int rowsAffected = preparedStatement2.executeUpdate();
                if (rowsAffected == 0) {
                    System.out.println("Hi");
                    throw new RuntimeException("Failed to insert the fine information");
                }

                String sql3 = "SELECT fine_no FROM fine ORDER BY fine_no DESC LIMIT 1";
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