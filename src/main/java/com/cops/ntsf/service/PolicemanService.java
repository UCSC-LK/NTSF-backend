package com.cops.ntsf.service;

import com.cops.ntsf.model.Auth;
import com.cops.ntsf.model.Policeman;
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

    public Policeman getPolicemanSignedUp(String policeId,
                                          String nic,
                                          String email,
                                          String password) {
        Policeman policeman = new Policeman(policeId, nic, email);
        policeman.setPolicemanInfo();

//        String policeId = policeman.getpoliceId();

        if (policeId != null) {
            Auth auth = new Auth(policeId, password);
            auth.setPoliceAuthInfo();
        }
        return policeman;
    }
}