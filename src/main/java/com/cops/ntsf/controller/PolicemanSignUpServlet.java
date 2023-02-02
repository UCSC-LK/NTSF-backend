package com.cops.ntsf.controller;

import com.cops.ntsf.model.Policeman;
import com.cops.ntsf.service.PolicemanService;
import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


// Policeman sign up
public class PolicemanSignUpServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Get request parameters
        String policeId = req.getParameter("police_id");
        String name = req.getParameter("name");
        String nic = req.getParameter("nic");
        String email = req.getParameter("email");
        String mobileNo = req.getParameter("mobile_no");
        String password = req.getParameter("password");

        PolicemanService policemanService = new PolicemanService();
//        Policeman policeman = policemanService.getPolicemanSignedUp(policeId, name, nic, email, mobileNo, password);

        // Output response
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

//        out.write(new Gson().toJson(policeman));
        out.close();
    }
}
