package com.cops.ntsf.controller;

import com.cops.ntsf.service.AuthService;
import com.cops.ntsf.util.Validator;

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

        AuthService authService = null;
        if (checkValidations(nic, password)) {
            authService = new AuthService();
        }


        // Output response
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        assert authService != null;
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

    public boolean checkValidations(String nic, String password) {

        Validator validator = new Validator();

        if (validator.checkNICValidation(nic)) {
            validator.checkPasswordValidation(password);
            return true;
        }
        return false;
    }
}