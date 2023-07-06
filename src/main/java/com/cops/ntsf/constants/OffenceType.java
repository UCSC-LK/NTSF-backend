package com.cops.ntsf.constants;

public enum OffenceType {
    DRIVER,
    VEHICLE,
    PEDESTRIAN;

    public static OffenceType fromId(int offenceType) {
        return OffenceType.values()[offenceType - 1];
    }

    public static OffenceType fromString(String offenceTypeString) {
        OffenceType offenceType = null;
        switch (offenceTypeString) {
            case "driver":
                offenceType = DRIVER;
                break;
            case "vehicle":
                offenceType = VEHICLE;
                break;
            case "pedestrian":
                offenceType = PEDESTRIAN;
                break;
            default:
                break;
        }
        return offenceType;
    }
}