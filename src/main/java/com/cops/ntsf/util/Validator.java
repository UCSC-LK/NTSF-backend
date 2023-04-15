package com.cops.ntsf.util;

import java.util.Arrays;

public class Validator {
    /*
   @ Validate NIC
   * */
    public boolean checkNICValidation(String nic) {

        if (nic.trim().equals("")) {
            System.out.println("NIC is empty");
        } else if (nic.length() == 10 && nic.substring(0, 9).matches("[0-9]+") && !Character.isLetter(nic.charAt(9)) && (nic.charAt(9) == 'x' || nic.charAt(9) == 'v')) {
            System.out.println("NIC is valid");
            return true;
        } else if (nic.length() == 12 && nic.matches("[0-9]+")) {
            System.out.println("NIC is valid");
            return true;
        }
        return false;
    }

    /*
    @ Validate password
    * */
    public void checkPasswordValidation(String password) {

        if (password == null || password.length() < 8) {
            System.out.println("Password is not valid");
            return;
        }

        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowercase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else {
                hasSpecialChar = true;
            }
        }

        if (!hasUppercase || !hasLowercase || !hasDigit || !hasSpecialChar) {
            System.out.println("Password should contain at least one uppercase letter, one lowercase letter, one digit, and one special character");
        }
    }

    /*
    @ Validate Mobile No in Sri Lankan format
    * */
    public boolean checkMobileNoValidation(String mobileNo) {
        if (mobileNo == null || mobileNo.isEmpty()) {
            System.out.println("Mobile No is empty");
            return false;
        }

        // Remove any leading plus sign
        if (mobileNo.startsWith("+")) {
            mobileNo = mobileNo.substring(1);
        }

        // Check length and format
        if (mobileNo.length() != 9 || !mobileNo.matches("\\d{9}")) {
            return false;
        }

        // Check for valid network operator codes

        // Declares a String array named validOperatorCodes
        // Initializes it with five valid network operator codes in Sri Lanka
        String[] validOperatorCodes = {"071", "072", "075", "077", "078"};

        // Extracts the first 3 digits of the phone number and assigns them to a new String variable named operatorCode
        String operatorCode = mobileNo.substring(0, 3);

        // Checks whether the operatorCode is in the list of validOperatorCodes
        if (!Arrays.asList(validOperatorCodes).contains(operatorCode)) {
            return false;
        }

        // All validation checks have passed
        return true;
    }

    /*
    @ Validate email
    * */
    public boolean checkEmailValidation(String email) {
        if (email.trim().equals("")) {
            System.out.println("Email is empty");
            return false;
        } else if (!email.trim().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            System.out.println("Email is invalid");
            return false;
        } else {
            System.out.println("Email is valid");
            return true;
        }
    }
}
