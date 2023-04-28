package com.cops.ntsf.model;

import com.cops.ntsf.dao.AuthDAO;

public class Auth {
    private String userId;
    private String hashedPassword;

    public Auth() {

    }

    public Auth(String userId, String hashedPassword) {
        this.userId = userId;
        this.hashedPassword = hashedPassword;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return hashedPassword;
    }

    public void setPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void getAuthFromUserId() {
        AuthDAO authDAO = new AuthDAO();
        authDAO.getAuthFromUserId(this);
    }

    public void setAuthInfo() {
        AuthDAO authDAO = new AuthDAO();
        authDAO.insertAuth(this);
    }
}
