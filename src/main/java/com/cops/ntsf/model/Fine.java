package com.cops.ntsf.model;

import com.cops.ntsf.dao.FineDAO;
import org.json.JSONArray;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Fine {

    public Offence getOffence() {
        return offence;
    }

    public void setOffence(Offence offence) {
        this.offence = offence;
    }

    private Offence offence;

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
    private String footage_file;
    private String latitude;
    private String longitude;
    private String paymentStatus;

    public Fine() {

    }
    public Fine(String nic) {
        this.nic = nic;
    }

    // Removed fineType from constructor
    public Fine(String offenceNo, String nic, String licenseNo, String vehicleNo, String drivenVehicleNo, String spotDescription, LocalDateTime imposedDateTime, LocalDateTime dueDateTime, String policeId, String policeStation, String footage_file, String latitude, String longitude) {
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
        this.footage_file = footage_file;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /*
    @ Used in reducePoints function in pointService
    * */
    public Fine(int fineNo, String nic) {
        this.nic = nic;
        this.fineNo = fineNo;
    }

    public Fine(String nic, String fineType, Integer offenceNo) {
        this.nic = nic;
        this.fineType = fineType;
        this.offenceNo = String.valueOf(offenceNo);
    }

    public Integer getFineNo() {
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

    public String getFootage_file() {
        return footage_file;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
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

    public void setFootage_file(String footage_file) {
        this.footage_file = footage_file;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public boolean createFine() {
        FineDAO fineDAO = new FineDAO();
        boolean fineAddedResult = fineDAO.createFine(this);
        return fineAddedResult;

    }

    public JSONArray getFineListAsOIC(String policeStation, String offenceType, String paymentStatus) {
        FineDAO fineDAO = new FineDAO();
        JSONArray fineDetails = fineDAO.viewFineDetailsAsOIC(policeStation, offenceType, paymentStatus);
        return fineDetails;
    }

    public int getCurrentFineNoForFootage() {
        FineDAO fineDAO = new FineDAO();
        return fineDAO.getCurrentFineNoForFootage();
    }

    /**
     * @return ArrayList including fines
     * @throws SQLException Exception is thrown
     */
    public ArrayList<Fine> getUserFinesInfo() throws SQLException {
        FineDAO fineDAO = new FineDAO();
        return fineDAO.fetchUserFinesInfo(this);
    }

    /*
    @ Getting fine info by fine no
     */
    public Fine getFineByFineNo() {
        FineDAO fineDAO = new FineDAO();
        return fineDAO.fetchFineByFineNo(this);
    }

    public JSONArray getLocation(String fine_no) {
        FineDAO fineDAO = new FineDAO();
        return fineDAO.fetchLocation(fine_no);
    }

    public JSONArray getFineAsCourtSeargent(String policeStation, String offenceType, String paymentStatus) {
        FineDAO fineDAO = new FineDAO();
        return fineDAO.viewFineDetailsAsCourtSeargent(policeStation, offenceType, paymentStatus);
    }
}

