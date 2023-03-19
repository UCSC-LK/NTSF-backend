package com.cops.ntsf.constants;

public enum PaymentStatus {
    PAY,
    PAID;

    public static PaymentStatus fromId(int paymentStatus) {
        return PaymentStatus.values()[paymentStatus - 1];
    }
}