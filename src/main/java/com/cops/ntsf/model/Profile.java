package com.cops.ntsf.model;

public class Profile {

    private final User user;
    private final Driver driver;
    private final Vehicle vehicle;
    private final People people;

    public Profile(User user, Driver driver, Vehicle vehicle, People people) {
        this.user = user;
        this.driver = driver;
        this.vehicle = vehicle;
        this.people = people;
    }

    public User getUser() {
        return user;
    }

    public Driver getDriver() {
        return driver;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public People getPeople() {
        return people;
    }
}
