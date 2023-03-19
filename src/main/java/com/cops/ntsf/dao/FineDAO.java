package com.cops.ntsf.dao;

import com.cops.ntsf.constants.OffenceType;
import com.cops.ntsf.constants.PaymentStatus;
import com.cops.ntsf.model.Fine;
import com.cops.ntsf.util.Database;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        Connection dbConn = null;
        JSONArray jsonArray = new JSONArray();

        try{
            dbConn = Database.getConnection();
            String sql = "SELECT * FROM fine where police_station_name = ? and status = ?";
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("fine_type", resultSet.getString("fine_type"));
            jsonObject.put("offence_no", resultSet.getString("offence_no"));
            jsonObject.put("nic", resultSet.getString("nic"));
            jsonObject.put("license_no", resultSet.getString("license_no"));
            jsonObject.put("vehicle_no", resultSet.getString("vehicle_no"));
            jsonObject.put("driven_vehicle_no", resultSet.getString("driven_vehicle_no"));
            jsonObject.put("spot_description", resultSet.getString("spot_description"));
            jsonObject.put("imposed_date_time", resultSet.getString("imposed_date_time"));
            jsonObject.put("due_date_time", resultSet.getString("due_date_time"));
            jsonObject.put("police_id", resultSet.getString("police_id"));
            jsonObject.put("police_station_name", resultSet.getString("police_station_name"));
            jsonObject.put("status", resultSet.getString("status"));

            jsonArray.put(jsonObject);


        } catch (Exception e){
            e.printStackTrace();
        }
        return jsonArray;
    }
}














































//AVISHI CODE BELOW THIS LINE When Solving conflicts
//        preparedStatement.setString(1, fine.getNic());
//                preparedStatement.setString(2, String.valueOf(fine.getOffenceType()));
//
//                ResultSet resultSet = preparedStatement.executeQuery();
//
//                ArrayList<Fine> finesList = new ArrayList<Fine>();
//
//        while (resultSet.next()) {
//        Fine nextFine;
//
//        nextFine = new Fine(fine.getNic());
//        nextFine.setUserId(Integer.valueOf(resultSet.getString("user_id")));
//        nextFine.setTicketNo(Integer.valueOf(resultSet.getString("ticket_no")));
//        nextFine.setFineNo(Integer.valueOf(resultSet.getString("fine_no")));
//        nextFine.setDate(resultSet.getDate("date"));
//        nextFine.setDueDate(resultSet.getDate("due_date"));
//        nextFine.setPaymentStatus(PaymentStatus.valueOf(resultSet.getString("payment_status")));
//        nextFine.setOffenceType(OffenceType.valueOf(resultSet.getString("offence_type")));
//        nextFine.setAmount(resultSet.getString("amount"));
//
//
//        finesList.add(nextFine);

//    public void insertFineInfo(Fine fine) {
//        Connection dbConn = Database.getConnection();
//
//        String sql = "INSERT INTO fine (nic, ticket_no, fine_no, date, due_date, amount, payment_status, offence_type, point_weight) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
//
//        try {
//            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
//            preparedStatement.setString(1, String.valueOf(fine.getTicketNo()));
//            preparedStatement.setString(2, String.valueOf(fine.getFineNo()));
//            preparedStatement.setString(3, String.valueOf(fine.getDate()));
//            preparedStatement.setString(4, String.valueOf(fine.getDueDate()));
//            preparedStatement.setString(5, fine.getAmount());
//            preparedStatement.setString(6, String.valueOf(fine.getPaymentStatus()));
//            preparedStatement.setString(7, String.valueOf(fine.getOffenceType()));
//            preparedStatement.setString(8, String.valueOf(fine.getPointWeight()));
//
//            preparedStatement.executeUpdate();
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }


