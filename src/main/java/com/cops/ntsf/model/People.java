package com.cops.ntsf.model;

import com.cops.ntsf.dao.PeopleDAO;

import java.sql.Date;

public class People {

    private String name;
    private String address;
    private Date bod;
    private String gender;
    private String birthPlace;
    private String nic;
    private Date issueDate;
    private String job;

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

    public Date getBod() {
        return bod;
    }

    public void setBod(Date bod) {
        this.bod = bod;
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

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
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
    }

    public void getCivilInfo() {
        PeopleDAO peopleDAO = new PeopleDAO();
        peopleDAO.fetchCivilInfo(this);
    }
}
