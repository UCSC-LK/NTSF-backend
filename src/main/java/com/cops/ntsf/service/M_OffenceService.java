package com.cops.ntsf.service;

import com.cops.ntsf.model.M_Offence;

public class M_OffenceService {

    public M_Offence insertOffenceInfo(String offenceNo, String offenceType, String pointWeight, String description, String amount) {

        M_Offence offence = new M_Offence(offenceNo, offenceType, pointWeight, description, amount);
        offence.setOffenceInfo();

        return offence;
    }
}
