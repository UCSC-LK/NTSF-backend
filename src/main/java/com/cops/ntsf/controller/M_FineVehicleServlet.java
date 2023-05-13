package com.cops.ntsf.controller;

import com.cops.ntsf.model.M_Fine;
import com.cops.ntsf.service.M_FineVehicleService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

public class M_FineVehicleServlet extends HttpServlet {
    public M_FineVehicleServlet() {
        super();
    }



    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nic = req.getParameter("nic");
        String location = req.getParameter("location");
        String fineNo = req.getParameter("fine_no");
        String description = req.getParameter("description");
        String policeId = req.getParameter("police_id");
        String fineType = req.getParameter("fine_type");
        String vehicleNo = req.getParameter("vehicle_no");



        M_FineVehicleService fineVehicleService = new M_FineVehicleService();
        M_Fine fine = null;
        try {
            PrintWriter out = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("utf-8");

            out.write(fineVehicleService.insertFineInfo(nic,location,fineNo,description,policeId,fineType,vehicleNo));
            out.close();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }



    }

}