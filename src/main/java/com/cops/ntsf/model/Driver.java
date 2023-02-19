package com.cops.ntsf.model;

import com.cops.ntsf.constants.UserType;
import com.cops.ntsf.dao.DriverDAO;

public class Driver extends User {
    private String licenceNo;

    public Driver() {
        super();
    }
    
    public Driver(String nic) {
        super();
    }

    public Driver(String userId, String licenseNo) {
        super(userId);
        this.licenceNo = licenseNo;
    }

    public Driver(String userId, UserType userType) {
        super(userId, userType);
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

    public void setDriverInfo() {
        DriverDAO driverDAO = new DriverDAO();
        driverDAO.insertDriverInfo(this);
    }

    public void getDriverInfo() {
        DriverDAO driverDAO = new DriverDAO();
        driverDAO.fetchDriverLicence(this);
    }
}
