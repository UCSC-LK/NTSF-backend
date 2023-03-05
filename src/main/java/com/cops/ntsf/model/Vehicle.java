package com.cops.ntsf.model;

import com.cops.ntsf.dao.VehicleDAO;

public class Vehicle extends User {

    private String vehicleNo;
    private String registerNo;
    private String chassisNo;
    private String currentOwnerName;
    private String currentOwnerAddress;
    private String currentOwnerId;
    private String engineNo;
    private String vehicleClass;
    private String stateWhenRegister;
    private String color;
    private Integer engineCapacity;
    private String fuelType;
    private Integer yearOfManufacture;
    private String previousOwners;

    public String getRegisterNo() {
        return registerNo;
    }

    public void setRegisterNo(String registerNo) {
        this.registerNo = registerNo;
    }

    public String getChassisNo() {
        return chassisNo;
    }

    public void setChassisNo(String chassisNo) {
        this.chassisNo = chassisNo;
    }

    public String getCurrentOwnerName() {
        return currentOwnerName;
    }

    public void setCurrentOwnerName(String currentOwnerName) {
        this.currentOwnerName = currentOwnerName;
    }

    public String getCurrentOwnerAddress() {
        return currentOwnerAddress;
    }

    public void setCurrentOwnerAddress(String currentOwnerAddress) {
        this.currentOwnerAddress = currentOwnerAddress;
    }

    public String getCurrentOwnerId() {
        return currentOwnerId;
    }

    public void setCurrentOwnerId(String currentOwnerId) {
        this.currentOwnerId = currentOwnerId;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public String getVehicleClass() {
        return vehicleClass;
    }

    public void setVehicleClass(String vehicleClass) {
        this.vehicleClass = vehicleClass;
    }

    public String getStateWhenRegister() {
        return stateWhenRegister;
    }

    public void setStateWhenRegister(String stateWhenRegister) {
        this.stateWhenRegister = stateWhenRegister;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(Integer engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Integer getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(Integer yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public String getPreviousOwners() {
        return previousOwners;
    }

    public void setPreviousOwners(String previousOwners) {
        this.previousOwners = previousOwners;
    }

    public Vehicle(String userId) {
        super(userId);
    }

    public Vehicle() {
        super();
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

    public void getVehicleInfo() {
        VehicleDAO vehicleDAO = new VehicleDAO();
        vehicleDAO.fetchVehicleInfo(this);
    }

}
