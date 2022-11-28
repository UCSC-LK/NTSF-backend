package com.cops.ntsf.constants;

public enum UserType {
    DRIVER,
    VEHICLE,
    PEDESTRIAN;

    public static UserType fromId(int userType) {
        return UserType.values()[userType - 1];
    }

}