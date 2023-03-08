package com.cops.ntsf.dao;

import com.cops.ntsf.util.Database;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class OffenceDAO {

    public JSONArray getOffenceDetailsList() {
        Connection dbConn = null;

        JSONArray jsonArray = new JSONArray();

        try{
            dbConn = Database.getConnection();
            String sql = "SELECT * FROM offence";

            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int offence_no = resultSet.getInt("offence_no");
                String offence_type = resultSet.getString("offence_type");
                String description = resultSet.getString("description");
                int amount = resultSet.getInt("amount");
                int demerit_points = resultSet.getInt("demerit_points");

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("offence_no", offence_no);
                jsonObject.put("offence_type", offence_type);
                jsonObject.put("description", description);
                jsonObject.put("amount", amount);
                jsonObject.put("demerit_points", demerit_points);

                jsonArray.put(jsonObject);

                System.out.println("Offence No: " + offence_no);
                System.out.println("Offence Type: " + offence_type);
                System.out.println("Description: " + description);
                System.out.println("Amount: " + amount);
                System.out.println("Demerit Points: " + demerit_points);
            }
            resultSet.close();
            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }
}
