package com.cops.ntsf.service;

import com.cops.ntsf.constants.FineType;
import com.cops.ntsf.constants.PaymentStatus;
import com.cops.ntsf.model.Fine;

import java.sql.Date;

public class FineService {
    public Fine insertFineInfo(Integer ticketNo,
                               Integer fineNo,
                               Date date,
                               Date dueDate,
                               String fineAmount,
                               PaymentStatus paymentStatus,
                               FineType fineType) {
        Fine fine = new Fine(ticketNo, fineNo, date, dueDate, fineAmount, paymentStatus, fineType);
        fine.setFineInfo();

        return fine;
    }
}
