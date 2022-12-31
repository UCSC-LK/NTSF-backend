package com.cops.ntsf.model;

public class Policeman {
    private String police_station;
    private String police_id;
    private String name;
    private String nic;

    public Policeman(String police_station, String police_id, String name, String nic)
    {
        this.police_station = police_station;
        this.police_id = police_id;
        this.name = name;
        this.nic = nic;
    }

    //getters
    public String getPolice_station() {
        return police_station;
    }

    public String getPolice_id() {
        return police_id;
    }

    public String getName() {
        return name;
    }

    public String getNic() {
        return nic;
    }

    //setters


    public void setPolice_station(String police_station) {
        this.police_station = police_station;
    }

    public void setPolice_id(String police_id) {
        this.police_id = police_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }
}

