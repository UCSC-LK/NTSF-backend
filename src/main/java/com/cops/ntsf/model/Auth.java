package com.cops.ntsf.model;

import com.cops.ntsf.dao.AuthDAO;

public class Auth {
    private String userId;
    private String password;

    public Auth(){

    }
    public Auth(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void getAuthFromUserId() {
        AuthDAO authDAO = new AuthDAO();
        authDAO.getAuthFromUserId(this);
    }

    public void setPassword() {
        AuthDAO authDAO = new AuthDAO();
        authDAO.insertPassword(this);
    }
}
