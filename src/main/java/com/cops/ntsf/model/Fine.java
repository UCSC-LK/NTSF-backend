package com.cops.ntsf.model;

import com.cops.ntsf.dao.FineDAO;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;

public class Fine {
    private String userId;
    private Integer ticketNo;
    private Integer fineNo;
    private Date date;
    private Date dueDate;
    private BigDecimal fineAmount;
    private String paymentStatus;

    public Fine(String userId) {

    }

    public void getUserFinesInfo() throws SQLException {
        FineDAO fineDAO = new FineDAO();
        fineDAO.fetchUserFinesInfo(this);
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

    public BigDecimal getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(BigDecimal fineAmount) {
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
}
