package com.cops.ntsf.service;

import com.cops.ntsf.constants.OffenceType;
import com.cops.ntsf.constants.PaymentStatus;

import java.sql.Date;

public class FineService {
    public Fine insertFineInfo(String nic,
                               Integer ticketNo,
                               Integer fineNo,
                               Date date,
                               Date dueDate,
                               PaymentStatus paymentStatus,
                               OffenceType offenceType,
                               String Amount,
                               Integer pointWeight) {
        Fine fine = new Fine(nic, ticketNo, fineNo, date, dueDate, paymentStatus, offenceType, Amount, pointWeight);
        fine.setFineInfo();

        return fine;
    }
}
//package com.cops.ntsf.service;
//
//import com.cops.ntsf.constants.OffenceType;
//import com.cops.ntsf.constants.PaymentStatus;
//import com.cops.ntsf.model.Fine;
//
//import java.sql.Date;
//
//public class FineService {
//    public Fine insertFineInfo(String nic,
//                               Integer ticketNo,
//                               Integer fineNo,
//                               Date date,
//                               Date dueDate,
//                               PaymentStatus paymentStatus,
//                               OffenceType offenceType,
//                               String Amount,
//                               Integer pointWeight) {
//        Fine fine = new Fine(nic, ticketNo, fineNo, date, dueDate, paymentStatus, offenceType, Amount, pointWeight);
//        fine.setFineInfo();
//
//        return fine;
//    }
//}
//public class FineService {
//
//    public Fine insertFineInfo(Integer fineNo, String fineType, String userId, Integer fineAmount, String paymentStatus, String policeId) throws ParseException {
//
//        Fine fine = new Fine(fineNo, fineType, userId, fineAmount, paymentStatus, policeId, fineType);
//        fine.setFineInfo();
//
//        return fine;
//    }
//}
