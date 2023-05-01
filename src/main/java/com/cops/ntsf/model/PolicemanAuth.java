package com.cops.ntsf.model;

public class PolicemanAuth {
    private String userId;
    private String password;
    private String otp;
    private String login_flag;

    public PolicemanAuth(String userId, String password, String otp, String login_flag) {
        this.userId = userId;
        this.password = password;
        this.otp = otp;
        this.login_flag = login_flag;
    }

    public PolicemanAuth(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getOtp() {
        return otp;
    }

    public String getLogin_flag() {
        return login_flag;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public void setLogin_flag(String login_flag) {
        this.login_flag = login_flag;
    }

}
