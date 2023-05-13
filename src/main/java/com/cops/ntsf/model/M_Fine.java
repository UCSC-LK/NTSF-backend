package com.cops.ntsf.model;

//import com.cops.ntsf.dao.FineDAO;

import com.cops.ntsf.dao.M_FineDAO;

import java.time.LocalDateTime;

public class M_Fine {
    private String userId;
    private String licenceNo;
    private String name;
    private Integer ticketNo;
    private String fineNo;
    private String fineType;
    private String descripton;
    private String location;
    private  String policeId;

    private String nic;
    private String vehicleNo;

//    private LocalDateTime imposedDateTime;
//    private LocalDateTime dueDateTime;

    LocalDateTime imposedDateTime = LocalDateTime.now();
    LocalDateTime dueDateTime = imposedDateTime.plusDays(14);


    private Integer fineAmount;
    private String paymentStatus;



    public M_Fine(String licenceNo, String location, String fineNo, String description, String policeId, String fineType, String vehicleNo, LocalDateTime imposedDateTime, LocalDateTime dueDateTime) {
        this.licenceNo = licenceNo;
        this.location = location;
        this.fineNo = fineNo;
        this.descripton = description;
        this.policeId = policeId;
        this.fineType = fineType;
        this.vehicleNo = vehicleNo;
    }

    public String getLicenceNo() {
        return licenceNo;
    }

    public String getFineType() {
        return fineType;
    }

    public String getName(){
        return name;
    }



    public String getLocation() {
        return location;
    }

    public String getFineNo() {
        return fineNo;
    }

    public String getDescripton() {
        return descripton;
    }

    public String getPoliceId() {
        return policeId;
    }

    public String getVehicleNo(){
        return vehicleNo;
    }

    public LocalDateTime getImposedDateTime(){return imposedDateTime;}

    public LocalDateTime getDueDateTime(){return dueDateTime;}


    public Integer setFineInfo() {
        M_FineDAO fineDAO=new M_FineDAO();
        return fineDAO.setFineInfo(this);
    }

    public String getNic() {
        return nic;
    }


}