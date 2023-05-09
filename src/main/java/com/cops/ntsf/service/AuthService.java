package com.cops.ntsf.service;

import com.cops.ntsf.constants.AuthType;
import com.cops.ntsf.model.Auth;
import com.cops.ntsf.model.People;
import com.cops.ntsf.model.User;
import com.cops.ntsf.util.JwtUtils;
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

        Auth auth = new Auth(userId, inputPassword);
        auth.setUserId(userId);
        auth.getAuthFromUserId();

        boolean loggedIn = verifyPassword(auth.getPassword(), inputPassword);

        // Get basic information from people table
        People people = new People(nic);
        people.getCivilInfo();

        // Create new JSON object to people
        JSONObject peopleJson = new JSONObject(people);

        JSONObject loginResponse = new JSONObject();
        loginResponse.put("loggedIn", verifyPassword(auth.getPassword(), inputPassword));
        loginResponse.put("userId", userId);
        loginResponse.put("nic", nic);
        loginResponse.put("people", peopleJson);

        if (loggedIn) {
            JwtUtils jwtUtils = new JwtUtils(AuthType.USER, null);
            loginResponse.put("jwt", jwtUtils.generateJwt());
        }

        return loginResponse.toString();
    }

    public Boolean verifyPassword(String password, String inputPassword) {
        if (password == null || inputPassword == null)
            return false;
        return password.equals(inputPassword);
    }
}