package com.cops.ntsf.model;

import java.time.LocalDateTime;

public class Fine {
    private int fineNo;

    private int offenceNo;

    private String userId;

    private String spotDescription;

    private LocalDateTime dateTime;

    private LocalDateTime dueDateTime;

    private int paymentStatus;

    private String policeId;

    private String policeStation;

    public Fine() {
    }

    public int getFineNo() {
        return fineNo;
    }

    public int getOffenceNo() {
        return offenceNo;
    }

    public String getUserId() {
        return userId;
    }

    public String getSpotDescription() {
        return spotDescription;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public LocalDateTime getDueDateTime() {
        return dueDateTime;
    }

    public int getPaymentStatus() {
        return paymentStatus;
    }

    public String getPoliceId() {
        return policeId;
    }

    public String getPoliceStation() {
        return policeStation;
    }

    public void setFineNo(int fineNo) {
        this.fineNo = fineNo;
    }

    public void setOffenceNo(int offenceNo) {
        this.offenceNo = offenceNo;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setSpotDescription(String spotDescription) {
        this.spotDescription = spotDescription;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDueDateTime(LocalDateTime dueDateTime) {
        this.dueDateTime = dueDateTime;
    }

    public void setPaymentStatus(int paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId;
    }

    public void setPoliceStation(String policeStation) {
        this.policeStation = policeStation;
    }



}