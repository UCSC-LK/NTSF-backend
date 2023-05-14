package com.cops.ntsf.controller;

import com.cops.ntsf.service.M_PolicemanService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.Base64;

public class M_PoliceLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Get request parameters
        try{
            String police_id = req.getParameter("police_id");
            String password = req.getParameter("password");
            String hashedPassword = hashingPassword(password);

            M_PolicemanService policemanService = new M_PolicemanService();

            // Output response
            PrintWriter out = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("utf-8");

            out.write(policemanService.verifyLogin(police_id,hashedPassword));
            out.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String hashingPassword(String password) throws Exception {
        String originalString = password;
        System.out.println("Original String to hash: " + originalString);
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(originalString.getBytes("UTF-8"));
        String encoded = Base64.getEncoder().encodeToString(hash);
        System.out.println("Hash: " + encoded);
        return encoded;
    }
}