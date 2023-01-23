package com.cops.ntsf.service;

import com.cops.ntsf.model.TrafficPolice;

public class PolicemanService {
    public TrafficPolice appointPolicemanAsTrafficOfficer(String name,
                                                          String policeId,
                                                          String nic,
                                                          String rank, String policeStation) {
        TrafficPolice trafficPolice = new TrafficPolice(name, policeId, nic, rank, policeStation);
        trafficPolice.setTrafficPoliceInfo();
        return trafficPolice;
    }
}