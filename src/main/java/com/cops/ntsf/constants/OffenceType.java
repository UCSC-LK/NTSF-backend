package com.cops.ntsf.constants;

public enum OffenceType {
    DRIVER,
    VEHICLE,
    PEDESTRIAN;

    public static OffenceType fromId(int offenceType) {
        return OffenceType.values()[offenceType - 1];
    }
}