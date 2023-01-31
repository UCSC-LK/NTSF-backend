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
    protected void addPoliceman(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    try
    {
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
    } catch (Exception e)
    {
        e.printStackTrace();
    }

    }

    protected void checkPolicemanPolice_ID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    try{
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

//        HttpSession session = request.getSession();
        JSONObject jsonObject = new JSONObject();

        String police_id = request.getParameter("police_id");
        System.out.println(police_id);
        System.out.println("Came until error duplication in servlet");

        Policeman policeman = new Policeman();
        jsonObject.put("alert",  policeman.policemanPolice_IDCheck(police_id));

        out.write(jsonObject.toString());
        out.close();

    } catch (Exception e)
    {
        e.printStackTrace();
        System.out.println("error block");
    }
    }

    protected void checkPolicemanNic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

//        HttpSession session = request.getSession();
            JSONObject jsonObject = new JSONObject();

            String nic = request.getParameter("nic");
            System.out.println(nic);
            System.out.println("Came until error duplication in servlet");

            Policeman policeman = new Policeman();
            jsonObject.put("alert",  policeman.policemanNicCheck(nic));

            out.write(jsonObject.toString());
            out.close();

        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("error block");
        }

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if(action.equals("addPoliceman")) {
            addPoliceman(request, response);
        }
        else if (action.equals("checkPoliceman_ID"))
        {
            checkPolicemanPolice_ID(request, response);
            System.out.println("Hi");
        }
        else if (action.equals("checkNIC"))
        {
            checkPolicemanNic(request, response);
            System.out.println("Hi from NIC Checking servelet");
        }
    }

//    public void doGet(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
//        String action = request.getParameter("action");
//
//        if (action.equals("checkPoliceman_ID"))
//        {
//            checkPolicemanPolice_ID(request, response);
//            System.out.println("Hi");
//        }
//    }



}
