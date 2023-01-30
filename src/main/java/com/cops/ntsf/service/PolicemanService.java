package com.cops.ntsf.service;

import com.cops.ntsf.constants.PoliceRank;
import com.cops.ntsf.model.Policeman;

public class PolicemanService {

    public Policeman getPolicemanSignedUp(String policeId,
                                          String name,
                                          String nic,
                                          String email,
                                          String mobileNo,
                                          String password) {
        Policeman policeman = new Policeman(policeId, name, nic, email, mobileNo, password);
        policeman.setPolicemanInfo();

//        if (policeId != null) {
//            Auth auth = new Auth(policeId, password);
//            auth.setPoliceAuthInfo();
//        }
        return policeman;
    }

    public Policeman updatePolicemanRankStation(String policeId,
                                                PoliceRank policeRank,
                                                String policeStation) {
        Policeman policeman = new Policeman(policeId, policeRank,policeStation);
        policeman.updatePolicemanRankStation();

        return policeman;
    }

    public Policeman getPolicemanInfo(PoliceRank policeRank, String policeId, String password) {
        Policeman policeman = new Policeman(policeId, policeRank, password);
        policeman.getPolicemanInfo();

        return policeman;
    }
}