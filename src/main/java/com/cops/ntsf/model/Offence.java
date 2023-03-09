package com.cops.ntsf.model;

import com.cops.ntsf.dao.OffenceDAO;

public class Offence {

    private Integer offenceNo;
    private String offenceType;
    private Integer pointWeight;
    private String description;
    private Integer amount;

    public Offence() {
        // Empty constructor
    }

    public Offence(Integer offenceNo, String offenceType, Integer pointWeight, String description, Integer amount) {
        this.offenceNo = offenceNo;
        this.offenceType = offenceType;
        this.pointWeight = pointWeight;
        this.description = description;
        this.amount = amount;
    }

    public Integer getOffenceNo() {
        return offenceNo;
    }

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

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setOffenceInfo() {
        OffenceDAO offenceDAO = new OffenceDAO();
        offenceDAO.setOffenceInfo(this);
    }

    public void updateOffenceInfo() {
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




//package com.cops.ntsf.model;
//
//import com.cops.ntsf.dao.OffenceDAO;
//
//public class Offence {
//
//    private static Integer offenceNo ;
//
//    public Offence() {
//
//    }
//
//    public static void setOffenceNo(Integer offenceNo) {
//        Offence.offenceNo = offenceNo;
//    }
//
//    public void setOffenceType(String offenceType) {
//        this.offenceType = offenceType;
//    }
//
//    private  String offenceType;
//
//    private static Integer pointWeight;
//
//    private static String description;
//
//    private static Integer amount;
//
//    public Offence(Integer offenceNo, String offenceType, Integer pointWeight, String description, Integer amount) {
//        this.offenceType=offenceType;
//        this.offenceNo=offenceNo;
//        this.pointWeight=pointWeight;
//        this.description=description;
//        this.amount=amount;
//
//    }
//
//    public static int getOffenceNo() {
//        return offenceNo;
//    }
//
//    public static void setOffenceNo(int offenceNo) {
//        Offence.offenceNo = offenceNo;
//    }
//
//    public static String getDescription() {
//        return description;
//    }
//
//    public static void setDescription(String description) {
//        Offence.description = description;
//    }
//
//    public static Integer getAmount() {
//        return amount;
//    }
//
//    public static void setAmount(int amount) {
//        Offence.amount = amount;
//    }
//
//    public static int getPointWeight() {
//        return pointWeight;
//    }
//
//    public static void setPointWeight(int pointWeight) {
//        Offence.pointWeight = pointWeight;
//    }
//
//    public void setOffenceInfo() {
//        OffenceDAO offenceDAO=new OffenceDAO();
//        offenceDAO.setOffenceInfo(this);
//    }
//
//    public void updateOffenceInfo() {
//        OffenceDAO offenceDAO=new OffenceDAO();
//        offenceDAO.updateOffenceInfo(this);
//    }
//
//    public String getOffenceType() {
//        return offenceType;
//    }
//
//
//}