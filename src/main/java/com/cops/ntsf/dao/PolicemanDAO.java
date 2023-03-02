package com.cops.ntsf.dao;

import com.cops.ntsf.model.Policeman;
import com.cops.ntsf.util.Database;
import com.mysql.cj.Session;
import org.json.JSONArray;
import org.json.JSONObject;
import com.cops.ntsf.service.Email;

import java.net.PasswordAuthentication;
import java.security.MessageDigest;
import java.sql.*;
import java.util.Base64;
import java.util.Properties;

public class PolicemanDAO {
    public String createPoliceman(Policeman policeman)
    {
        Connection dbConn = null;
        try {
            dbConn = Database.getConnection();
            String sql = "INSERT into policeman (name, police_id, nic, mobile_number, email,  rank, police_station, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, policeman.getName());
            preparedStatement.setString(2, policeman.getPolice_id());
            preparedStatement.setString(3, policeman.getNic());
            preparedStatement.setString(4, policeman.getMobile_number());
            preparedStatement.setString(5, policeman.getEmail());
            preparedStatement.setString(6, policeman.getRank());
            preparedStatement.setString(7, policeman.getPolice_station());
            preparedStatement.setString(8, policeman.getPassword());

            Email email = new Email();
            email.sendMail(policeman.getEmail(), policeman.getPassword());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONArray getPolicemanDetailsList() {
        Connection dbConn = null;

//        ArrayList<Policeman> policemanDetails = new ArrayList<>(); //not used
        JSONArray jsonArray = new JSONArray();

        try {
            dbConn = Database.getConnection();

            String sql = "SELECT * from policeman";

            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
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

                System.out.println(name);
                System.out.println(police_id);
                System.out.println(nic);
                System.out.println(mobile_number);
                System.out.println(email);
                System.out.println(rank);
                System.out.println(police_station);
            }

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }
    public boolean updatePolicemanDetails(Policeman policeman)
    {
        Connection dbConn = null;
        boolean alert = false;

        try {
            dbConn = Database.getConnection();
            String sql = "UPDATE policeman SET name = ?, nic = ?, mobile_number = ?, email = ?, rank = ?, police_station = ? WHERE police_id = ?";
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);

            preparedStatement.setString(1, policeman.getName());
            preparedStatement.setString(2, policeman.getNic());
            preparedStatement.setString(3, policeman.getMobile_number());
            preparedStatement.setString(4, policeman.getEmail());
            preparedStatement.setString(5, policeman.getRank());
            preparedStatement.setString(6, policeman.getPolice_station());
            preparedStatement.setString(7, policeman.getPolice_id());

            int resultSet = preparedStatement.executeUpdate();
            if (resultSet > 0) {
                System.out.println("Updated Policeman!!");
                alert = true;
            } else {
                System.out.println("Failed to update policeman!!");
                alert = false;
            }
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alert;
    }

    public boolean deletePoliceman(String police_id){
        Connection dbConn = null;
        boolean alert = false;
        try{
            dbConn = Database.getConnection();
            String sql = "DELETE from policeman where police_id = ?";

            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, police_id);

            int resultSet = preparedStatement.executeUpdate();
            if (resultSet > 0) {
                System.out.println("Deleted Policeman!!");
                alert = true;
            } else {
                System.out.println("Failed to delete policeman!!");
                alert = false;
            }
            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean getPolicemanPolice_IDCheckResult(String police_idCheck) {
        Connection dbConn = null;

        boolean alert = false;
        try {
            dbConn = Database.getConnection();

            String sql = "SELECT police_id from policeman where police_id =  ?";

            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, police_idCheck);

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

    public boolean getPolicemanNicCheckResult(String nicCheck) {
        Connection dbConn = null;

        boolean alert = false;
        try {
            dbConn = Database.getConnection();

            String sql = "SELECT nic from policeman where nic =  ?";

            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, nicCheck);

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

    public boolean getPolicemanMobileNumberCheckResult(String mobile_numberCheck) {
        Connection dbConn = null;

        boolean alert = false;
        try {
            dbConn = Database.getConnection();

            String sql = "SELECT mobile_number from policeman where mobile_number =  ?";

            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, mobile_numberCheck);

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

    public boolean getPolicemanEmailCheckResult(String emailCheck) {
        Connection dbConn = null;

        boolean alert = false;
        try {
            dbConn = Database.getConnection();

            String sql = "SELECT email from policeman where email =  ?";

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

    public JSONArray getPolicemanLoginResult(String police_id, String password) {
        Connection dbConn = null;
        JSONArray jsonArray = new JSONArray();

        try {
            dbConn = Database.getConnection();

            String sql = "SELECT rank from policeman where police_id =  ? and password = ?";

            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, police_id);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                String rank = resultSet.getString("rank");

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("authorization", true);
                jsonObject.put("police_id", police_id);
                jsonObject.put("rank", rank);

                jsonArray.put(jsonObject);
                System.out.println(rank);
            }

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public boolean getPolicemanLoginUsernameCheckResult(String police_id) {
        Connection dbConn = null;
        boolean alert = false;
        try {
            dbConn = Database.getConnection();

            String sql = "SELECT police_id from policeman where police_id =  ?";

            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, police_id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Username entered is valid!!");
                alert = true;
            } else {
                System.out.println("Username entered is invalid!!");
                alert = false;
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alert;
    }

    public JSONArray fetchPolicemanDetailsList(String police_id) {
        Connection dbConn = null;

        JSONArray jsonArray = new JSONArray();

        try {
            dbConn = Database.getConnection();

            String sql = "SELECT * from policeman WHERE police_id = ?";
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, police_id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                String name = resultSet.getString("name");
//                String police_id = resultSet.getString("police_id");
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

                System.out.println(name);
                System.out.println(police_id);
                System.out.println(nic);
                System.out.println(mobile_number);
                System.out.println(email);
                System.out.println(rank);
                System.out.println(police_station);
            }

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public void updatePoliceman(Policeman policeman) {
        Connection dbConn = null;

        try {
            dbConn = Database.getConnection();
            System.out.println("Came until the update  DAO");
            System.out.println(policeman.getName());
            System.out.println(policeman.getNic());
            System.out.println(policeman.getMobile_number());
            System.out.println(policeman.getEmail());
            System.out.println(policeman.getRank());
            System.out.println(policeman.getPolice_station());
            System.out.println(policeman.getPolice_id());
            String sql = "UPDATE policeman SET name = ?, nic = ?, mobile_number = ?, email = ?, rank = ?, police_station = ? WHERE police_id = ?";
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            preparedStatement.setString(1, policeman.getName());
            preparedStatement.setString(2, policeman.getNic());
            preparedStatement.setString(3, policeman.getMobile_number());
            preparedStatement.setString(4, policeman.getEmail());
            preparedStatement.setString(5, policeman.getRank());
            preparedStatement.setString(6, policeman.getPolice_station());
            preparedStatement.setString(7, policeman.getPolice_id());

            System.out.println("Came until the update  DAO 2");

            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public JSONArray getPolicemanDetailsListAsOIC() {
        Connection dbConn = null;

        JSONArray jsonArray = new JSONArray();

        try{
            System.out.println("Came until the DAO of  getPolicemanDetailsListAsOIC");
            dbConn = Database.getConnection();

            String sql = "SELECT * FROM policeman WHERE ";

            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                String name = resultSet.getString("name");
                String police_id = resultSet.getString("police_id");
                String nic = resultSet.getString("nic");
                String mobile_number = resultSet.getString("mobile_number");
                String email = resultSet.getString("email");
                String rank = resultSet.getString("rank");
                String police_station = resultSet.getString("police_station");

            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return jsonArray;
    }
}




