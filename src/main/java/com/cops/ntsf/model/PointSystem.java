package com.cops.ntsf.model;

import java.util.ArrayList;
import java.util.List;

public class PointSystem {
    private final List<Offence> offenceList;

    public PointSystem() {
        offenceList = new ArrayList<>();

        // Initialize offenceList and their corresponding amount and point weight
        offenceList.add(new Offence("Speeding", 100, 2));
        offenceList.add(new Offence("Running a red light", 150, 3));
        offenceList.add(new Offence("Driving under the influence", 500, 5));
        // Add more offenceList as needed
    }

    public List<Offence> getOffences() {
        return offenceList;
    }

    // You can add other methods as needed, such as methods to get a violation by name or to calculate the total fine and points for a given list of offenceList.
}
