package com.cops.ntsf.model;

import com.cops.ntsf.constants.PoliceRank;
import com.cops.ntsf.dao.PolicemanDAO;

public class Policeman extends User{
    private static String policeId;
    private PoliceRank policeRank;
    private String name;
//    private String policeId;
//    private String police_id;
    private String nic;
//    private String rank;
    private String policeStation;
    private String password;
//    private String police_station;

    public Policeman(String policeId, PoliceRank policeRank, String policeStation)
    {
        this.policeId = policeId;
        this.policeRank = policeRank;
        this.policeStation = policeStation;
    }

    public Policeman(String policeId, String name, String nic, String email, String mobileNo, String password) {
        this.policeId=policeId;
        this.name=name;
        this.nic=nic;
        this.setEmail(email);
        this.setMobileNo(mobileNo);
        this.password=password;
    }

//    public Policeman(String policeId, String nic, String email, String mobileNo, String password) {
//        this.policeId = policeId;
//        this.nic = nic;
//        this.setEmail(email);
//    }

//    public Policeman(String policeId, PoliceRank policeRank) {
//        this.policeId=policeId;
//        this.policeRank=policeRank;
//    }

    //getters
    public String getPoliceStation() {
        return policeId;
    }


    public String getName() {
        return name;
    }

    public String getNic() {
        return nic;
    }

//    public String getRank() {
//        return rank;
//    }

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

//    public void setRank(String rank) {
//        this.rank = rank;
//    }

//    public void policemanAdded()
//    {
//        PolicemanDAO policemanDAO = new PolicemanDAO();
//        policemanDAO.insert(this);
//    }

    public void setPolicemanInfo() {
        PolicemanDAO policemanDAO = new PolicemanDAO();
        policemanDAO.insertPolicemanInfo(this);
    }

    public void updatePolicemanRankStation() {
        PolicemanDAO policemanDAO = new PolicemanDAO();
        policemanDAO.updatePolicemanRankStation(this);
    }

    public String getPassword() {
        return password;
    }

    public void getPolicemanInfo() {
        PolicemanDAO policemanDAO = new PolicemanDAO();
        policemanDAO.fetchPolicemanInfo(this);
    }

    public String getPoliceId() {
        return policeId;
    }

    public String getPoliceRank() {
        return policeRank;
    }
}

