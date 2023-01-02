package com.cops.ntsf.controller;
import com.cops.ntsf.dao.ComplaintDAO;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Complaint extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String user_id = request.getParameter("user_id");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String complaint_no = request.getParameter("complaint_no");


        boolean isTrue;

        isTrue = ComplaintDAO.addComplaint(user_id, title, description, complaint_no);

        if (isTrue == true)
        {
            RequestDispatcher dis = request.getRequestDispatcher("success.jsp");
            dis.forward(request, response);
        }
        else
        {
            RequestDispatcher dis2 = request.getRequestDispatcher("unsuccess.jsp");
            dis2.forward(request, response);
        }

    }

}
