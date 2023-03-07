package com.cops.ntsf.model;

import com.cops.ntsf.dao.DriverDAO;

import java.sql.Date;

public class Driver extends User {
    private String licenceNo;
    private Date issueDate;
    private Date expireDate;
    private String administrativeNo;
    private String holdersSignature;
    private Date dob;
    private String address;

    public Driver(String nic) {
        super(nic);
    }

    public String getLicenceNo() {
        return licenceNo;
    }

    public void setLicenceNo(String licenceNo) {
        this.licenceNo = licenceNo;
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

    public void setAdministrativeNo(String administrativeNo) {
        this.administrativeNo = administrativeNo;
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
}
