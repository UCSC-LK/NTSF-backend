package com.cops.ntsf.controller;

import com.cops.ntsf.service.AuthService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class UserLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        // Get request parameters
        String nic = req.getParameter("nic");
        String password = req.getParameter("password");

        String hashedPassword;
        try {
            hashedPassword = hashingPassword(password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        AuthService authService = new AuthService();
        checkValidations(nic, password);

        // Output response
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        out.write(authService.verifyLogin(nic, hashedPassword));
        out.close();
    }

    public static String hashingPassword(String password) throws Exception {
        System.out.println("Original String to hash: " + password);
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        String encoded = Base64.getEncoder().encodeToString(hash);
        System.out.println("Hash: " + encoded);
        return encoded;
    }

    public void checkValidations(String nic, String password) {
        if (checkNICValidation(nic)) {
            checkPasswordValidation(password);
        }
    }

    /*
    @ Validate NIC
    * */
    public boolean checkNICValidation(String nic) {

        boolean flagNic = false; // flag = true means title validation is passed

        if (nic.trim().equals("")) {
            System.out.println("NIC is empty");
        } else if (nic.length() == 10 && nic.substring(0, 9).matches("[0-9]+") && !Character.isLetter(nic.charAt(9)) && (nic.charAt(9) == 'x' || nic.charAt(9) == 'v')) {
            System.out.println("NIC is valid");
            flagNic = true;
        } else if (nic.length() == 12 && nic.matches("[0-9]+")) {
            System.out.println("NIC is valid");
            flagNic = true;
        }

        return flagNic;
    }

    /*
    @ Validate password
    * */
    public boolean checkPasswordValidation(String password) {

        boolean flagPassword = false; // flag = true means password validation is passed

        if (password == null || password.length() < 8) {
            System.out.println("Password is not valid");
        }

        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        assert password != null;

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
        return flagPassword;
    }
}