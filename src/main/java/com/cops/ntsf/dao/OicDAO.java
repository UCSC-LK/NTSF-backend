package com.cops.ntsf.dao;

import com.cops.ntsf.util.Database;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OicDAO {
    public JSONArray getPolicemanDetailsList(String oic_police_station){
        Connection dbConn = null;
        JSONArray jsonArray = new JSONArray();
        try{
            dbConn = Database.getConnection();
            String sql = "SELECT * FROM policeman where police_station = ? and rank = 'policeman'";

            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, oic_police_station);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String name = resultSet.getString("name");
                String police_id = resultSet.getString("police_id");
                String nic = resultSet.getString("nic");
                String mobile_number = resultSet.getString("mobile_number");
                String email = resultSet.getString("email");
                String rank = resultSet.getString("rank");
                String position = resultSet.getString("position");
//                String police_station = resultSet.getString("police_station");

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", name);
                jsonObject.put("police_id", police_id);
                jsonObject.put("nic", nic);
                jsonObject.put("mobile_number", mobile_number);
                jsonObject.put("email", email);
                jsonObject.put("rank", rank);
                jsonObject.put("position", position);
//                jsonObject.put("police_station", police_station);

                jsonArray.put(jsonObject);
            }
            resultSet.close();
            preparedStatement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonArray;
    }

    public void editPosition(String position, String police_id) {
        Connection dbConn = null;

        try{
            dbConn = Database.getConnection();
            String sql = "UPDATE policeman SET position = ? WHERE police_id = ?";

            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, position);
            preparedStatement.setString(2, police_id);
            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
