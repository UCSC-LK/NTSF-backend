package com.cops.ntsf.service;

import com.cops.ntsf.constants.UserType;
import com.cops.ntsf.model.Auth;
import com.cops.ntsf.model.Driver;
import com.cops.ntsf.model.User;
import com.cops.ntsf.model.Vehicle;

public class UserService {
    public User getUserSignedUp(UserType userType,
                                String name,
                                String address,
                                String nic,
                                String email,
                                String mobileNo,
                                String password,
                                String loginId) {
        User user = new User(name, address, nic, email, mobileNo, userType);
        user.setUserInfo();

        String userId = user.getUserId();

        if (userId != null) {
            Auth auth = new Auth(userId, password);
            auth.setAuthInfo();
            switch (userType) {
                case DRIVER:
                    Driver driver = new Driver(userId, loginId);
                    driver.setDriverInfo();
                    return user;
                case VEHICLE:
                    Vehicle vehicle = new Vehicle(userId, loginId);
                    vehicle.setVehicleInfo();
                    return user;
                default:
                    throw new RuntimeException();
            }
        }
        return user;
    }

//    public User getUserInfo(String name,
//                            String address,
//                            String nic,
//                            String email,
//                            String mobileNo,
//                            String loginId,
//                            String userId,
//                            String userType) {
//        User user = new User(name, address, nic, email, mobileNo);
//        user.getUserInfo();
//
//        userId = user.getUserId();
//
//        if (userId != null) {
//            switch (userType) {
//                case DRIVER:
//                    Driver driver = new Driver(userId, loginId);
//                    driver.getDriverInfo();
//                    return user;
//                default:
//                    throw new RuntimeException();
//            }
//        }
//        return user;
//    }
}
