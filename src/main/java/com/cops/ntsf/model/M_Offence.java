package com.cops.ntsf.model;

import com.cops.ntsf.dao.M_OffenceDAO;

public class M_Offence {



    public M_Offence() {

    }



    public void setOffenceNo(String offenceNo) {
        this.offenceNo = offenceNo;
    }

    public void setOffenceType(String offenceType) {
        this.offenceType = offenceType;
    }

    private String offenceNo ;

    private  String offenceType;

    private String pointWeight;

    private String description;

    private String amount;

    public M_Offence(String offenceNo, String offenceType, String pointWeight, String description, String amount) {
        this.offenceType=offenceType;
        this.offenceNo=offenceNo;
        this.pointWeight=pointWeight;
        this.description=description;
        this.amount=amount;

    }

    public String getOffenceNo() {
        return offenceNo;
    }



//    public static void setOffenceNo(int offenceNo) {
//        this.offenceNo = offenceNo;
//    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPointWeight() {
        return pointWeight;
    }

    public void setPointWeight(String pointWeight) {
        this.pointWeight = pointWeight;
    }

    public void setOffenceInfo() {
        M_OffenceDAO offenceDAO=new M_OffenceDAO();
        offenceDAO.setOffenceInfo(this);
    }

    public void updateOffenceInfo() {
        M_OffenceDAO offenceDAO=new M_OffenceDAO();
        offenceDAO.updateOffenceInfo(this);
    }

    public String getOffenceType() {
        return offenceType;
    }


}