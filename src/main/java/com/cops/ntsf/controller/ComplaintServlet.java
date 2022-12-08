package com.cops.ntsf.util;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ComplaintServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        boolean isTrue;

        isTrue = AddComplaintDBUtil.addComplaint(title, description);

        if (isTrue == true) {
            RequestDispatcher dis = request.getRequestDispatcher("http://127.0.0.1:5500/driver/temporary/success.html");
            dis.forward(request, response);
        } else {
            RequestDispatcher dis2 = request.getRequestDispatcher("C:\\Users\\Suraif\\Desktop\\GroupProject\\NTSF-frontend\\driver\\temporary\\unsuccess.html");
            dis2.forward(request, response);
        }
    }
}
