package com.cops.ntsf.model;

import com.cops.ntsf.constants.OffenceType;

import java.util.ArrayList;

public class FinesByOffenceType {
    // Private instance variables named "driver", "vehicle", "pedestrian" of type ArrayList<Fine>
    private final ArrayList<Fine> driver;
    private final ArrayList<Fine> vehicle;
    private final ArrayList<Fine> pedestrian;

    public ArrayList<Fine> getDriver() {
        return driver;
    }

    public ArrayList<Fine> getVehicle() {
        return vehicle;
    }

    public ArrayList<Fine> getPedestrian() {
        return pedestrian;
    }

    // Constructor for the FinesByOffenceType class
    public FinesByOffenceType() {
        // Initialize private instance variables driver, vehicle, and pedestrian with new instances of ArrayList<Fine>
        this.driver = new ArrayList<>();
        this.vehicle = new ArrayList<>();
        this.pedestrian = new ArrayList<>();
    }

    /**
     * Method name - FinesByOffenceTypeFactory
     *
     * @param finesList ArrayList<Fine> type
     * @return FinesByOffenceType object
     */
    public FinesByOffenceType FinesByOffenceTypeFactory(ArrayList<Fine> finesList) {
        // For loop that iterates through each Fine object in the finesList ArrayList
        for (Fine fine : finesList) {
            // OffenceType.fromString() method takes the offence_type as a String input
            // And returns the corresponding enum value of OffenceType
            OffenceType offenceType = OffenceType.fromString(fine.getOffence().getOffence_type());

            switch (offenceType) {
                case DRIVER:
                    this.driver.add(fine);
                    break;
                case VEHICLE:
                    this.vehicle.add(fine);
                    break;
                case PEDESTRIAN:
                    this.pedestrian.add(fine);
                    break;
                default:
                    break;
            }
        }
        return this;
    }
}
