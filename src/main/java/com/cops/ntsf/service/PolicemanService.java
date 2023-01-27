package com.cops.ntsf.service;

import com.cops.ntsf.model.Auth;
import com.cops.ntsf.model.Policeman;

public class PolicemanService {

    public Policeman getPolicemanSignedUp(String policeId,
                                          String name,
                                          String nic,
                                          String email,
                                          String mobileNo,
                                          String password) {
        Policeman policeman = new Policeman(policeId, name, nic, email, mobileNo);
        policeman.setPolicemanInfo();

        if (policeId != null) {
            Auth auth = new Auth(policeId, password);
            auth.setPoliceAuthInfo();
        }
        return policeman;
    }

    public Policeman updatePolicemanRankStation(String policeId,
                                                String rank,
                                                String policeStation) {
        Policeman policeman = new Policeman(policeId,rank,policeStation);
        policeman.updatePolicemanRankStation();

        return policeman;
    }
}