package com.cops.ntsf.controller;

import com.cops.ntsf.constants.OffenceType;
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
    public FineServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Get request parameters
        String nic = req.getParameter("nic");
        String offenceType = req.getParameter("offence_type");

        ArrayList<Fine> finesList;

        Fine fine = new Fine(nic, offenceType);
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
        // Get request parameters
        String nic = req.getParameter("nic");
        Integer ticketNo = Integer.valueOf(req.getParameter("ticket_no"));
        Integer fineNo = Integer.valueOf(req.getParameter("fine_no"));
        Date date = Date.valueOf(req.getParameter("date"));
        Date dueDate = Date.valueOf(req.getParameter("due_date"));
        PaymentStatus paymentStatus = PaymentStatus.valueOf(req.getParameter("payment_status"));
        OffenceType offenceType = OffenceType.valueOf(req.getParameter("offence_type"));
        String amount = req.getParameter("amount");
        Integer pointWeight = Integer.valueOf(req.getParameter("point_weight"));

        FineService fineService = new FineService();
        Fine fine = fineService.insertFineInfo(nic, ticketNo, fineNo, date, dueDate, paymentStatus, offenceType, amount, pointWeight);

        // Output response
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        out.write(new Gson().toJson(fine));
        out.close();

    }

    public void addFine(HttpServletRequest request, HttpServletResponse response) {
    }
}
