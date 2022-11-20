package com.cops.ntsf.service;

import com.cops.ntsf.constants.UserType;
import com.cops.ntsf.model.Driver;
import org.json.JSONObject;

public class AuthService {
    public String getUserIdFromLoginId(UserType userType, String loginId){
        switch (userType){
            case DRIVER:
                Driver driver = new Driver();
                driver.setLicenceNo(loginId);
                driver.getDriverFromLicenseNo();
                return driver.getUserId();
            default:
                throw new RuntimeException();
        }
    }

    public String verifyLogin(String loginId, String password, UserType userType){
        String userId = this.getUserIdFromLoginId(userType, loginId);

        JSONObject loginResponse = new JSONObject();
        loginResponse.put("loggedIn", true);
        loginResponse.put("userId", userId);

        return loginResponse.toString();
    }
}
