package com.cops.ntsf.model;

import com.cops.ntsf.constants.UserType;
import com.cops.ntsf.dao.UserDAO;
import java.sql.Blob;

public class User {
    private String userId;
    private String nic;
    private String email;
    private String mobileNo;
    private UserType userType;
    private String firstName;
    private String lastName;
    private Blob profilePicture;

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

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Blob getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Blob profilePicture) {
        this.profilePicture = profilePicture;
    }

    public User(){ }

    public User(String nic, String email, String mobileNo, UserType userType){
        this.nic = nic;
        this.email = email;
        this.userType = userType;
    }

    public void setUserInfo(){
        UserDAO userDAO = new UserDAO();
        userDAO.insertUserInfo(this);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMobileNo() {
        return mobileNo;
    }
}