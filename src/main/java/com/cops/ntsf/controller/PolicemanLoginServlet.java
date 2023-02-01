package com.cops.ntsf.controller;

import com.cops.ntsf.constants.PoliceRank;
import com.cops.ntsf.model.Policeman;
import com.cops.ntsf.service.PolicemanService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class PolicemanLoginServlet extends HttpServlet {
    // Police Login
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Get request parameters
        PoliceRank policeRank = PoliceRank.fromId(Integer.parseInt(req.getParameter("police_rank")));
        String policeId = req.getParameter("police_id");
        String password = req.getParameter("password");

        PolicemanService policemanService = new PolicemanService();

        // Output response
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        out.write(policemanService.verifyLogin(policeId, password, policeRank));
        out.close();
    }
}
