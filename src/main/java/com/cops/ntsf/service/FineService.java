package com.cops.ntsf.service;

import com.cops.ntsf.model.Fine;
import com.cops.ntsf.model.Offence;

import java.sql.SQLException;
import java.util.ArrayList;

public class FineService {
    public ArrayList<Fine> getFinesInfo(String nic) throws SQLException {
        Fine fineObj = new Fine(nic);
        ArrayList<Fine> finesList = fineObj.getUserFinesInfo();

        for (Fine fine : finesList) {
            String offenceNo = fine.getOffenceNo();
            Offence offence = new Offence(offenceNo);
            offence.getOffence();

            fine.setOffence(offence);
        }

        return finesList;
    }
}
