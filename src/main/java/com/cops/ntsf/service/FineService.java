package com.cops.ntsf.service;

import com.cops.ntsf.model.Fine;
import com.cops.ntsf.model.Offence;

import java.sql.SQLException;
import java.util.ArrayList;

public class FineService {
    public ArrayList<Fine> getFinesInfo(String nic, Integer offenceNo) throws SQLException {
        Fine fine = new Fine(nic);
        fine.getUserFinesInfo();

        Offence offence = new Offence(offenceNo);
        offence.getOffence();

        return new ArrayList<>();
    }
}
