package com.cops.ntsf.util;

import java.util.Random;

public class OTPGenerator {
    public static String generateOTP() {
        // Create a random number generator
        Random rand = new Random();

        // Generate a random integer between 100000 and 999999 (inclusive)
        int otpInt = rand.nextInt(900000) + 100000;

        // Convert the integer OTP to a string
        String otpStr = Integer.toString(otpInt);

        // Return the OTP as a string
        return otpStr;
    }
}
