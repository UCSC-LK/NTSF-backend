package com.cops.ntsf.model;

import com.cops.ntsf.dao.VehicleDAO;

public class Vehicle extends User {

    private String vehicleNo;

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
}
