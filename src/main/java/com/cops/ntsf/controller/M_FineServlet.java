package com.cops.ntsf.controller;

import com.cops.ntsf.model.M_Fine;
import com.cops.ntsf.service.M_FineService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.time.LocalDateTime;

public class M_FineServlet extends HttpServlet {
    public M_FineServlet() {
        super();
    }



    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String licenceNo = req.getParameter("licence_number");
        String location = req.getParameter("location");
        String fineNo = req.getParameter("fine_no");
        String description = req.getParameter("description");
        String policeId = req.getParameter("police_id");
        String fineType = req.getParameter("fine_type");
        String vehicleNo = req.getParameter("vehicle_no");

        LocalDateTime imposedDateTime = LocalDateTime.now();
        LocalDateTime dueDateTime = imposedDateTime.plusDays(14);








//        String latitude = req.getParameter("latitude");
//        String longitude = req.getParameter("longitude");

//        String latitude = "79.764737";
//        String longitude = "2.2232323";




        M_FineService fineService = new M_FineService();
        M_Fine fine = null;
        try {
            PrintWriter out = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("utf-8");

            out.write(fineService.insertFineInfo(licenceNo,location,fineNo,description,policeId,fineType,vehicleNo,imposedDateTime,dueDateTime));
            out.close();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }



    }



}