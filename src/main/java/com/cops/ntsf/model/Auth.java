package com.cops.ntsf.model;

import com.cops.ntsf.dao.AuthDAO;

public class Auth {
    private String userId;
    private String password;

    /**
     * Used for login process
     *
     * @param userId        User Id in Auth
     * @param inputPassword User input password
     */
    public Auth(String userId, String inputPassword) {
        this.userId = userId;
        this.password = inputPassword;
    }

    /**
     * Used for sign up process
     *
     * @param userId   User Id in Auth
     * @param password Input password
     */
    public Auth(Integer userId, String password) {
        this.userId = String.valueOf(userId);
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

    public void setAuthInfo() {
        AuthDAO authDAO = new AuthDAO();
        authDAO.insertAuth(this);
    }
}
