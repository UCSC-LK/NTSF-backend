package com.cops.ntsf.service;

import com.cops.ntsf.constants.UserType;
import com.cops.ntsf.model.Auth;
import com.cops.ntsf.model.Driver;
import com.cops.ntsf.model.Pedestrian;
import org.json.JSONObject;

public class AuthService {
    public String getUserIdFromLoginId(UserType userType, String loginId) {
        switch (userType) {
            case DRIVER:
                Driver driver = new Driver();
                driver.setLicenceNo(loginId);
                driver.getDriverFromLicenseNo();
                return driver.getUserId();
            case PEDESTRIAN:
                Pedestrian pedestrian = new Pedestrian();
                pedestrian.setNic(loginId);
                pedestrian.getPedestrianFromNic();
                return pedestrian.getUserId();
            default:
                throw new RuntimeException();
        }
    }

    public String verifyLogin(String loginId, String inputPassword, UserType userType) {
        String userId = this.getUserIdFromLoginId(userType, loginId);

        Auth auth = new Auth();
        auth.setUserId(userId);
        auth.getAuthFromUserId();

        JSONObject loginResponse = new JSONObject();
        loginResponse.put("loggedIn", verifyPassword(auth.getPassword(), inputPassword));
        loginResponse.put("userId", userId);

        return loginResponse.toString();
    }

    public Boolean verifyPassword(String password, String inputPassword) {
        // System.out.println(password + inputPassword);
        return password.equals(inputPassword);
    }

//    public Auth setPasswordForUserId(String userId, String password) {
//        Auth auth = new Auth(userId, password);
//        auth.setPassword();
//        return auth;
//    }
}