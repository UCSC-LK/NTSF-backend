package com.cops.ntsf.model;

import com.cops.ntsf.dao.PeopleDAO;

import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Base64;

public class People extends User {

    private String name;
    private String address;
    private String gender;
    private String birthPlace;
    private Date issueDate;
    private String job;
    private Date dob;
    private Blob profilePicture;

    public People() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public People(String nic) {
        super(nic);
    }

    public void getCivilInfo() {
        PeopleDAO peopleDAO = new PeopleDAO();
        peopleDAO.fetchCivilInfo(this);
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getDob() {
        return dob;
    }

    public void setProfilePicture(Blob profilePicture) {
        this.profilePicture = profilePicture;
    }

    /**
     * Convert profile picture to base64 encoded string
     */
    public String getProfilePicture() throws SQLException {
//        return new String(profilePicture.getBytes((long) 1, (int) profilePicture.length()));
        return new String(Base64.getEncoder().encode(profilePicture.getBytes(1, (int) profilePicture.length())));
    }
}

