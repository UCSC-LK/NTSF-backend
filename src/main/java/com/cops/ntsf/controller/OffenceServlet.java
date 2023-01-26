package com.cops.ntsf.controller;


import com.cops.ntsf.dao.OffenceDAO;
import com.cops.ntsf.model.Offence;
import com.cops.ntsf.service.OffenceService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class OffenceServlet extends HttpServlet {
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



    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        resp.setContentType("text/html");
        PrintWriter out=resp.getWriter();

        Integer offenceNo = Integer.valueOf(req.getParameter("offence_no"));
        String offenceType = req.getParameter("offence_type");
        Integer pointWeight = Integer.valueOf(req.getParameter("point_weight"));
        String description = req.getParameter("description");
        Integer amount = Integer.valueOf(req.getParameter("amount"));


        Offence offence=new Offence();
        offence.setOffenceNo(offenceNo);
        offence.setOffenceType(offenceType);
        offence.setPointWeight(pointWeight);
        offence.setDescription(description);
        offence.setAmount(amount);

        int status= OffenceDAO.updateOffenceInfo(offence);
        if(status>0){
            resp.sendRedirect("ViewServlet");
        }else{
            out.println("Sorry! unable to update record");
        }
        out.close();
    }


    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer offenceNo = Integer.valueOf(req.getParameter("offence_no"));
        OffenceDAO.deleteOffenceInfo(Integer.parseInt(String.valueOf(offenceNo)));
        resp.sendRedirect("ViewServlet");
    }



}

