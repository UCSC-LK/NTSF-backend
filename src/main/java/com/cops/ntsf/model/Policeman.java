package com.cops.ntsf.model;

import com.cops.ntsf.constants.PoliceRank;
import com.cops.ntsf.dao.AuthDAO;
import com.cops.ntsf.dao.PolicemanDAO;

public class Policeman extends User{
    private static String police_id;
    private String rank;

    public Policeman(String name, String police_id, String nic, String rank, String police_station) {
        this.name=name;
        this.police_id=police_id;
        this.nic=nic;
        this.rank=rank;
        this.police_station=police_station;
    }

    public void setPoliceRank(PoliceRank policeRank) {
        this.policeRank = policeRank;
    }

    private PoliceRank policeRank;
    private String name;
//    private String police_id;
//    private String police_id;
    private String nic;
//    private String rank;
    private String police_station;
    private String password;
//    private String police_station;

    public Policeman(String police_id, PoliceRank policeRank, String police_station)
    {
        this.police_id = police_id;
        this.policeRank = policeRank;
        this.police_station = police_station;
    }

    public Policeman(String police_id, String name, String nic, String email, String mobileNo, String password) {
        this.police_id=police_id;
        this.name=name;
        this.nic=nic;
        this.setEmail(email);
        this.setMobileNo(mobileNo);
        this.password=password;
    }

    public Policeman() {

    }

//    public Policeman(String police_id, String nic, String email, String mobileNo, String password) {
//        this.police_id = police_id;
//        this.nic = nic;
//        this.setEmail(email);
//    }

//    public Policeman(String police_id, PoliceRank policeRank) {
//        this.police_id=police_id;
//        this.policeRank=policeRank;
//    }

    //getters
    public String getPoliceStation() {
        return police_id;
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

    public void setPoliceStation(String police_station) {
        this.police_station = police_station;
    }

    public void setPoliceId(String police_id) {
        this.police_id = police_id;
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

    public void policemanAdded()
    {
        PolicemanDAO policemanDAO = new PolicemanDAO();
        policemanDAO.insert(this);
    }

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
        return police_id;
    }

    public PoliceRank getPoliceRank() {
        return policeRank;
    }

    public void getPolicemanFromPoliceId() {
        PolicemanDAO policemanDAO = new PolicemanDAO();
        policemanDAO.getPolicemanFromPoliceId(this);
    }

    public void setPassword(String password) {
        this.password=password;
    }

    public String getRank() {
        return rank;
    }
}

