package com.cops.ntsf.controller;

import com.cops.ntsf.model.User;
import com.cops.ntsf.service.UserService;
import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class UserSignUpServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Get request parameters
        String nic = req.getParameter("nic");
        String email = req.getParameter("email");
        String mobileNo = req.getParameter("mobile_no");
        String password = req.getParameter("password");

        User user = null;
        if (checkValidations(nic, email, mobileNo, password)) {
            UserService userService = new UserService();
            user = userService.getUserSignedUp(nic, email, mobileNo, password);
        }

        // Output response
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        out.write(new Gson().toJson(user));
        out.close();
    }

    private boolean checkValidations(String nic, String email, String mobileNo, String password) {
        return checkNICValidation(nic) && checkEmailValidation(email) && checkMobileNoValidation(mobileNo) && checkPasswordValidation(password);
    }

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
    public boolean checkPasswordValidation(String password) {

        if (password == null || password.length() < 8) {
            System.out.println("Password is not valid");
            return false;
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
            return false;
        }
        return false;
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
