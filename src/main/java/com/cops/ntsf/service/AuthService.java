package com.cops.ntsf.service;

import com.cops.ntsf.constants.AuthType;
import com.cops.ntsf.model.Auth;
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

        Auth auth = new Auth();
        auth.setUserId(userId);
        auth.getAuthFromUserId();

        boolean loggedIn = verifyPassword(auth.getPassword(), inputPassword);

        JSONObject loginResponse = new JSONObject();
        loginResponse.put("loggedIn", verifyPassword(auth.getPassword(), inputPassword));
        loginResponse.put("userId", userId);

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