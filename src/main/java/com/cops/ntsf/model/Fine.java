package com.cops.ntsf.model;

import com.cops.ntsf.dao.FineDAO;
import org.json.JSONArray;

import java.time.LocalDateTime;

public class Fine {
    private int fineNo;
    private String fineType;
    private String offenceNo;

    private String nic;

    private String licenseNo;

    private String vehicleNo;

    private String drivenVehicleNo;

    private String spotDescription;

    private LocalDateTime imposedDateTime;

    private LocalDateTime dueDateTime;

    private String policeId;

    private String policeStation;

    private String paymentStatus;

    public Fine() {
    }

    public Fine(String fineType, String offenceNo, String nic, String licenseNo, String vehicleNo, String drivenVehicleNo, String spotDescription, LocalDateTime imposedDateTime, LocalDateTime dueDateTime, String policeId, String policeStation) {
        this.fineType = fineType;
        this.offenceNo = offenceNo;
        this.nic = nic;
        this.licenseNo = licenseNo;
        this.vehicleNo = vehicleNo;
        this.drivenVehicleNo = drivenVehicleNo;
        this.spotDescription = spotDescription;
        this.imposedDateTime = imposedDateTime;
        this.dueDateTime = dueDateTime;
        this.policeId = policeId;
        this.policeStation = policeStation;
        this.paymentStatus = "unpaid";
    }

    public int getFineNo() {
        return fineNo;
    }

    public String getFineType() {
        return fineType;
    }

    public String getOffenceNo() {
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

    public String getDrivenVehicleNo() {
        return drivenVehicleNo;
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

    public void setFineType(String fineType) {
        this.fineType = fineType;
    }
    public void setOffenceNo(String offenceNo) {
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

    public void setDrivenVehicleNo(String drivenVehicleNo) {
        this.drivenVehicleNo = drivenVehicleNo;
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

    public void createFine() {
        FineDAO fineDAO = new FineDAO();
        fineDAO.createFine(this);
    }

    public JSONArray getFineListAsOIC() {
        FineDAO fineDAO = new FineDAO();
        JSONArray fineDetails = fineDAO.viewFineDetailsAsOIC();
        return fineDetails;

    }
}
