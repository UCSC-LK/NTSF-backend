package com.cops.ntsf.dao;

import com.cops.ntsf.model.Policeman;
import com.cops.ntsf.model.PolicemanAuth;
import com.cops.ntsf.util.Database;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IgpDAO {
    public boolean createPoliceman(Policeman policeman) {
        Connection dbConn = null;
        boolean alert = false;
        try {
            dbConn = Database.getConnection();
            String sql = "INSERT into policeman (name, police_id, nic, mobile_number, email,  rank, police_station, grade, profile_picture) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
            System.out.println("Hi from createPoliceman method in IgpDAO ");
            preparedStatement.setString(1, policeman.getName());
            preparedStatement.setString(2, policeman.getPolice_id());
            preparedStatement.setString(3, policeman.getNic());
            preparedStatement.setString(4, policeman.getMobile_number());
            preparedStatement.setString(5, policeman.getEmail());
            preparedStatement.setString(6, policeman.getRank());
            preparedStatement.setString(7, policeman.getPolice_station());
            preparedStatement.setString(8, policeman.getGrade());
            preparedStatement.setString(9, policeman.getProfile_picture());

            int resultSet = preparedStatement.executeUpdate();

            if (resultSet > 0) {
                System.out.println("Policeman added successfully");
                alert = true;
            } else {
                System.out.println("Policeman adding failed");
                alert = false;
            }

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alert;
    }


    public boolean createPolicemanAuth(PolicemanAuth policemanAuth) {
        Connection dbConn = null;
        boolean alert = false;

        try {
            dbConn = Database.getConnection();
            String sql = "INSERT into police_auth (police_id, otp) VALUES (?, ?)";
            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);

            preparedStatement.setString(1, policemanAuth.getPolice_id());
            preparedStatement.setString(2, policemanAuth.getOtp());

            int resultSet = preparedStatement.executeUpdate();

            if (resultSet > 0) {
                System.out.println("Policeman Auth added successfully");
                alert = true;
            } else {
                System.out.println("Policeman Auth adding failed");
                alert = false;
            }

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alert;
    }

    public JSONArray getPolicemanDetailsList() {
        Connection dbConn = null;

//        ArrayList<Policeman> policemanDetails = new ArrayList<>(); //not used
        JSONArray jsonArray = new JSONArray();

        try {
            dbConn = Database.getConnection();

            String sql = "SELECT * from policeman where active = 1";

            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String police_id = resultSet.getString("police_id");
                String nic = resultSet.getString("nic");
                String mobile_number = resultSet.getString("mobile_number");
                String email = resultSet.getString("email");
                String rank = resultSet.getString("rank");
                String grade = resultSet.getString("grade");
                String police_station = resultSet.getString("police_station");

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", name);
                jsonObject.put("police_id", police_id);
                jsonObject.put("nic", nic);
                jsonObject.put("mobile_number", mobile_number);
                jsonObject.put("email", email);
                jsonObject.put("rank", rank);
                jsonObject.put("grade", grade);
                jsonObject.put("police_station", police_station);

                jsonArray.put(jsonObject);

            }

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public boolean updatePolicemanDetails(Policeman policeman) {
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

    public boolean deletePoliceman(String police_id) {
        Connection dbConn = null;
        boolean alert = false;
        try {
            dbConn = Database.getConnection();
            String sql = "UPDATE policeman set  active = 0 WHERE police_id = ?";

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
        return alert;
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

    public JSONArray getPolicemanLoginResult(String police_id, String password, boolean firsTimeLogin) {
        System.out.println("Hi from Login DAO");
        Connection dbConn = null;
        JSONArray jsonArray = new JSONArray();

        if (firsTimeLogin == true) {
            System.out.println("First Time Login");
            try {
                dbConn = Database.getConnection();
                System.out.println("police_id: " + police_id);
                System.out.println("password: " + password);
                String sql = "SELECT * FROM policeman p JOIN police_auth pa ON p.police_id = pa.police_id WHERE p.active = 1 AND p.police_id = ? AND pa.otp = ?";
                PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
                preparedStatement.setString(1, police_id);
                preparedStatement.setString(2, password);

                ResultSet resultSet = preparedStatement.executeQuery();
                System.out.println(resultSet);
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("name"));
                    System.out.println(resultSet.getString("rank"));
                    System.out.println(resultSet.getString("position"));
                    String name = resultSet.getString("name");
                    String rank = resultSet.getString("rank");
                    String position = resultSet.getString("position");
                    String police_station = resultSet.getString("police_station");

                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("authorization", true);
                    jsonObject.put("police_id", police_id);
                    jsonObject.put("name", name);
                    jsonObject.put("rank", rank);
                    jsonObject.put("position", position);
                    jsonObject.put("police_station", police_station);

                    jsonArray.put(jsonObject);
                    System.out.println(rank);

//                    String sql2 = "UPDATE police_auth SET login_flag = ? WHERE police_id = ?";
//                    PreparedStatement preparedStatement2 = dbConn.prepareStatement(sql2);
//                    preparedStatement2.setInt(1, 1);
//                    preparedStatement2.setString(2, police_id);
//                    preparedStatement2.executeUpdate();
//                    preparedStatement2.close();
                }
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Not First Time Login");
            try {
                dbConn = Database.getConnection();
                System.out.println("police_id: " + police_id);
                System.out.println("password: " + password);
                //String sql = "SELECT name, rank, position, police_station from policeman where police_id =  ? and password = ? and active = 1";
                String sql = "SELECT * FROM policeman p JOIN police_auth pa ON p.police_id = pa.police_id WHERE p.active = 1 AND p.police_id = ? AND pa.password = ?";
                PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
                preparedStatement.setString(1, police_id);
                preparedStatement.setString(2, password);

                ResultSet resultSet = preparedStatement.executeQuery();
                System.out.println(resultSet);
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("name"));
                    System.out.println(resultSet.getString("rank"));
                    System.out.println(resultSet.getString("position"));
                    String name = resultSet.getString("name");
                    String rank = resultSet.getString("rank");
                    String position = resultSet.getString("position");
                    String police_station = resultSet.getString("police_station");

                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("authorization", true);
                    jsonObject.put("police_id", police_id);
                    jsonObject.put("name", name);
                    jsonObject.put("rank", rank);
                    jsonObject.put("position", position);
                    jsonObject.put("police_station", police_station);

                    jsonArray.put(jsonObject);
                    System.out.println(rank);
                }

                resultSet.close();
                preparedStatement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }

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
            while (resultSet.next()) {
                String name = resultSet.getString("name");
//              String police_id = resultSet.getString("police_id");
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

        try {
            System.out.println("Came until the DAO of  getPolicemanDetailsListAsOIC");
            dbConn = Database.getConnection();

            String sql = "SELECT * FROM policeman WHERE active = 1 ";

            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String police_id = resultSet.getString("police_id");
                String nic = resultSet.getString("nic");
                String mobile_number = resultSet.getString("mobile_number");
                String email = resultSet.getString("email");
                String rank = resultSet.getString("rank");
                String police_station = resultSet.getString("police_station");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

}




