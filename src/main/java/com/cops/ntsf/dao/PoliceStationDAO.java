package com.cops.ntsf.dao;

import com.cops.ntsf.model.PoliceStation;
import com.cops.ntsf.util.Database;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;

public class PoliceStationDAO {
    public String createPoliceStation(PoliceStation policeStation) {
        Connection dbConn = null;
        try {
            dbConn = Database.getConnection();
            String sql = "INSERT into police_station (branch_name, address, district, province, contact_number, email) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, policeStation.getBranch_name());
            preparedStatement.setString(2, policeStation.getAddress());
            preparedStatement.setString(3, policeStation.getDistrict());
            preparedStatement.setString(4, policeStation.getProvince());
            preparedStatement.setString(5, policeStation.getContact_number());
            preparedStatement.setString(6, policeStation.getEmail());

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONArray getPoliceStationOptionsList() {
        Connection dbConn = null;
        boolean alert = false;

        JSONArray jsonArray = null;
        try {
            System.out.println("DAO to get the list of policestations to addPoliceman.js dynamically as an array is called");
            dbConn = Database.getConnection();
            String sql = "SELECT police_station FROM policeman GROUP BY police_station";
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            jsonArray = new JSONArray();

            while (resultSet.next()) {
                String police_station = resultSet.getString("police_station");
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("police_station", police_station);
                jsonArray.put(jsonObject);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(jsonArray);
        return jsonArray;
    }

    public JSONArray getPoliceStationDetailsList() {
        Connection dbConn = null;

//        ArrayList<Policeman> policemanDetails = new ArrayList<>(); //not used
        JSONArray jsonArray = new JSONArray();

        try {
            dbConn = Database.getConnection();

            String sql = "SELECT * from police_station";

            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String branch_name = resultSet.getString("branch_name");
                String address = resultSet.getString("address");
                String district = resultSet.getString("district");
                String province = resultSet.getString("province");
                String contact_number = resultSet.getString("contact_number");
                String email = resultSet.getString("email");

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("branch_name", branch_name);
                jsonObject.put("address", address);
                jsonObject.put("district", district);
                jsonObject.put("province", province);
                jsonObject.put("contact_number", contact_number);
                jsonObject.put("email", email);

                jsonArray.put(jsonObject);

                System.out.println(branch_name);
                System.out.println(address);
                System.out.println(district);
                System.out.println(province);
                System.out.println(contact_number);
                System.out.println(email);
            }

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }


    public boolean getPoliceStationBranch_NameCheckResult(String branch_nameCheck) {
        Connection dbConn = null;

        boolean alert = false;
        try {
            dbConn = Database.getConnection();
            String sql = "SELECT branch_name from police_station WHERE branch_name = ?";

            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, branch_nameCheck);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Duplicate Entry!!");
                return true;
            } else {
                System.out.println("New Entry!!");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alert;
    }

    public boolean getPoliceStationContact_NumberCheckResult(String contact_numberCheck) {
        Connection dbConn = null;

        boolean alert = false;

        try {
            dbConn = Database.getConnection();
            String sql = "SELECT contact_number from police_station WHERE contact_number = ?";

            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, contact_numberCheck);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Duplicate Entry!!");
                alert = true;
            } else {
                System.out.println("New Entry!!");
                alert = false;
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alert;
    }

    public boolean getPoliceStationEmailCheckResult(String emailCheck) {
        Connection dbConn = null;

        boolean alert = false;
        try {
            dbConn = Database.getConnection();

            String sql = "SELECT email from police_station WHERE email = ?";

            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, emailCheck);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Duplicate Entry!!");
                alert = true;
            } else {
                System.out.println("New Entry!!");
                alert = false;
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alert;
    }

    public JSONArray fetchPoliceStationDetailsList(String branch_name) {
        Connection dbConn = null;

        JSONArray jsonArray = new JSONArray();

        try {
            dbConn = Database.getConnection();

            String sql = "SELECT * from police_station WHERE branch_name = ?";

            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, branch_name);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String branch_name1 = resultSet.getString("branch_name");
                String address = resultSet.getString("address");
                String district = resultSet.getString("district");
                String province = resultSet.getString("province");
                String contact_number = resultSet.getString("contact_number");
                String email = resultSet.getString("email");

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("branch_name", branch_name1);
                jsonObject.put("address", address);
                jsonObject.put("district", district);
                jsonObject.put("province", province);
                jsonObject.put("contact_number", contact_number);
                jsonObject.put("email", email);

                jsonArray.put(jsonObject);

                System.out.println(branch_name1);
                System.out.println(address);
                System.out.println(district);
                System.out.println(province);
                System.out.println(contact_number);
                System.out.println(email);
            }

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public void updatePoliceStationDetails(PoliceStation policeStation) {
        Connection dbConn = null;
        System.out.println("updatePoliceStationDetails in DAO");

        try {
            System.out.println("updatePoliceStationDetails in DAO try");
            System.out.println(policeStation.getBranch_name());
            System.out.println(policeStation.getAddress());
            System.out.println(policeStation.getDistrict());
            System.out.println(policeStation.getProvince());
            System.out.println(policeStation.getContact_number());
            System.out.println(policeStation.getEmail());
            dbConn = Database.getConnection();
            String sql = "UPDATE police_station SET address = ?, district = ?, province = ?, contact_number = ?, email = ? WHERE branch_name = ?";
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, policeStation.getAddress());
            preparedStatement.setString(2, policeStation.getDistrict());
            preparedStatement.setString(3, policeStation.getProvince());
            preparedStatement.setString(4, policeStation.getContact_number());
            preparedStatement.setString(5, policeStation.getEmail());
            preparedStatement.setString(6, policeStation.getBranch_name());

            preparedStatement.executeUpdate();
            preparedStatement.close();

            System.out.println("updatePoliceStationDetails in DAO try end");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deletePoliceStation(String branch_name) {
        Connection dbConn = null;
        boolean alert = false;
        try {
            dbConn = Database.getConnection();
            String sql = "DELETE from police_station WHERE branch_name = ?";
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, branch_name);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            alert = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alert;

    }
}
