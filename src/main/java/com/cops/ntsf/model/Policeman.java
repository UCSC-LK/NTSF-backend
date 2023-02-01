package com.cops.ntsf.model;

import com.cops.ntsf.dao.PolicemanDAO;
import org.json.JSONArray;
import org.json.JSONObject;

public class Policeman {
    private String name;
    private String police_id;
    private String nic;
    private String mobile_number;
    private String email;
    private String rank;
    private String police_station;

    public Policeman(String name, String police_id, String nic, String mobile_number, String email, String rank, String police_station)
    {
        this.name = name;
        this.police_id = police_id;
        this.nic = nic;
        this.mobile_number = mobile_number;
        this.email = email;
        this.rank = rank;
        this.police_station = police_station;
    }

    public Policeman()
    {

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

    public String getMobile_number() {
        return mobile_number;
    }

    public String getEmail() {
        return email;
    }

    public String getRank() {
        return rank;
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

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void policemanAdded()
    {
        PolicemanDAO policemanDAO = new PolicemanDAO();
        policemanDAO.createPoliceman(this);
    }

    public JSONArray getPolicemanDetails()
    {
        PolicemanDAO policemanDAO = new PolicemanDAO();
        JSONArray policemanDetailsList = policemanDAO.getPolicemanDetailsList();
        return policemanDetailsList;
    }

    public boolean policemanPolice_IDCheck(String police_id) {
        PolicemanDAO policemanDAO = new PolicemanDAO();
        boolean policemanPolice_IDCheckResult  = policemanDAO.getPolicemanPolice_IDCheckResult(police_id);
        return policemanPolice_IDCheckResult;
    }

    public boolean policemanNicCheck(String nic) {
        PolicemanDAO policemanDAO = new PolicemanDAO();
        boolean policemanNicCheckResult  = policemanDAO.getPolicemanNicCheckResult(nic);
        return policemanNicCheckResult;
    }


}

