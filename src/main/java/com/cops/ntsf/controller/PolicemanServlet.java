package com.cops.ntsf.controller;
import com.cops.ntsf.model.Policeman;
import com.cops.ntsf.dao.PolicemanDAO;
import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class PolicemanServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        JSONObject jsonObject = new JSONObject();

        String name = request.getParameter("name");
        String police_id = request.getParameter("police_id");
        String nic = request.getParameter("nic");
        String rank = request.getParameter("rank");
        String police_station = request.getParameter("police_station");

        System.out.println("Works until Servlet");
        System.out.println(name);
        System.out.println(police_id);
        System.out.println(nic);
        System.out.println(rank);
        System.out.println(police_station);

        Policeman policeman = new Policeman(name, police_id, nic, rank, police_station);
        policeman.policemanAdded();

        out.write(jsonObject.toString());
        out.close();
    }



}
