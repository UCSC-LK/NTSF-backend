package com.cops.ntsf.controller;


import com.cops.ntsf.dao.M_OffenceDAO;
import com.cops.ntsf.model.M_Offence;
import com.cops.ntsf.service.M_OffenceService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class M_OffenceServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Get request parameters
//       UserType userType = UserType.fromId(Integer.parseInt(req.getParameter("fine_type")));
        String offenceNo =req.getParameter("offence_no");
        String offenceType = req.getParameter("offence_type");
        String pointWeight = req.getParameter("point_weight");
        String description = req.getParameter("description");
        String amount = req.getParameter("amount");

        M_OffenceService offenceService = new M_OffenceService();
        M_Offence offence = offenceService.insertOffenceInfo(offenceNo, offenceType, pointWeight, description, amount);

        // Output response
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        out.write(new Gson().toJson(offence));
        out.close();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        try {
            System.out.println("req" + request);
            System.out.println("res" + response);
            System.out.println("1");
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<a href='index.html'>Add New Offences</a>");
            out.println("<h1>Offence List</h1>");

            List<M_Offence> list = M_OffenceDAO.getAllOffences();
            for (M_Offence offence : list) {
                System.out.println(""+offence.getOffenceNo());
                          }
            out.print("<table border='1' width='100%'");


            out.print("<tr><th>OffenceNo</th><th>Offence_Type</th><th>Description</th><th>PointWeight</th><th>Amount</th> <th>Edit</th><th>Delete</th></tr>");
            for (M_Offence offence : list) {
                out.print("<tr><td>" + offence.getOffenceNo() + "</td><td>" + offence.getOffenceType() + "</td><td>" + offence.getDescription() + "</td><td>" + offence.getPointWeight() + "</td><td>" + offence.getAmount() + "</td><td><a href='OffenceServlet?offenceNo=" + offence.getOffenceNo() + "'>edit</a></td> <td><a href='DeleteServlet?id=" + offence.getOffenceNo() + "'>delete</a></td></tr>");
            }
            out.print("</table>");

            out.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }


    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        resp.setContentType("text/html");
        PrintWriter out=resp.getWriter();

        String offenceNo = req.getParameter("offence_no");
        String offenceType = req.getParameter("offence_type");
        String pointWeight = req.getParameter("point_weight");
        String description = req.getParameter("description");
        String amount = req.getParameter("amount");


        M_Offence offence=new M_Offence();
        offence.setOffenceNo(offenceNo);
        offence.setOffenceType(offenceType);
        offence.setPointWeight(pointWeight);
        offence.setDescription(description);
        offence.setAmount(amount);

        int status= M_OffenceDAO.updateOffenceInfo(offence);
        if(status>0){
            doGet(req,resp);
        }else{
            out.println("Sorry! unable to update record");
        }
        out.close();
    }



    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer offenceNo = Integer.valueOf(req.getParameter("offence_no"));
        M_OffenceDAO.deleteOffenceInfo(Integer.parseInt(String.valueOf(offenceNo)));
        resp.sendRedirect("this");
    }









}

