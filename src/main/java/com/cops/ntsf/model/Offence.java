package com.cops.ntsf.model;

import com.cops.ntsf.dao.OffenceDAO;

public class Offence {

    //    private Integer offenceNo;
    private Integer offenceNo;

    public Offence() {

    }

    public void setOffenceNo(Integer offenceNo) {
        this.offenceNo = offenceNo;
    }

    public void setOffenceType(String offenceType) {
        this.offenceType = offenceType;
    }

    private String offenceType;

    private Integer pointWeight;

    private String description;

    private Integer amount;

    public Offence(Integer offenceNo, String offenceType, Integer pointWeight, String description, Integer amount) {
        this.offenceType = offenceType;
        this.offenceNo = offenceNo;
        this.pointWeight = pointWeight;
        this.description = description;
        this.amount = amount;

    }

    public int getOffenceNo() {
        return offenceNo;
    }

    public void setOffenceNo(int offenceNo) {
        this.offenceNo = offenceNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPointWeight() {
        return pointWeight;
    }

    public void setPointWeight(int pointWeight) {
        this.pointWeight = pointWeight;
    }

    public void setOffenceInfo() {
        OffenceDAO offenceDAO = new OffenceDAO();
        offenceDAO.setOffenceInfo(this);
    }

    public void updateOffenceInfo() {
        OffenceDAO offenceDAO = new OffenceDAO();
        OffenceDAO.updateOffenceInfo(this);
    }

    public String getOffenceType() {
        return offenceType;
    }


}