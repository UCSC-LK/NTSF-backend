package com.cops.ntsf.model;

import com.cops.ntsf.constants.FineType;
import com.cops.ntsf.constants.PaymentStatus;
import com.cops.ntsf.dao.FineDAO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class Fine {
    private FineType fineType;
    private String userId;
    private String ticketNo;
    private String fineNo;
    private Date date;
    private Date dueDate;
    private String fineAmount;
    private PaymentStatus paymentStatus;

    public FineType getFineType() {
        return fineType;
    }

    public void setFineType(FineType fineType) {
        this.fineType = fineType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public String getFineNo() {
        return fineNo;
    }

    public void setFineNo(String fineNo) {
        this.fineNo = fineNo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(String fineAmount) {
        this.fineAmount = fineAmount;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Fine(String userId) {
        this.userId = userId;
    }

    public Fine(String userId, FineType fineType) {
        this.userId = userId;
        this.fineType = fineType;
    }

    public Fine(String ticketNo, String fineNo, Date date, Date dueDate, String fineAmount, PaymentStatus paymentStatus, FineType fineType) {
        this.ticketNo = ticketNo;
        this.fineNo = fineNo;
        this.date = date;
        this.dueDate = dueDate;
        this.fineAmount = fineAmount;
        this.paymentStatus = paymentStatus;
        this.fineType = fineType;

    }

    public ArrayList<Fine> getUserFinesInfo() throws SQLException {
        FineDAO fineDAO = new FineDAO();
        return fineDAO.fetchUserFinesInfo(this);
    }

    public void setFineInfo() {
        FineDAO fineDAO = new FineDAO();
        fineDAO.insertFineInfo(this);
    }
}
