package com.cops.ntsf.service;

import com.cops.ntsf.constants.PoliceRank;
import com.cops.ntsf.model.Policeman;
import org.json.JSONObject;

public class PolicemanService {

//    public Policeman getPolicemanSignedUp(String police_id,
//                                          String name,
//                                          String nic,
//                                          String email,
//                                          String mobileNo,
//                                          String password) {
//        Policeman policeman = new Policeman(police_id, name, nic, email, mobileNo, password);
//        policeman.setPolicemanInfo();
//
////        if (police_id != null) {
////            Auth auth = new Auth(police_id, password);
////            auth.setPoliceAuthInfo();
////        }
//        return policeman;
//    }
//
//    public Policeman updatePolicemanRankStation(String police_id,
//                                                PoliceRank police_rank,
//                                                String policeStation) {
//        Policeman policeman = new Policeman(police_id, police_rank,policeStation);
//        policeman.updatePolicemanRankStation();
//
//        return policeman;
//    }
//
//    public Policeman getPolicemanInfo(PoliceRank police_rank, String police_id, String password) {
//        Policeman policeman = new Policeman(police_id, police_rank, password);
//        policeman.getPolicemanInfo();
//
//        return policeman;
//    }

//    public String verifyLogin(String nic, String input_password) {
//        String userId = this.getUserIdFromNic(nic);
//
//        Auth auth = new Auth();
//        auth.setUserId(userId);
//        auth.getAuthFromUserId();
//
//        JSONObject loginResponse = new JSONObject();
//        loginResponse.put("loggedIn", verifyPassword(auth.getPassword(), input_password));
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

    public String verifyLogin(String police_id, String input_password) {
//        String userId = this.getUserIdFromNic(nic);

        Policeman policeman = new Policeman();
        policeman.setPoliceId(police_id);
        policeman.getPolicemanFromPoliceId();

//        Auth auth = new Auth();
//        auth.setUserId(userId);
//        auth.getAuthFromUserId();

        JSONObject loginResponse = new JSONObject();
        loginResponse.put("loggedIn", verifyPassword(policeman.getPassword(), input_password));
        loginResponse.put("police_id", police_id);
        boolean police_rank = false;
        loginResponse.put("loginAuthorizingBackend", police_rank);

        return loginResponse.toString();
    }

    public Boolean verifyPassword(String password, String input_password) {
        // System.out.println(password + input_password);
        return password.equals(input_password);
    }

//    public Policeman getPolicemanInfo(PoliceRank policeRank, String policeId, String password) {
//
//    }
}