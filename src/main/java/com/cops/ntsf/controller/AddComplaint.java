package com.cops.ntsf.controller;
import com.cops.ntsf.model.AddComplaintModel;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddComplaint extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        boolean isTrue;

        isTrue = AddComplaintModel.addComplaint(title, description);

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
