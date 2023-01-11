package com.cops.ntsf.controller;

import com.cops.ntsf.dao.PoliceStationDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddPoliceStation extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String name = request.getParameter("name");
        //String station_id = request.getParameter("station_id");

        boolean isTrue;

        isTrue = PoliceStationDAO.AddPoliceStation(/*station_id,*/name);

        if (isTrue == true)
        {
            RequestDispatcher dis = request.getRequestDispatcher("success.jsp");
            dis.forward(request, response);
        }
        else{
            RequestDispatcher dis2 = request.getRequestDispatcher("unsuccess.jsp");
            dis2.forward(request, response);
        }
    }
}
