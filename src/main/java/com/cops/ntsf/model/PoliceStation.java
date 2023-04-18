package com.cops.ntsf.model;

import com.cops.ntsf.dao.PoliceStationDAO;
import org.json.JSONArray;

public class PoliceStation {
    private String branch_name;
    private String address;
    private String district;
    private String province;
    private String contact_number;
    private String email;

    public PoliceStation(String branch_name, String address, String district, String province, String contact_number, String email) {
        this.branch_name = branch_name;
        this.address = address;
        this.district = district;
        this.province = province;
        this.contact_number = contact_number;
        this.email = email;
    }

    public PoliceStation() {
    }

    //getters
    public String getBranch_name() {
        return branch_name;
    }

    public String getAddress() {
        return address;
    }

    public String getDistrict() {
        return district;
    }

    public String getProvince() {
        return province;
    }

    public String getContact_number() {
        return contact_number;
    }

    public String getEmail() {
        return email;
    }

    //setters
    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void policeStationAdded() {
        PoliceStationDAO policeStationDAO = new PoliceStationDAO();
        policeStationDAO.createPoliceStation(this);
    }

    public JSONArray getPoliceStationOptions() {
        PoliceStationDAO policeStationDAO = new PoliceStationDAO();
        JSONArray policeStationOptionsList = policeStationDAO.getPoliceStationOptionsList();
        return policeStationOptionsList;
    }

    public boolean policeStationContact_NumberCheck(String contact_number) {
        PoliceStationDAO policeStationDAO = new PoliceStationDAO();
        boolean policeStationContact_NumberCheckResult = policeStationDAO.getPoliceStationContact_NumberCheckResult(contact_number);
        return policeStationContact_NumberCheckResult;
    }

    public boolean policeStationEmailCheck(String email) {
        PoliceStationDAO policeStationDAO = new PoliceStationDAO();
        boolean policeStationEmailCheckResult = policeStationDAO.getPoliceStationEmailCheckResult(email);
        return policeStationEmailCheckResult;
    }

    public boolean policeStationBranch_NameCheck(String branch_name) {
        PoliceStationDAO policeStationDAO = new PoliceStationDAO();
        boolean policeStationBranch_NameCheckResult = policeStationDAO.getPoliceStationBranch_NameCheckResult(branch_name);
        return policeStationBranch_NameCheckResult;
    }

    public JSONArray getPoliceStationDetails() {
        PoliceStationDAO policeStationDAO = new PoliceStationDAO();
        JSONArray policeStationDetailsList = policeStationDAO.getPoliceStationDetailsList();
        return policeStationDetailsList;
    }

    public JSONArray fetchPoliceStationDetails(String branch_name) {
        PoliceStationDAO policeStationDAO = new PoliceStationDAO();
        JSONArray fetchedpoliceStationDetailsList = policeStationDAO.fetchPoliceStationDetailsList(branch_name);
        return fetchedpoliceStationDetailsList;
    }

    public void updatePoliceStation() {
        System.out.println("updatePoliceStation() in PoliceStation.java is called");
        PoliceStationDAO policeStationDAO = new PoliceStationDAO();
        policeStationDAO.updatePoliceStationDetails(this);

    }

    public boolean deletePoliceStationDetails(String branch_name) {
        PoliceStationDAO policeStationDAO = new PoliceStationDAO();
        boolean deletePoliceStationDetailsResult = policeStationDAO.deletePoliceStation(branch_name);
        return deletePoliceStationDetailsResult;
    }
}
