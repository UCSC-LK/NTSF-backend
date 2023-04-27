package com.cops.ntsf.service;

import com.cops.ntsf.model.Fine;
import com.cops.ntsf.model.Offence;

import java.sql.SQLException;
import java.util.ArrayList;

public class FineService {

    /**
     * Made the function static and variables final
     */
    public static class FinesInfo {
        private final ArrayList<Fine> fines;
        private final String offenceType;
        private final String amount;

        public FinesInfo(ArrayList<Fine> fines, String offenceType, String amount) {
            this.fines = fines;
            this.offenceType = offenceType;
            this.amount = amount;
        }

        public ArrayList<Fine> getFines() {
            return fines;
        }

        public String getOffenceType() {
            return offenceType;
        }

        public String getAmount() {
            return amount;
        }
    }

    public FinesInfo getFinesInfo(String nic) throws SQLException {
        Fine fine = new Fine(nic);
        fine.getUserFinesInfo();

        String offenceNo = fine.getOffenceNo();

        Offence offence = new Offence(offenceNo);
        String offenceType = offence.getOffence_type();
        String amount = String.valueOf(offence.getAmount());

        ArrayList<Fine> fines = new ArrayList<>();
        fines.add(fine);

        return new FinesInfo(fines, offenceType, amount);
    }
}
