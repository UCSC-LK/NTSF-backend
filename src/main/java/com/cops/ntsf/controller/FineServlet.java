package com.cops.ntsf.controller;

import com.cops.ntsf.constants.FineType;
import com.cops.ntsf.constants.PaymentStatus;
import com.cops.ntsf.model.Fine;
import com.cops.ntsf.service.FineService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        // Get request parameters
        Integer ticketNo = Integer.valueOf(req.getParameter("ticket_no"));
        Integer fineNo = Integer.valueOf(req.getParameter("fine_no"));
        Date date = Date.valueOf(req.getParameter("date"));
        Date dueDate = Date.valueOf(req.getParameter("due_date"));
        String fineAmount = req.getParameter("fine_amount");
        PaymentStatus paymentStatus = PaymentStatus.valueOf(req.getParameter("payment_status"));
        FineType fineType = FineType.valueOf(req.getParameter("fine_type"));

        FineService fineService = new FineService();
        Fine fine = fineService.insertFineInfo(ticketNo, fineNo, date, dueDate, fineAmount, paymentStatus, fineType);

        // Output response
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        out.write(new Gson().toJson(fine));
        out.close();

    }
}
