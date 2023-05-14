package com.cops.ntsf.model;

import com.cops.ntsf.dao.M_PoliceStationDAO;
import org.json.JSONArray;

public class M_PoliceStation {
    private String branch_name;
    private String address;
    private String district;
    private String province;
    private String contact_number;
    private String email;

    public M_PoliceStation(String branch_name, String address, String district, String province, String contact_number, String email) {
        this.branch_name = branch_name;
        this.address = address;
        this.district = district;
        this.province = province;
        this.contact_number = contact_number;
        this.email = email;
    }

    public M_PoliceStation() {
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
        M_PoliceStationDAO policeStationDAO = new M_PoliceStationDAO();
        policeStationDAO.createPoliceStation(this);
    }

    public boolean policeStationContact_NumberCheck(String contact_number) {
        M_PoliceStationDAO policeStationDAO = new M_PoliceStationDAO();
        boolean policeStationContact_NumberCheckResult = policeStationDAO.getPoliceStationContact_NumberCheckResult(contact_number);
        return policeStationContact_NumberCheckResult;
    }

    public boolean policeStationEmailCheck(String email) {
        M_PoliceStationDAO policeStationDAO = new M_PoliceStationDAO();
        boolean policeStationEmailCheckResult = policeStationDAO.getPoliceStationEmailCheckResult(email);
        return policeStationEmailCheckResult;
    }

    public boolean policeStationBranch_NameCheck(String branch_name) {
        M_PoliceStationDAO policeStationDAO = new M_PoliceStationDAO();
        boolean policeStationBranch_NameCheckResult = policeStationDAO.getPoliceStationBranch_NameCheckResult(branch_name);
        return policeStationBranch_NameCheckResult;
    }

    public JSONArray getPoliceStationDetails() {
        M_PoliceStationDAO policeStationDAO = new M_PoliceStationDAO();
        JSONArray policeStationDetailsList = policeStationDAO.getPoliceStationDetailsList();
        return policeStationDetailsList;
    }
}
