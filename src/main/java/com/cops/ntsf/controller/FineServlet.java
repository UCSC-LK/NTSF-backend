package com.cops.ntsf.controller;

import com.cops.ntsf.model.Fine;
//import com.cops.ntsf.dao.FineDAO;
import com.cops.ntsf.service.FineService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class FineServlet extends HttpServlet {



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Get request parameters
//       UserType userType = UserType.fromId(Integer.parseInt(req.getParameter("fine_type")));
        Integer fineNo = Integer.valueOf(req.getParameter("fine_no"));
        String fineType = req.getParameter("fine_type");
        String userId = req.getParameter("user_id");
//        String fineDate =req.getParameter("fine_date");
//        String dueDate =req.getParameter("due_date");
        Integer fineAmount = Integer.valueOf(req.getParameter("fine_amount"));
        String paymentStatus = req.getParameter("payment_status");
        String policeId = req.getParameter("police_id");
//        Time fineTime = Time.valueOf(req.getParameter("fine_time"));


        FineService fineService = new FineService();
        Fine fine = null;
        try {
            fine = fineService.insertFineInfo(fineNo, fineType, userId, fineAmount, paymentStatus,policeId);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

//        Fine fine;
//        try {
//            fine = fineService.insertFineInfo(fineNo, fineType, userId, fineAmount, paymentStatus,policeId);
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }


        // Output response
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        out.write(new Gson().toJson(fine));
        out.close();
    }




//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        // Get request parameters
//        String userId = req.getParameter("user_id");
//        ArrayList<Fine> finesList;
//        //        UserService userService = new UserService();
//
//        Fine fine = null;
//        try {
//            fine = new Fine(fineNo, userId, userId, fineAmount, paymentStatus, policeId);
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            finesList = fine.getUserFinesInfo();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
//
//
//        // Output response
//        PrintWriter out = resp.getWriter();
//        resp.setContentType("application/json");
//        resp.setCharacterEncoding("utf-8");
//
//        out.write(new Gson().toJson(finesList));
//        out.close();
//    }
}
