package com.cops.ntsf.dao;

import com.cops.ntsf.model.Offence;
import com.cops.ntsf.util.Database;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OffenceDAO {

    public JSONArray getOffenceDetailsList() {
        Connection dbConn = null;

        JSONArray jsonArray = new JSONArray();

        try {
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

    public void createOffence(Offence offence) {
        Connection dbConn = null;
        try {
            dbConn = Database.getConnection();
            String sql = "INSERT INTO offence (offence_no, offence_type, description, amount, demerit_points) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);

            preparedStatement.setInt(1, offence.getOffence_no());
            preparedStatement.setString(2, offence.getOffence_type());
            preparedStatement.setString(3, offence.getDescription());
            preparedStatement.setInt(4, offence.getAmount());
            preparedStatement.setInt(5, offence.getDemerit_points());

            preparedStatement.executeUpdate();
            preparedStatement.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteOffence(int offence_no) {
        Connection dbConn = null;
        boolean alert = false;

        try {
            dbConn = Database.getConnection();
            String sql = "DELETE FROM offence WHERE offence_no = ?";
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setInt(1, offence_no);

            int resultSet = preparedStatement.executeUpdate();
            if (resultSet > 0) {
                System.out.println("Offence No: " + offence_no + " has been deleted");
                alert = true;
            } else {
                System.out.println("Offence No: " + offence_no + " does not exist");
                alert = false;
            }
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return alert;
    }

    public boolean offenceDescriptionCheck(String description) {
        Connection dbConn = null;
        boolean alert = false;

        try {
            dbConn = Database.getConnection();
            String sql = "SELECT * FROM offence WHERE description = ?";
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, description);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Offence Description: " + description + " already exists");
                alert = true;
            } else {
                System.out.println("Offence Description: " + description + " does not exist");
                alert = false;
            }
            resultSet.close();
            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return alert;
    }

    public void fetchOffenceByOffenceNo(Offence offence) {
        Connection dbConn = Database.getConnection();

        String sql = "SELECT * FROM offence WHERE offence_no = ?";

        try {
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setInt(1, offence.getOffence_no());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                offence.setOffence_type(resultSet.getString("offence_type"));
                offence.setDescription(resultSet.getString("description"));
                offence.getAmount(resultSet.getInt("amount"));
                offence.setDemerit_points(resultSet.getInt("demerit_points"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
