package com.cops.ntsf.model;

import com.cops.ntsf.dao.IgpDAO;
import com.cops.ntsf.dao.OicDAO;
import org.json.JSONArray;

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
        this.name = name; //instead setters can be called here //setname(name);
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
        IgpDAO igpDAO = new IgpDAO();
        igpDAO.createPoliceman(this);
    }

    public JSONArray getPolicemanDetails()
    {
        IgpDAO igpDAO = new IgpDAO();
        JSONArray policemanDetailsList = igpDAO.getPolicemanDetailsList();
        return policemanDetailsList;
    }
    public boolean policemanPolice_IDCheck(String police_id) {
        IgpDAO igpDAO = new IgpDAO();
        boolean policemanPolice_IDCheckResult  = igpDAO.getPolicemanPolice_IDCheckResult(police_id);
        return policemanPolice_IDCheckResult;
    }

    public boolean policemanNicCheck(String nic) {
        IgpDAO igpDAO = new IgpDAO();
        boolean policemanNicCheckResult  = igpDAO.getPolicemanNicCheckResult(nic);
        return policemanNicCheckResult;
    }

    public boolean policemanMobileNumberCheck(String mobile_number) {
        IgpDAO igpDAO = new IgpDAO();
        boolean policemanMobileNumberCheckResult  = igpDAO.getPolicemanMobileNumberCheckResult(mobile_number);
        return policemanMobileNumberCheckResult;
    }

    public boolean policemanEmailCheck(String email) {
        IgpDAO igpDAO = new IgpDAO();
        boolean policemanEmailCheckResult  = igpDAO.getPolicemanEmailCheckResult(email);
        return policemanEmailCheckResult;
    }


    public JSONArray login(String police_id, String password) {
        IgpDAO igpDAO = new IgpDAO();
        JSONArray policemanLoginResult = igpDAO.getPolicemanLoginResult(police_id, password);
        return policemanLoginResult;
    }

    public boolean LoginUsernameCheck(String police_id) {
        IgpDAO igpDAO = new IgpDAO();
        boolean policemanLoginUsernameCheckResult  = igpDAO.getPolicemanLoginUsernameCheckResult(police_id);
        return policemanLoginUsernameCheckResult;
    }

    public boolean deletePolicemanDetails(String police_id) {
        IgpDAO igpDAO = new IgpDAO();
        boolean deletePolicemanDetailsResult  = igpDAO.deletePoliceman(police_id);
        return deletePolicemanDetailsResult;
    }

    public JSONArray fetchPolicemanDetails(String police_id) {
        IgpDAO igpDAO = new IgpDAO();
        JSONArray fetchedPolicemanDetailsList = igpDAO.fetchPolicemanDetailsList(police_id);
        return fetchedPolicemanDetailsList;

    }


    public void policemanEdited() {
        System.out.println("Came until the update model");
        IgpDAO igpDAO = new IgpDAO();
        igpDAO.updatePoliceman(this);
    }

//OIC
    public JSONArray getPolicemanDetailsAsOIC() {
        OicDAO oicDAO = new OicDAO();
        JSONArray policemanDetailsListAsOIC = oicDAO.getPolicemanDetailsList();
        return policemanDetailsListAsOIC;
    }
}

