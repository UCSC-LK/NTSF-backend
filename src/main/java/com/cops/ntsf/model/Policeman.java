package com.cops.ntsf.model;

import com.cops.ntsf.dao.PolicemanDAO;

public class Policeman extends User{
    private String name;
    private String policeId;
//    private String police_id;
    private String nic;
    private String rank;
    private String policeStation;
//    private String police_station;

    public Policeman(String name, String policeId, String nic, String rank, String policeStation)
    {
        this.name = name;
        this.policeId = policeId;
        this.nic = nic;
        this.rank = rank;
        this.policeStation = policeStation;
    }

    public Policeman(String policeId, String nic, String email) {
        this.policeId = policeId;
        this.nic = nic;
        this.setEmail(email);
    }

    public Policeman(String policeId, String rank) {
        this.policeId=policeId;
        this.rank=rank;
    }

    //getters
    public String getPoliceStation() {
        return policeId;
    }

    public String getPoliceId() {
        return policeId;
    }

    public String getName() {
        return name;
    }

    public String getNic() {
        return nic;
    }

    public String getRank() {
        return rank;
    }

    //setters

    public void setPoliceStation(String policeStation) {
        this.policeStation = policeStation;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void policemanAdded()
    {
        PolicemanDAO policemanDAO = new PolicemanDAO();
        policemanDAO.insert(this);
    }

//    public void setTrafficPoliceInfo() {
//        PolicemanDAO policemanDAO = new PolicemanDAO();
//        policemanDAO.insertTrafficPoliceInfo((TrafficPolice) this);
//    }

//    public void setPolicemanInfo() {
//        PolicemanDAO policemanDAO = new PolicemanDAO();
//        policemanDAO.insertPolicemanInfo(this);
//    }

    public void updatePolicemanRankStation() {
        PolicemanDAO policemanDAO = new PolicemanDAO();
        policemanDAO.updatePolicemanRankStation(this);
    }
}

