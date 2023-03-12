package com.cops.ntsf.model;

import com.cops.ntsf.dao.OffenceDAO;

public class Offence {

    //    private Integer offenceNo;
    private Integer offenceNo;
    private String offenceType;
    private Integer pointWeight;
    private String description;
    private Integer amount;

    public Offence() {

    }

    public Offence(String speeding, int i, int i1) {
    }

    public void setOffenceNo(Integer offenceNo) {
        this.offenceNo = offenceNo;
        // Empty constructor
    }

    public void setOffenceType(String offenceType) {
        this.offenceType = offenceType;
    }

    public Offence(Integer offenceNo, String offenceType, Integer pointWeight, String description, Integer amount) {
        this.offenceType = offenceType;
        this.offenceNo = offenceNo;
        this.pointWeight = pointWeight;
        this.description = description;
        this.amount = amount;
    }

    public Integer getOffenceNo() {
        return offenceNo;
    }

    public void setOffenceNo( offenceNo) {
        this.offenceNo = offenceNo;
    public void setOffenceNo(Integer offenceNo) {
        this.offenceNo = offenceNo;
    }

    public String getOffenceType() {
        return offenceType;
    }

    public void setOffenceType(String offenceType) {
        this.offenceType = offenceType;
    }

    public Integer getPointWeight() {
        return pointWeight;
    }

    public void setPointWeight(Integer pointWeight) {
        this.pointWeight = pointWeight;
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
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setOffenceInfo() {
        OffenceDAO offenceDAO = new OffenceDAO();
        offenceDAO.setOffenceInfo(this);
    }

    public void updateOffenceInfo() {
        OffenceDAO offenceDAO = new OffenceDAO();
        OffenceDAO.updateOffenceInfo(this);
        OffenceDAO offenceDAO = new OffenceDAO();
        offenceDAO.updateOffenceInfo(this);
    }

    @Override
    public String toString() {
        return "Offence{" +
                "offenceNo=" + offenceNo +
                ", offenceType='" + offenceType + '\'' +
                ", pointWeight=" + pointWeight +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                '}';
    }


}