package com.cops.ntsf.model;

import com.cops.ntsf.constants.UserType;
import com.cops.ntsf.dao.UserDAO;

public class User {
    private String userId;
    private String name;
//    private String address;
    private String nic;
    private String email;
    private String mobileNo;
    private UserType userType;
    // private Blob profilePicture;

    public User(String nic, String email) {
        this.nic = nic;
        this.email = email;
    }

    public User(String userId, UserType userType) {
        this.userId = userId;
        this.userType = userType;
    }

    public User(String userId) {
        this.userId = userId;
    }

    public User() {
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

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = UserType.valueOf(userType);
    }

    public void setUserInfo() {
        UserDAO userDAO = new UserDAO();
        userDAO.insertUserInfo(this);
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public void getUserInfo() {
        UserDAO userDAO = new UserDAO();
        userDAO.fetchUserInfo(this, false);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getAddress() {
//        return address;
//    }

//    public void setAddress(String address) {
//        this.address = address;
//    }

//    public void updateUserInfo() {
//        UserDAO userDAO = new UserDAO();
//        userDAO.updateUserInfo(this);
//    }

    public void getUserFromNic() {
        UserDAO userDAO = new UserDAO();
        userDAO.getUserFromNic(this);
    }
}