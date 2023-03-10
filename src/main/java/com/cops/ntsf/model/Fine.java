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


}