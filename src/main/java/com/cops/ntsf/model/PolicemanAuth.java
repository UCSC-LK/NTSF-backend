package com.cops.ntsf.model;

import com.cops.ntsf.dao.IgpDAO;
import com.cops.ntsf.dao.PolicemanAuthDAO;

public class PolicemanAuth {
    private String police_id;
    private String password;
    private String otp;
    private String login_flag;

    public PolicemanAuth(String police_id, String password, String otp, String login_flag) {
        this.police_id = police_id;
        this.password = password;
        this.otp = otp;
        this.login_flag = login_flag;
    }

    public PolicemanAuth(String police_id, String otp) {
        this.police_id = police_id;
        this.otp = otp;
    }

    public PolicemanAuth() {

    }

    public String getPolice_id() {
        return police_id;
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

    public void setPolice_id(String police_id) {
        this.police_id = police_id;
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

    public boolean policemanAuthAdded() {
        IgpDAO igpDAO = new IgpDAO();
        boolean policemanAuthAddedResult = igpDAO.createPolicemanAuth(this);
        System.out.println("Hi from policemanAuthAdded method in PolicemanAuth.java");
        System.out.println("PolicemanAuthAddedResult: " + policemanAuthAddedResult);
        return policemanAuthAddedResult;
    }

    public boolean checkFirstLoginOrNot(String police_id) {
        PolicemanAuthDAO policemanAuthDAO = new PolicemanAuthDAO();
        System.out.println("Hi from checkFirstLoginOrNot method in PolicemanAuth.java");
        boolean checkFirstLoginOrNotResult = policemanAuthDAO.FirstLoginOrNotCheck(police_id);
        System.out.println("checkFirstLoginOrNotResult: " + checkFirstLoginOrNotResult);
        return checkFirstLoginOrNotResult;
    }

    public boolean changeFirstTimePassword(String police_id, String hashedPassword) {
        PolicemanAuthDAO policemanAuthDAO = new PolicemanAuthDAO();
        System.out.println("Hi from changeFirstTimePassword method in PolicemanAuth.java");
        boolean changeFirstTimePasswordResult = policemanAuthDAO.changeFirstTimePassword(police_id, hashedPassword);
        System.out.println("changeFirstTimePasswordResult: " + changeFirstTimePasswordResult);
        return changeFirstTimePasswordResult;
    }
}
