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
import java.util.List;


public class OffenceServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Get request parameters
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            System.out.println("req" + request);
            System.out.println("res" + response);
            System.out.println("1");
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<a href='index.html'>Add New Offences</a>");
            out.println("<h1>Offence List</h1>");

            OffenceDAO offenceDAO = new OffenceDAO();
            List<Offence> list = offenceDAO.getAllOffences();


            for (Offence offence : list) {
                System.out.println("" + offence.getOffenceNo());
            }
            out.print("<table border='1' width='100%'");


            out.print("<tr><th>OffenceNo</th><th>Offence_Type</th><th>Description</th><th>PointWeight</th><th>Amount</th> <th>Edit</th><th>Delete</th></tr>");
            for (Offence offence : list) {
                out.print("<tr><td>" + offence.getOffenceNo() + "</td><td>" + offence.getOffenceType() + "</td><td>" + offence.getDescription() + "</td><td>" + offence.getPointWeight() + "</td><td>" + offence.getAmount() + "</td><td><a href='OffenceServlet?offenceNo=" + offence.getOffenceNo() + "'>edit</a></td> <td><a href='DeleteServlet?id=" + offence.getOffenceNo() + "'>delete</a></td></tr>");
            }
            out.print("</table>");

            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        Integer offenceNo = Integer.valueOf(req.getParameter("offence_no"));
        String offenceType = req.getParameter("offence_type");
        Integer pointWeight = Integer.valueOf(req.getParameter("point_weight"));
        String description = req.getParameter("description");
        Integer amount = Integer.valueOf(req.getParameter("amount"));

        OffenceDAO offenceDAO = new OffenceDAO(); // create an instance of OffenceDAO
        Offence offence = new Offence();

        offence.setOffenceNo(offenceNo);
        offence.setOffenceType(offenceType);
        offence.setPointWeight(pointWeight);
        offence.setDescription(description);
        offence.setAmount(amount);


        int status = offenceDAO.updateOffenceInfo(offence); // call the method on the instance

        if (status > 0) {
            doGet(req, resp);
        } else {
            out.println("Sorry! unable to update record");
        }
        out.close();
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer offenceNo = Integer.valueOf(req.getParameter("offence_no"));
        OffenceDAO offenceDAO = new OffenceDAO();
        offenceDAO.deleteOffenceInfo(Integer.parseInt(String.valueOf(offenceNo)));
        resp.sendRedirect("this");
    }

}

