package com.cops.ntsf.service;

import com.cops.ntsf.model.Driver;

public class DriverService {
    public Driver getDriverSignedUp(String licenseNo){
        Driver driver = new Driver(licenseNo);
        driver.setDriverInfo();
        return driver;
    }
}
