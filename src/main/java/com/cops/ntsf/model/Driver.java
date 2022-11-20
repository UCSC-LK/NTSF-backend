package com.cops.ntsf.model;

import com.cops.ntsf.constants.UserType;
import com.cops.ntsf.dao.DriverDAO;

import java.time.LocalDate;

import static com.cops.ntsf.constants.UserType.DRIVER;

public class Driver extends User{
    private String licenceNo;
    private LocalDate licenceExpiryDate;
    private LocalDate licenceIssueDate;

    public String getLicenceNo() {
        return licenceNo;
    }

    public void setLicenceNo(String licenceNo) {
        this.licenceNo = licenceNo;
    }

    public LocalDate getLicenceExpiryDate() {
        return licenceExpiryDate;
    }

    public void setLicenceExpiryDate(LocalDate licenceExpiryDate) {
        this.licenceExpiryDate = licenceExpiryDate;
    }

    public LocalDate getLicenceIssueDate() {
        return licenceIssueDate;
    }

    public void setLicenceIssueDate(LocalDate licenceIssueDate) {
        this.licenceIssueDate = licenceIssueDate;
    }

    public void getDriverFromLicenseNo() {
        DriverDAO driverDAO = new DriverDAO();
        driverDAO.getDriverFromLicense(this);
    }
}
