package com.cops.ntsf.model;

import com.cops.ntsf.constants.UserType;
import com.cops.ntsf.dao.UserDAO;

public class User {
    private String userId;
    private String nic;
    private String email;
    private String mobileNo;
    private UserType userType;
    // private String firstName;
    // private String lastName;
    // private Blob profilePicture;

    public User() {
    }

    public User(String nic, String email, String mobileNo, UserType userType) {
        this.nic = nic;
        this.email = email;
        this.mobileNo = mobileNo;
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNic() {
        return nic;
    }

    public String getEmail() {
        return email;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserInfo() {
        UserDAO userDAO = new UserDAO();
        userDAO.insertUserInfo(this);
    }

    public String getMobileNo() {
        return mobileNo;
    }
}