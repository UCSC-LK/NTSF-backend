package com.cops.ntsf.dao;

import com.cops.ntsf.model.Fine;
import com.cops.ntsf.util.Database;
import org.json.JSONArray;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class FineDAO {
    public void createFine(Fine fine) {
        Connection dbConn = null;
        try{
            System.out.println("Reached FineDAO");
            dbConn = Database.getConnection();
            String sql = "INSERT INTO fine (fine_type, offence_no, nic, license_no, vehicle_no, driven_vehicle_no,spot_description, imposed_date_time, due_date_time, police_id, police_station_name) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, fine.getFineType());
            preparedStatement.setString(2, fine.getOffenceNo());
            preparedStatement.setString(3, fine.getNic());
            preparedStatement.setString(4, fine.getLicenseNo());
            preparedStatement.setString(5, fine.getVehicleNo());
            preparedStatement.setString(6, fine.getDrivenVehicleNo());
            preparedStatement.setString(7, fine.getSpotDescription());
            preparedStatement.setObject(8, fine.getImposedDateTime());
            preparedStatement.setObject(9, fine.getDueDateTime());
            preparedStatement.setString(10, fine.getPoliceId());
            preparedStatement.setString(11, fine.getPoliceStation());

            preparedStatement.execute();
            preparedStatement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public JSONArray viewFineDetailsAsOIC() {
    }
}


