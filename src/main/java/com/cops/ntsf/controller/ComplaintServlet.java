package com.cops.ntsf.controller;

import com.cops.ntsf.model.Complaint;
import com.cops.ntsf.service.ComplaintService;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

public class ComplaintServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action.equals("createComplaint")) {
            createComplaint(request, response);
        }
    }

    protected void createComplaint(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        String complaint_no = request.getParameter("complaint_no");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String user_id = request.getParameter("user_id");

        //remove later
        System.out.println("Works until Servlet");
        System.out.println(complaint_no);
        System.out.println(title);
        System.out.println(description);
        System.out.println(user_id);

        Complaint complaint = null;

        // Validations done in complaintService
        ComplaintService complaintService = new ComplaintService();

        if (complaintService.checkValidations(title, description)) {
            complaint = new Complaint(user_id, title, description, complaint_no);
            complaint.complaintAdded();
        }

        out.write(new Gson().toJson(complaint));
        out.close();
    }

    /*
    @ View Complaint code for user side
    * */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Get request parameters
        String user_id = req.getParameter("user_id");
        ArrayList<Complaint> complaintList;

        Complaint complaint = new Complaint(user_id);
        try {
            complaintList = complaint.getUserComplaintInfo();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Output response
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        out.write(new Gson().toJson(complaintList));
        out.close();
    }

    protected void viewComplaintsAsInvestigationOfficer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("serverResponse", "Allowed");

        Complaint complaint = new Complaint();
        JSONArray complaintListasInvestigationOfficer = complaint.getComplaintsDetailsAsInvestigationOfficer();

        jsonObject.put("List", complaintListasInvestigationOfficer);

        out.write(jsonObject.toString());
        out.close();
    }
}
