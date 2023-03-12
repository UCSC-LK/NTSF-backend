package com.cops.ntsf.model;

import java.time.LocalDateTime;

public class Fine {
    private int fineNo;
    private int offenceNo;
    private String nic;
    private String licenseNo;
    private String vehicleNo;

    private String spotDescription;

    private LocalDateTime imposedDateTime;

    private LocalDateTime dueDateTime;

    private String policeId;

    private String policeStation;

    private String paymentStatus;

    public Fine() {
    }

    //Constructor for pedestrian fine
    public Fine(String nic, String spotDescription, LocalDateTime imposedDateTime, LocalDateTime dueDateTime, String policeId, String policeStation) {
        this.nic = nic;
        this.spotDescription = spotDescription;
        this.imposedDateTime = imposedDateTime;
        this.dueDateTime = dueDateTime;
        this.policeId = policeId;
        this.policeStation = policeStation;
    }

    //Constructor for driver fine

    public Fine(String licenseNo, String spotDescription, LocalDateTime imposedDateTime, LocalDateTime dueDateTime, String policeId, String policeStation) {
        this.licenseNo = licenseNo;
        this.spotDescription = spotDescription;
        this.imposedDateTime = imposedDateTime;
        this.dueDateTime = dueDateTime;
        this.policeId = policeId;
        this.policeStation = policeStation;
    }

    //Constructor for vehicle fine
    public Fine( String vehicleNo, String spotDescription, LocalDateTime imposedDateTime, LocalDateTime dueDateTime, String policeId, String policeStation) {
        this.vehicleNo = vehicleNo;
        this.spotDescription = spotDescription;
        this.imposedDateTime = imposedDateTime;
        this.dueDateTime = dueDateTime;
        this.policeId = policeId;
        this.policeStation = policeStation;
    }

    public int getFineNo() {
        return fineNo;
    }

    public int getOffenceNo() {
        return offenceNo;
    }

    public String getNic() {
        return nic;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public String getSpotDescription() {
        return spotDescription;
    }

    public LocalDateTime getImposedDateTime() {
        return imposedDateTime;
    }

    public LocalDateTime getDueDateTime() {
        return dueDateTime;
    }

    public String getPoliceId() {
        return policeId;
    }

    public String getPoliceStation() {
        return policeStation;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setFineNo(int fineNo) {
        this.fineNo = fineNo;
    }

    public void setOffenceNo(int offenceNo) {
        this.offenceNo = offenceNo;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public void setSpotDescription(String spotDescription) {
        this.spotDescription = spotDescription;
    }

    public void setImposedDateTime(LocalDateTime imposedDateTime) {
        this.imposedDateTime = imposedDateTime;
    }

    public void setDueDateTime(LocalDateTime dueDateTime) {
        this.dueDateTime = dueDateTime;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId;
    }


    public void setPoliceStation(String policeStation) {
        this.policeStation = policeStation;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

}
