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
//            switch (userType) {
//                case DRIVER:
//                    Driver driver = new Driver(nic, loginId);
//                    driver.setDriverInfo();
//                    return user;
//                case PEDESTRIAN:
//                    Pedestrian pedestrian = new Pedestrian(nic, loginId);
//                    pedestrian.setPedestrianInfo();
//                    return pedestrian;
//                case VEHICLE:
//                    Vehicle vehicle = new Vehicle(nic, loginId);
//                    vehicle.setVehicleInfo();
//                    return user;
//                default:
//                    throw new RuntimeException();
//            }
        }
        return user;
    }

//    public User getUserInfo(String userId) {
//        User user = new User(userId);
//        user.getUserInfo();
//
//        String nic = user.getNic();
//
//        if (nic != null) {
////            switch (userType) {
////                case DRIVER:
//            Driver driver = new Driver(nic);
//            driver.getDriverInfo();
//            Vehicle vehicle = new Vehicle(nic);
//            vehicle.getVehicleInfo();
//            People people = new People(nic);
//            people.getCivilInfo();
////            return user;
//////                default:
//////                    throw new RuntimeException();
//////            }
////        }
//        }
//        return user;
//    }

    public User getUserInfo(String nic) {
        User user = new User(nic);
        user.getUserInfo();

//        String nic = user.getNic();

        if (nic != null) {
//            switch (userType) {
//                case DRIVER:
            Driver driver = new Driver(nic);
            driver.getDriverInfo();
            Vehicle vehicle = new Vehicle(nic);
            vehicle.getVehicleInfo();
            People people = new People(nic);
            people.getCivilInfo();
//            return user;
////                default:
////                    throw new RuntimeException();
////            }
//        }
        }
        return user;
    }
}