package com.cops.ntsf.model;

//import com.cops.ntsf.dao.FineDAO;

import com.cops.ntsf.dao.M_FineVehicleDAO;

public class M_FineVehicle {


    private String licenceNo;

    private String fineNo;
    private String fineType;
    private String descripton;

    private String location;

    private  String policeId;

    private String nic;

    private String vehicleNo;




    public M_FineVehicle(String nic, String location, String fineNo, String description, String policeId, String fineType, String vehicleNo) {
        this.nic = nic;
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

    public String getNic() {
        return nic;
    }

    public String getFineType() {
        return fineType;
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
    public Integer setFineInfo() {
        M_FineVehicleDAO fineVehicleDAO=new M_FineVehicleDAO();
        return fineVehicleDAO.setFineInfo(this);
    }

}