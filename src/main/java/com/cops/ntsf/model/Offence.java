package com.cops.ntsf.model;

import com.cops.ntsf.dao.OffenceDAO;

public class Offence {

    private static Integer offenceNo ;

    private  String offenceType;

    private static Integer pointWeight;

    private static String description;

    private static Integer amount;

    public Offence(Integer offenceNo, String offenceType, Integer pointWeight, String description, Integer amount) {
        this.offenceType=offenceType;
        this.offenceNo=offenceNo;
        this.pointWeight=pointWeight;
        this.description=description;
        this.amount=amount;

    }

    public static int getOffenceNo() {
        return offenceNo;
    }

    public static void setOffenceNo(int offenceNo) {
        Offence.offenceNo = offenceNo;
    }

    public static String getDescription() {
        return description;
    }

    public static void setDescription(String description) {
        Offence.description = description;
    }

    public static Integer getAmount() {
        return amount;
    }

    public static void setAmount(int amount) {
        Offence.amount = amount;
    }

    public static int getPointWeight() {
        return pointWeight;
    }

    public static void setPointWeight(int pointWeight) {
        Offence.pointWeight = pointWeight;
    }

    public void setOffenceInfo() {
        OffenceDAO offenceDAO=new OffenceDAO();
        offenceDAO.setOffenceInfo(this);
    }

    public String getOffenceType() {
        return offenceType;
    }
}