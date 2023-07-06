package com.cops.ntsf.model;

import com.cops.ntsf.dao.DriverDAO;
import com.mysql.cj.protocol.x.SyncMessageSender;

import java.sql.Date;

public class Driver extends User {
    private String licenseNo;
    private Date issueDate;
    private Date expireDate;
    private String administrativeNo;
    private String holdersSignature;
    private Date dob;
    private String address;
    private String vehicleCategories;

    public Driver() {
    }

    public Driver(String licenseNo) {
        super(licenseNo);
    }

    public String getLicenceNo() {
        return licenseNo;
    }

    public void setLicenceNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public void getDriverFromLicenseNo() {
        DriverDAO driverDAO = new DriverDAO();
        driverDAO.getDriverFromLicense(this);
    }

    public void getDriverInfo() {
        DriverDAO driverDAO = new DriverDAO();
        driverDAO.fetchDriverInfo(this);
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;

    }

    public void setAdministrativeNo(String vehicleCategories) {
        this.vehicleCategories = vehicleCategories;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public String getAdministrativeNo() {
        return administrativeNo;
    }

    public void setHoldersSignature(String holdersSignature) {
        this.holdersSignature = holdersSignature;
    }

    public String getHoldersSignature() {
        return holdersSignature;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getDob() {
        return dob;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
    }

    public String getVehicleCategories() {
        return vehicleCategories;
    }

    public void setVehicleCategories(String vehicleCategories) {
        this.vehicleCategories = vehicleCategories;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

//    public String getNICByLicenseNo() {
//        DriverDAO driverDAO = new DriverDAO();
//        return driverDAO.fetchNICByLicenseNo(this);
//    }

    public String getNICByLicenseNo(String licenseNo) {
        DriverDAO driverDAO = new DriverDAO();
        String nicGotFromLicenseNo = driverDAO.fetchNICByLicenseNo(licenseNo);
        System.out.println("nicGotFromLicenseNo: " + nicGotFromLicenseNo);
        return nicGotFromLicenseNo;
    }

    public boolean checkingUser_IDasLicenseNo(String license_no) {
        DriverDAO driverDAO = new DriverDAO();
        return driverDAO.checkUser_IDasLicenseNo(license_no);
    }
}
