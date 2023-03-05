package com.cops.ntsf.dao;

import com.cops.ntsf.util.Database;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OicDAO {
    public JSONArray getPolicemanDetailsList() {
        Connection dbConn = null;
        JSONArray jsonArray = new JSONArray();
        try{
            dbConn = Database.getConnection();
            String sql = "SELECT * FROM policeman";

            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String name = resultSet.getString("name");
                String police_id = resultSet.getString("police_id");
                String nic = resultSet.getString("nic");
                String mobile_number = resultSet.getString("mobile_number");
                String email = resultSet.getString("email");
                String rank = resultSet.getString("rank");
                String police_station = resultSet.getString("police_station");

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", name);
                jsonObject.put("police_id", police_id);
                jsonObject.put("nic", nic);
                jsonObject.put("mobile_number", mobile_number);
                jsonObject.put("email", email);
                jsonObject.put("rank", rank);
                jsonObject.put("police_station", police_station);

                jsonArray.put(jsonObject);
            }
            resultSet.close();
            preparedStatement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonArray;
    }
}
