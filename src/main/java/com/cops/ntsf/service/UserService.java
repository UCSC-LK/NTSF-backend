package com.cops.ntsf.service;

import com.cops.ntsf.model.*;

public class UserService {
    public User getUserSignedUp(String nic,
                                String hashedPassword,
                                String email,
                                String mobileNo) {
        User user = new User(nic, email, mobileNo);
        user.setUserInfo();

        Integer userId = user.getUserIdFromNic(nic);

        if (nic != null) {
            Auth auth = new Auth(userId, hashedPassword);
            auth.setAuthInfo();
        }
        return user;
    }

    public Profile getProfileInfo(String nic) {
        User user = new User(nic);
        user.getUserInfo();

        Driver driver = new Driver(nic);
        driver.getDriverInfo();

        Vehicle vehicle = new Vehicle(nic);
        vehicle.getVehicleInfo();

        People people = new People(nic);
        people.getCivilInfo();

        return new Profile(user, driver, vehicle, people);
    }
}