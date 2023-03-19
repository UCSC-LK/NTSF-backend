package com.cops.ntsf.service;

import com.cops.ntsf.model.Auth;
import com.cops.ntsf.model.User;
import org.json.JSONObject;

public class AuthService {
    public String getUserIdFromNic(String nic) {
        User user = new User();
        user.setNic(nic);
        user.getUserInfo();
        return user.getUserId();
    }

    public String verifyLogin(String nic, String inputPassword) {
        String userId = this.getUserIdFromNic(nic);

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
}