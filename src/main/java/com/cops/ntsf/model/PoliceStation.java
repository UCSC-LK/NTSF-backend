package com.cops.ntsf.model;

import com.cops.ntsf.dao.PoliceStationDAO;
import com.cops.ntsf.dao.PolicemanDAO;
import org.json.JSONArray;

import java.sql.SQLException;
import java.util.ArrayList;

public class PoliceStation
{
    private String name;
    private String station_id;

    public PoliceStation(String station_id, String name)
    {
        this.station_id = station_id;
        this.name = name;
    }

    public PoliceStation(String station_id) {
        this.station_id=station_id;
    }

    /*public static ArrayList<PoliceStation> getPoliceStationInfo() {
        return policeStationInfo;
    }*/



    /*public static void setPoliceStationInfo(ArrayList<PoliceStation> policeStationInfo) {
        PoliceStation.policeStationInfo = policeStationInfo;
    }*/

    public String getStation_id()
    {
        return station_id;
    }

    public String getName()
    {
        return name;
    }

    public void SetStation_id() { this.station_id=station_id; }
    public void SetName() { this.name=name; }

    public JSONArray getPoliceStationDetails()
    {
        PolicemanDAO policemanDAO = new PolicemanDAO();
        JSONArray PoliceStationDetailsList = policemanDAO.getPolicemanDetailsList();
        return PoliceStationDetailsList;
    }

    public void setName(String name) {

    }

    public void setStation_id(String stationId) {
        this.station_id=station_id;
    }
}
