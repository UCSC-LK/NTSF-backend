package com.cops.ntsf.controller;

import com.cops.ntsf.constants.FineType;
import com.cops.ntsf.model.Fine;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Get request parameters
        String userId = req.getParameter("user_id");
        FineType fineType = FineType.valueOf(req.getParameter("fine_type"));
        ArrayList<Fine> finesList;

//        UserService userService = new UserService();
//        Fine fine = new Fine(userId);
//        try {
//            finesList = fine.getUserFinesInfo();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

        Fine fine = new Fine(userId, fineType);
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
