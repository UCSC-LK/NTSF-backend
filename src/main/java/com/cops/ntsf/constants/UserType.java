package com.cops.ntsf.constants;

public enum UserType {
    DRIVER(0),
    VEHICLE(1),
    PEDESTRIAN(2);

    public final int userType;

    UserType(int userType) {
        this.userType = userType;
    }
}