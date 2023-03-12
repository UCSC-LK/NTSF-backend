package com.cops.ntsf.dao;

import com.cops.ntsf.model.Fine;
import com.cops.ntsf.util.Database;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class FineDAO {
    public void createFine(Fine fine) {
        Connection dbConn = null;
        try{
            dbConn = Database.getConnection();
            String sql = "INSERT INTO fine (fine_type, offence_no, nic, license_no, vehicle_no, spot_description, imposed_date_time, due_date_time, police_id, police_station) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, fine.getFineType());
            preparedStatement.setString(2, fine.getOffenceNo());
            preparedStatement.setString(3, fine.getNic());
            preparedStatement.setString(4, fine.getLicenseNo());
            preparedStatement.setString(5, fine.getVehicleNo());
            preparedStatement.setString(6, fine.getSpotDescription());
            preparedStatement.setObject(7, fine.getImposedDateTime());
            preparedStatement.setObject(8, fine.getDueDateTime());
            preparedStatement.setString(9, fine.getPoliceId());
            preparedStatement.setString(10, fine.getPoliceStation());

            preparedStatement.execute();
            preparedStatement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}


