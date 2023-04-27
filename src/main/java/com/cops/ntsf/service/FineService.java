package com.cops.ntsf.service;

import com.cops.ntsf.model.Fine;
import com.cops.ntsf.model.Offence;

import java.sql.SQLException;
import java.util.ArrayList;

public class FineService {

    public class FinesInfo {
        private ArrayList<Fine> fines;
        private String offenceType;

        public FinesInfo(ArrayList<Fine> fines, String offenceType) {
            this.fines = fines;
            this.offenceType = offenceType;
        }

        public ArrayList<Fine> getFines() {
            return fines;
        }

        public String getOffenceType() {
            return offenceType;
        }
    }

    public FinesInfo getFinesInfo(String nic) throws SQLException {
        Fine fine = new Fine(nic);
        fine.getUserFinesInfo();

        String offenceNo = fine.getOffenceNo();

        Offence offence = new Offence(offenceNo);
        String offenceType = offence.getOffence_type();

        ArrayList<Fine> fines = new ArrayList<>();
        fines.add(fine);

        return new FinesInfo(fines, offenceType);
    }
}
