package com.cops.ntsf.model;

import com.cops.ntsf.dao.FineDAO;

import java.text.ParseException;

public class Fine {
    private String userId;
    private String fineType;
    private Integer fineNo;
    private Integer fineAmount;
    private String paymentStatus;

    private String policeId;

    public Fine(Integer fineNo, String userId, String id, Integer fineAmount, String paymentStatus, String policeId, String fineType) throws ParseException {
        this.fineNo = fineNo;
        this.fineType = fineType;
        this.userId = userId;
        this.fineAmount = fineAmount;
        this.paymentStatus = paymentStatus;
        this.policeId = policeId;
    }

//    public ArrayList<Fine> getUserFinesInfo() throws SQLException, ParseException {
//        FineDAO fineDAO = new FineDAO();
//        return fineDAO.fetchUserFinesInfo(this);
//    }

    public String getUserId() {
        return userId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId;
    }

    public String getPoliceId() {
        return policeId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getFineNo() {
        return fineNo;
    }

    public void setFineNo(Integer fineNo) {
        this.fineNo = fineNo;
    }

//    public Date getFineDate() {
//        return fineDate;
//    }



//    public void setFineDate(Date fineDate) {
//        this.fineDate = fineDate;
//    }
//
//    public java.sql.Date getDueDate() {
//        return dueDate;
//    }
//
//    public void setDueDate(Date dueDate) {
//        this.dueDate = String.valueOf(dueDate);
//    }

    public int getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(Integer fineAmount) {
        this.fineAmount = fineAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

//    public Fine getUserFinesInfo(String userId) throws SQLException, ParseException {
//        Fine fine = null;
//        try {
//            fine = new Fine(fineNo, userId, userId, fineAmount, paymentStatus, policeId);
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
//        fine.getUserFinesInfo();
//
//        return fine;
//    }

    public String getFineType() {return fineType;}

    public String getPoliceID() {return policeId;}

//    public Time getFineTime() {return fineTime;}

    public void setFineInfo() {
        FineDAO fineDAO=new FineDAO();
        fineDAO.setOffenceInfo(this);
    }

}
