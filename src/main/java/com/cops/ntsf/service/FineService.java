package com.cops.ntsf.service;

import com.cops.ntsf.constants.OffenceType;
import com.cops.ntsf.constants.PaymentStatus;
import com.cops.ntsf.model.Fine;

import java.sql.Date;

public class FineService {
    public Fine insertFineInfo(Integer ticketNo,
                               Integer fineNo,
                               Date date,
                               Date dueDate,
                               String Amount,
                               PaymentStatus paymentStatus,
                               OffenceType offenceType,
                               Integer pointWeight) {
        Fine fine = new Fine(ticketNo, fineNo, date, dueDate, Amount, paymentStatus, offenceType, pointWeight);
        fine.setFineInfo();

        return fine;
    }
}
