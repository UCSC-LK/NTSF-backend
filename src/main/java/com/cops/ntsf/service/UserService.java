package com.cops.ntsf.service;

import com.cops.ntsf.constants.UserType;
import com.cops.ntsf.model.User;

public class UserService {
    public User getUserSignedUp(UserType userType, String nic, String email, String mobileNo){
        switch (userType){
            case DRIVER:
                User user = new User(nic, email, mobileNo, userType);
                user.setUserInfo();
//                String userId = user.
                return user;
            default:
                throw new RuntimeException();
        }
    }
}
