package com.cops.ntsf.service;

import com.cops.ntsf.model.*;

public class UserService {
    public User getUserSignedUp(String nic,
                                String email,
                                String password) {
        User user = new User(nic, email);
        user.setUserInfo();

        String userId = user.getUserId();

        if (nic != null) {
            Auth auth = new Auth(userId, password);
            auth.setAuthInfo();
        }
        return user;
    }

    public UserProfileInformation getUserInfo(String nic) {
        User user = new User(nic);
        user.getUserInfo();

        Driver driver = new Driver(nic);
        driver.getDriverInfo();

        Vehicle vehicle = new Vehicle(nic);
        vehicle.getVehicleInfo();

        People people = new People(nic);
        people.getCivilInfo();

        return new UserProfileInformation(user, driver, vehicle, people);
    }
}