package com.cops.ntsf.model;

import com.cops.ntsf.dao.FineDAO;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

public class Fine {
    private String userId;
    private Integer ticketNo;

    private String fineType;
    private Integer fineNo;
    private Date fineDate;
    private Date dueDate;
    private Integer fineAmount;
    private String paymentStatus;

    private Time fineTime;

    private String policeID;

    public Fine(String userId) {
        this.userId = userId;
    }

    public ArrayList<Fine> getUserFinesInfo() throws SQLException {
        FineDAO fineDAO = new FineDAO();
        return fineDAO.fetchUserFinesInfo(this);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(Integer ticketNo) {
        this.ticketNo = ticketNo;
    }

    public Integer getFineNo() {
        return fineNo;
    }

    public void setFineNo(Integer fineNo) {
        this.fineNo = fineNo;
    }

    public Date getFineDate() {
        return fineDate;
    }

    public void setFineDate(Date fineDate) {
        this.fineDate = fineDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

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

    public Fine getUserFinesInfo(String userId) throws SQLException {
        Fine fine = new Fine(userId);
        fine.getUserFinesInfo();

        return fine;
    }

    public String getFineType() {return fineType;}

    public String getPoliceID() {return policeID;}

    public Time getFineTime() {return fineTime;}
}
