package com.cops.ntsf.service;

import com.cops.ntsf.constants.PoliceRank;
import com.cops.ntsf.model.Policeman;
import org.json.JSONObject;

public class PolicemanService {

    public Policeman getPolicemanSignedUp(String policeId,
                                          String name,
                                          String nic,
                                          String email,
                                          String mobileNo,
                                          String password) {
        Policeman policeman = new Policeman(policeId, name, nic, email, mobileNo, password);
        policeman.setPolicemanInfo();

//        if (policeId != null) {
//            Auth auth = new Auth(policeId, password);
//            auth.setPoliceAuthInfo();
//        }
        return policeman;
    }

    public Policeman updatePolicemanRankStation(String policeId,
                                                PoliceRank policeRank,
                                                String policeStation) {
        Policeman policeman = new Policeman(policeId, policeRank,policeStation);
        policeman.updatePolicemanRankStation();

        return policeman;
    }

    public Policeman getPolicemanInfo(PoliceRank policeRank, String policeId, String password) {
        Policeman policeman = new Policeman(policeId, policeRank, password);
        policeman.getPolicemanInfo();

        return policeman;
    }

//    public String verifyLogin(String nic, String inputPassword) {
//        String userId = this.getUserIdFromNic(nic);
//
//        Auth auth = new Auth();
//        auth.setUserId(userId);
//        auth.getAuthFromUserId();
//
//        JSONObject loginResponse = new JSONObject();
//        loginResponse.put("loggedIn", verifyPassword(auth.getPassword(), inputPassword));
//        loginResponse.put("userId", userId);
//
//        return loginResponse.toString();
//    }

//    public String getUserIdFromLoginId(UserType userType, String loginId) {
//        switch (userType) {
//            case DRIVER:
//                Driver driver = new Driver();
//                driver.setLicenceNo(loginId);
//                driver.getDriverFromLicenseNo();
//                return driver.getUserId();
//            case PEDESTRIAN:
//                Pedestrian pedestrian = new Pedestrian();
//                pedestrian.setNic(loginId);
//                pedestrian.getPedestrianFromNic();
//                return pedestrian.getUserId();
//            case VEHICLE:
//                Vehicle vehicle = new Vehicle();
//                vehicle.setVehicleNo(loginId);
//                vehicle.getVehicleFromVehicleNo();
//                return vehicle.getUserId();
//            default:
//                throw new RuntimeException();
//        }
//    }

    public String verifyLogin(String policeId, String inputPassword, PoliceRank policeRank) {
//        String userId = this.getUserIdFromNic(nic);

        Policeman policeman = new Policeman();
        policeman.setPoliceId(policeId);
        policeman.getPolicemanFromPoliceId();

//        Auth auth = new Auth();
//        auth.setUserId(userId);
//        auth.getAuthFromUserId();

        JSONObject loginResponse = new JSONObject();
        loginResponse.put("loggedIn", verifyPassword(policeman.getPassword(), inputPassword));
        loginResponse.put("policeId", policeId);
        loginResponse.put("loginAuthorizingBackend", policeRank);

        return loginResponse.toString();
    }

    public Boolean verifyPassword(String password, String inputPassword) {
        // System.out.println(password + inputPassword);
        return password.equals(inputPassword);
    }
}