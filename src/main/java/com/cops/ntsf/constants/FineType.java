package com.cops.ntsf.constants;

public enum FineType {
    DRIVER,
    VEHICLE,
    PEDESTRIAN;

    public static FineType fromId(int fineType) {
        return FineType.values()[fineType - 1];
    }
}