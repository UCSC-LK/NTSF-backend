package com.cops.ntsf.model;

import com.cops.ntsf.dao.PoliceStationDAO;

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

    public ArrayList<PoliceStation> getPoliceStationInfo() throws SQLException {
        PoliceStationDAO PoliceStationDAO = new PoliceStationDAO();
        return PoliceStationDAO.fetchPoliceStationInfo(this);
    }

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

    public void PoliceStationAdded()
    {
        PoliceStationDAO PoliceStationDAO = new PoliceStationDAO();
        PoliceStationDAO.insert(this);
    }

}
