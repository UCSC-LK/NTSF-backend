package com.cops.ntsf.controller;

import com.cops.ntsf.model.Fine;
import com.cops.ntsf.model.Offence;
import com.cops.ntsf.service.OffenceService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

public class FineServlet extends HttpServlet {



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Get request parameters
//       UserType userType = UserType.fromId(Integer.parseInt(req.getParameter("fine_type")));
        Integer offenceNo = Integer.valueOf(req.getParameter("offence_no"));
        String offenceType = req.getParameter("offence_type");
        Integer pointWeight = Integer.valueOf(req.getParameter("point_weight"));
        String description = req.getParameter("description");
        Integer amount = Integer.valueOf(req.getParameter("amount"));

        OffenceService offenceService = new OffenceService();
        Offence offence = offenceService.insertOffenceInfo(offenceNo, offenceType, pointWeight, description, amount);

        // Output response
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        out.write(new Gson().toJson(offence));
        out.close();
    }




    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Get request parameters
        String userId = req.getParameter("user_id");
        ArrayList<Fine> finesList;

//        UserService userService = new UserService();
        Fine fine = new Fine(userId);
        try {
            finesList = fine.getUserFinesInfo();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Output response
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        out.write(new Gson().toJson(finesList));
        out.close();
    }
}
