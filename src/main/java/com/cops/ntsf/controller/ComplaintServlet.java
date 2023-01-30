package com.cops.ntsf.controller;
import com.cops.ntsf.model.Complaint;
import com.cops.ntsf.dao.ComplaintDAO;

import org.json.JSONObject;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class ComplaintServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if(action.equals("createComplaint"))
        {
            createComplaint(request, response);
        }
    }

    protected void createComplaint(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        JSONObject jsonObject = new JSONObject();

        String user_id = request.getParameter("user_id");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String complaint_no = request.getParameter("complaint_no");

        //remove later
        System.out.println("Works until Servlet");
        System.out.println(user_id);
        System.out.println(title);
        System.out.println(description);
        System.out.println(complaint_no);

        Complaint complaint = new Complaint(user_id, title, description, complaint_no);
        complaint.complaintAdded();


        out.write(jsonObject.toString());
        out.close();

    }





}
