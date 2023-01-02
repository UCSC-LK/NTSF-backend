package com.cops.ntsf.controller;


import com.cops.ntsf.dao.PolicemanDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class addPoliceman extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String name = request.getParameter("name");
        String police_id = request.getParameter("police_id");
        String nic = request.getParameter("nic");
        String rank = request.getParameter("rank");
        String police_station = request.getParameter("poiice_station");

        boolean isTrue;

        isTrue = PolicemanDAO.addPoliceman(name, police_id, nic, rank, police_station);

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
