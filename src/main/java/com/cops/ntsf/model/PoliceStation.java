package com.cops.ntsf.model;

public class PoliceStation
{
    private String name;
    //private String station_id;


    public PoliceStation(String station_id, String name)
    {
        //this.station_id = station_id;
        this.name = name;
    }

    /*public String getStation_id()
    {
        return station_id;
    }*/

    public String getname()
    {
        return name;
    }
}
