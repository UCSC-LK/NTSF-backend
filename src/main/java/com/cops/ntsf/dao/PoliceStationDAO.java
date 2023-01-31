package com.cops.ntsf.dao;

import com.cops.ntsf.model.PoliceStation;
import com.cops.ntsf.util.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PoliceStationDAO
{
    public class PoliceStationDAO {
    public String insert(PoliceStation PoliceStation) {
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

        public ArrayList<PoliceStation> fetchPoliceStationInfo(PoliceStation policeStation) throws SQLException {
            Connection dbConn = Database.getConnection();

            String sql = "SELECT * FROM Police_Station WHERE station_id = ?";

            PreparedStatement preparedStatement = dbConn.prepareStatement(sql);

            preparedStatement.setString(1, PoliceStation.getStation_id());

            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<PoliceStation> PoliceStationListList = new ArrayList<PoliceStation>();

            while (resultSet.next()) {
                PoliceStation nextPoliceStation;
                nextPoliceStation = new PoliceStation(PoliceStation.getStation_id());

                nextPoliceStation.setName(resultSet.getString("name"));
                nextPoliceStation.setStation_id(resultSet.getString("station_id"));

                PoliceStationList.add(nextPoliceStation);
            }
            return PoliceStationList;
        }
    }
}
