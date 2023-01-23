package com.cops.ntsf.controller;

import com.cops.ntsf.model.TrafficPolice;
import com.cops.ntsf.service.PolicemanService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class TrafficPoliceServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get request parameters
        String name = req.getParameter("name");
        String policeId = req.getParameter("policeId");
        String nic = req.getParameter("nic");
        String rank = req.getParameter("rank");
        String policeStation = req.getParameter("policeStation");

        PolicemanService policemanService = new PolicemanService();
        TrafficPolice trafficPolice = policemanService.appointPolicemanAsTrafficOfficer(name, policeId, nic, rank,policeStation);

        // Output response
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        out.write(new Gson().toJson(trafficPolice));
        out.close();
    }
}
