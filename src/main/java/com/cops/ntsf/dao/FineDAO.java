package com.cops.ntsf.dao;

import com.cops.ntsf.model.Fine;
import com.cops.ntsf.util.Database;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FineDAO {
    public boolean createFine(Fine fine) {
        Connection dbConn = null;
        boolean alert = false;
        try {
            System.out.println("Reached FineDAO");
            dbConn = Database.getConnection();
            String sql = "INSERT INTO fine (offence_no, nic, license_no, vehicle_no, driven_vehicle_no,spot_description, imposed_date_time, due_date_time, police_id, police_station_name, footage, latitude, longitude) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?)";
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            System.out.println("Offence No: " + fine.getOffenceNo());
            System.out.println("NIC: " + fine.getNic());
            System.out.println("License No: " + fine.getLicenseNo());
            System.out.println("Vehicle No: " + fine.getVehicleNo());
            System.out.println("Driven Vehicle No: " + fine.getDrivenVehicleNo());
            System.out.println("Spot Description: " + fine.getSpotDescription());
            System.out.println("Imposed Date Time: " + fine.getImposedDateTime());
            System.out.println("Due Date Time: " + fine.getDueDateTime());
            System.out.println("Police ID: " + fine.getPoliceId());
            System.out.println("Police Station: " + fine.getPoliceStation());
            System.out.println("Footage: " + fine.getFootage_file());
            System.out.println("Latitude: " + fine.getLatitude());
            System.out.println("Longitude: " + fine.getLongitude());


            preparedStatement.setString(1, fine.getOffenceNo());
            preparedStatement.setString(2, fine.getNic());
            preparedStatement.setString(3, fine.getLicenseNo());
            preparedStatement.setString(4, fine.getVehicleNo());
            preparedStatement.setString(5, fine.getDrivenVehicleNo());
            preparedStatement.setString(6, fine.getSpotDescription());
            preparedStatement.setObject(7, fine.getImposedDateTime());
            preparedStatement.setObject(8, fine.getDueDateTime());
            preparedStatement.setString(9, fine.getPoliceId());
            preparedStatement.setString(10, fine.getPoliceStation());
            preparedStatement.setString(11, fine.getFootage_file());
            preparedStatement.setString(12, fine.getLatitude());
            preparedStatement.setString(13, fine.getLongitude());

            int resultSet = preparedStatement.executeUpdate();

            if (resultSet > 0) {
                System.out.println("Fine created successfully");
                alert = true;
            } else {
                System.out.println("Fine creation failed");
                alert = false;
            }

            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return alert;
    }

    public JSONArray viewFineDetailsAsOIC(String policeStation, String offenceType, String paymentStatus) {
        Connection dbConn = null;
        JSONArray jsonArray = new JSONArray();

        try {
            dbConn = Database.getConnection();
            String sql = "SELECT f.fine_no, f.offence_no, f.spot_description, f.nic, f.license_no, f.vehicle_no, f.driven_vehicle_no, f.imposed_date_time, f.due_date_time, f.police_id, f.police_station_name, f.footage, f.latitude, f.longitude, f.payment_status, o.amount, o.description FROM fine f INNER JOIN offence o ON f.offence_no = o.offence_no WHERE f.police_station_name = ? AND o.offence_type = ? AND f.payment_status = ?;  ";
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, policeStation);
            preparedStatement.setString(2, offenceType);
            preparedStatement.setString(3, paymentStatus);

            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Result set is" + resultSet);

            while(resultSet.next()) {

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("fine_no", resultSet.getString("fine_no"));
                jsonObject.put("offence_no", resultSet.getString("offence_no"));
                jsonObject.put("spot_description", resultSet.getString("spot_description"));
                jsonObject.put("nic", resultSet.getString("nic"));
                jsonObject.put("license_no", resultSet.getString("license_no"));
                jsonObject.put("vehicle_no", resultSet.getString("vehicle_no"));
                jsonObject.put("driven_vehicle_no", resultSet.getString("driven_vehicle_no"));
                jsonObject.put("imposed_date_time", resultSet.getString("imposed_date_time"));
                jsonObject.put("due_date_time", resultSet.getString("due_date_time"));
                jsonObject.put("police_id", resultSet.getString("police_id"));
                jsonObject.put("police_station_name", resultSet.getString("police_station_name"));
                jsonObject.put("footage", resultSet.getString("footage"));
                jsonObject.put("latitude", resultSet.getString("latitude"));
                jsonObject.put("longitude", resultSet.getString("longitude"));
                jsonObject.put("status", resultSet.getString("payment_status"));
                jsonObject.put("amount", resultSet.getString("amount"));
                jsonObject.put("description", resultSet.getString("offence_description"));

                jsonArray.put(jsonObject);
            }
            resultSet.close();
            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }


    // View fines on user side code starts here
    public ArrayList<Fine> fetchUserFinesInfo(Fine fine) throws SQLException {

        Connection dbConn = Database.getConnection();

        // Get offence_type from offence_no
        String sql = "SELECT * FROM fine WHERE nic = ?";

        PreparedStatement preparedStatement = dbConn.prepareStatement(sql);

        preparedStatement.setString(1, fine.getNic());

        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<Fine> finesList = new ArrayList<Fine>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        while (resultSet.next()) {
            Fine nextFine;
            nextFine = new Fine(fine.getNic());
            nextFine.setFineNo(resultSet.getInt("fine_no"));
            nextFine.setOffenceNo(resultSet.getString("offence_no"));
            nextFine.setSpotDescription(resultSet.getString("spot_description"));
            nextFine.setNic(resultSet.getString("nic"));
            nextFine.setLicenseNo(resultSet.getString("license_no"));
            nextFine.setVehicleNo(resultSet.getString("vehicle_no"));
            nextFine.setImposedDateTime(LocalDateTime.parse(resultSet.getString("imposed_date_time"), formatter));
            nextFine.setDueDateTime(LocalDateTime.parse(resultSet.getString("due_date_time"), formatter));
            nextFine.setPoliceId(resultSet.getString("police_id"));
            nextFine.setPoliceStation(resultSet.getString("police_station_name"));
            nextFine.setPaymentStatus(resultSet.getString("payment_status"));

            finesList.add(nextFine);
        }
        return finesList;
    }

    public Fine fetchFineByFineNo(Fine fine) {
        Connection dbConn = Database.getConnection();

        // Get offence_type from offence_no
        String sql = "SELECT * FROM fine WHERE fine_no = ? and nic = ?";

        try {
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);

            preparedStatement.setInt(1, fine.getFineNo());
            preparedStatement.setString(2, fine.getNic());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                fine.setOffenceNo(resultSet.getString("offence_no"));
                return fine;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getCurrentFineNoForFootage() {
        Connection dbConn = Database.getConnection();

        String sql = "SELECT MAX(fine_no) FROM fine";

        try {
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                return 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


