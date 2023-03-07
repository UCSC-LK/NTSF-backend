package com.cops.ntsf.service;

import com.cops.ntsf.model.*;

public class UserService {
    public User getUserSignedUp(String nic,
                                String email,
                                String mobileNo,
                                String password) {
        User user = new User(nic, email, mobileNo);
        user.setUserInfo();

        String userId = user.getUserId();

        if (nic != null) {
            Auth auth = new Auth(userId, password);
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