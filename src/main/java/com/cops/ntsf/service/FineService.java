package com.cops.ntsf.service;

import com.cops.ntsf.model.Fine;

import java.text.ParseException;

public class FineService {

    public Fine insertFineInfo(Integer fineNo, String fineType, String userId, Integer fineAmount, String paymentStatus, String policeId) throws ParseException {

        Fine fine = new Fine(fineNo, fineType, userId, fineAmount, paymentStatus, policeId, fineType);
        fine.setFineInfo();

        return fine;
    }
}
