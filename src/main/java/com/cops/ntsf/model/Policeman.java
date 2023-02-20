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
    private String password;

    public Policeman(String name, String police_id, String nic, String mobile_number, String email, String rank, String police_station, String password) {
        this.name = name;
        this.police_id = police_id;
        this.nic = nic;
        this.mobile_number = mobile_number;
        this.email = email;
        this.rank = rank;
        this.police_station = police_station;
        this.password = password;
    }
    public Policeman()
    {

    }

    public Policeman(String name, String police_id, String nic, String mobile_number, String email, String rank, String police_station){
        this.name = name;
        this.police_id = police_id;
        this.nic = nic;
        this.mobile_number = mobile_number;
        this.email = email;
        this.rank = rank;
        this.police_station = police_station;
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

    public String getPassword() {
        return password;
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

    public void setPassword(String password) {
        this.password = password;
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

    public boolean policemanMobileNumberCheck(String mobile_number) {
        PolicemanDAO policemanDAO = new PolicemanDAO();
        boolean policemanMobileNumberCheckResult  = policemanDAO.getPolicemanMobileNumberCheckResult(mobile_number);
        return policemanMobileNumberCheckResult;
    }

    public boolean policemanEmailCheck(String email) {
        PolicemanDAO policemanDAO = new PolicemanDAO();
        boolean policemanEmailCheckResult  = policemanDAO.getPolicemanEmailCheckResult(email);
        return policemanEmailCheckResult;
    }


    public JSONArray login(String police_id, String password) {
        PolicemanDAO policemanDAO = new PolicemanDAO();
        JSONArray policemanLoginResult = policemanDAO.getPolicemanLoginResult(police_id, password);
        return policemanLoginResult;
    }

    public boolean LoginUsernameCheck(String police_id) {
        PolicemanDAO policemanDAO = new PolicemanDAO();
        boolean policemanLoginUsernameCheckResult  = policemanDAO.getPolicemanLoginUsernameCheckResult(police_id);
        return policemanLoginUsernameCheckResult;
    }

    public boolean deletePolicemanDetails(String police_id) {
        PolicemanDAO policemanDAO = new PolicemanDAO();
        boolean deletePolicemanDetailsResult  = policemanDAO.deletePoliceman(police_id);
        return deletePolicemanDetailsResult;
    }

    public JSONArray fetchPolicemanDetails(String police_id) {
        PolicemanDAO policemanDAO = new PolicemanDAO();
        JSONArray fetchedPolicemanDetailsList = policemanDAO.fetchPolicemanDetailsList(police_id);
        return fetchedPolicemanDetailsList;

    }


    public void policemanEdited() {
        System.out.println("Came until the update model");
        PolicemanDAO policemanDAO = new PolicemanDAO();
        policemanDAO.updatePoliceman(this);
    }

//OIC
    public JSONArray getPolicemanDetailsAsOIC() {
        PolicemanDAO policemanDAO = new PolicemanDAO();
        JSONArray policemanDetailsListAsOIC = policemanDAO.getPolicemanDetailsListAsOIC();
        return policemanDetailsListAsOIC;
    }
}

