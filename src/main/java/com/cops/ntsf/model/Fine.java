package com.cops.ntsf.model;

import com.cops.ntsf.constants.OffenceType;
import com.cops.ntsf.constants.PaymentStatus;
import com.cops.ntsf.dao.FineDAO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class Fine {
    private final String nic;
    private OffenceType offenceType;
    private Integer ticketNo;
    private Integer fineNo;
    private Date date;
    private Date dueDate;
    private String amount;
    private PaymentStatus paymentStatus;
    private Integer pointWeight;
    private Integer userId;

    public Fine(String nic) {
        this.nic = nic;
    }

    public Fine(String nic, String offenceType) {
        this.nic = nic;
        this.offenceType = OffenceType.valueOf(offenceType);
    }

    public Fine(String nic, Integer ticketNo, Integer fineNo, Date date, Date dueDate, PaymentStatus paymentStatus, OffenceType offenceType, String amount, Integer pointWeight) {
        this.nic = nic;
        this.ticketNo = ticketNo;
        this.fineNo = fineNo;
        this.date = date;
        this.dueDate = dueDate;
        this.paymentStatus = paymentStatus;
        this.offenceType = offenceType;
        this.amount = amount;
        this.pointWeight = pointWeight;
    }

    public Fine(String nic, OffenceType offenceType) {
        this.nic = nic;
        this.offenceType = offenceType;
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

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public ArrayList<Fine> getUserFinesInfo() throws SQLException {
        FineDAO fineDAO = new FineDAO();
        return fineDAO.fetchUserFinesInfo(this);
    }

    public void setFineInfo() {
        FineDAO fineDAO = new FineDAO();
        fineDAO.insertFineInfo(this);
    }

    public OffenceType getOffenceType() {
        return offenceType;
    }

    public void setOffenceType(OffenceType offenceType) {
        this.offenceType = offenceType;
    }

    public String getAmount() {
        return amount;
    }

    public Integer getPointWeight() {
        return pointWeight;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getNic() {
        return nic;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }
}
