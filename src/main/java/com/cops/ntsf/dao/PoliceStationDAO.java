package com.cops.ntsf.dao;

import com.cops.ntsf.model.PoliceStation;
import com.cops.ntsf.util.Database;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PoliceStationDAO {
    public String insertPoliceStation(PoliceStation PoliceStation) throws SQLException {
        Connection dbConnect = null;
        try {
            dbConnect = Database.getConnection();
            String sql = "INSERT INTO Police_Station(name, station_id) VALUES (?,?)";
            PreparedStatement preparedStatement = dbConnect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, PoliceStation.getStation_id());
            preparedStatement.setString(2, PoliceStation.getName());

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (dbConnect != null) try {
                dbConnect.close();
            } catch (Exception ignore) {
            }
        }
        return null;
    }

    public JSONArray viewPoliceStationDetailsList() {
        Connection dbConn = null;

//        ArrayList<Policeman> policemanDetails = new ArrayList<>(); //not used
        JSONArray jsonArray = new JSONArray();

        try {
            dbConn = Database.getConnection();

            String sql = "SELECT * from police_Station";

            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                String name = resultSet.getString("name");
                String station_id = resultSet.getString("station_id");

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", name);
                jsonObject.put("station_id", station_id);

                jsonArray.put(jsonObject);

                System.out.println(name);
                System.out.println(station_id);
            }

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (dbConn != null) try {
                dbConn.close();
            } catch (Exception ignore) {
            }
        }
        return jsonArray;
    }
}

        /*public ArrayList<PoliceStation> fetchPoliceStationInfo(PoliceStation policestation) throws SQLException {
            Connection dbConn = Database.getConnection();

            String sql = "SELECT * FROM Police_Station WHERE station_id = ?";

            PreparedStatement preparedstatement = dbConn.prepareStatement(sql);

            preparedstatement.setString(1, PoliceStation.getStation_id());

            ResultSet resultSet = preparedstatement.executeQuery();

            ArrayList<PoliceStation> PoliceStationListList = new ArrayList<PoliceStation>();

            while (resultSet.next()) {
                PoliceStation nextPoliceStation;
                nextPoliceStation = new PoliceStation(PoliceStation.getStation_id());

                nextPoliceStation.setName(resultSet.getString("name"));
                nextPoliceStation.setStationId(resultSet.getString("station_id"));

                PoliceStationList.add(nextPoliceStation);
            }
            return PoliceStationList;
        }*/

