package com.cops.ntsf.model;

import com.cops.ntsf.dao.VehicleDAO;

public class Vehicle extends User {

    private String vehicleNo;

    public Vehicle(String userId, String vehicleNo) {
        super(userId);
        this.vehicleNo = vehicleNo;
    }

    public void getVehicleFromVehicleNo() {
        VehicleDAO vehicleDAO = new VehicleDAO();
        vehicleDAO.getVehicleFromVehicleNo(this);
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public void setVehicleInfo() {
        VehicleDAO vehicleDAO = new VehicleDAO();
        vehicleDAO.insertVehicleInfo(this);
    }
}
