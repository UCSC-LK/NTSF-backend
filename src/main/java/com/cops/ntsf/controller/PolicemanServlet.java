package com.cops.ntsf.controller;
import com.cops.ntsf.model.Policeman;
import com.cops.ntsf.dao.PolicemanDAO;
import org.json.JSONArray;
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
        String mobile_number = request.getParameter("mobile_number");
        String email = request.getParameter("email");
        String rank = request.getParameter("rank");
        String police_station = request.getParameter("police_station");

        System.out.println("Works until Servlet");
        System.out.println(name);
        System.out.println(police_id);
        System.out.println(nic);
        System.out.println(mobile_number);
        System.out.println(email);
        System.out.println(rank);
        System.out.println(police_station);

        if (checkValidations(name, police_id, nic, mobile_number , email,  rank, police_station))
        {
            Policeman policeman = new Policeman(name, police_id, nic, mobile_number, email, rank, police_station);
            policeman.policemanAdded();
        }
        else
        {

        }

        out.write(jsonObject.toString());
        out.close();
    } catch (Exception e)
    {
        e.printStackTrace();
    }

    }

    protected void viewPoliceman(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession(false);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("serverResponse", "Allowed");

        Policeman policeman = new Policeman();
        JSONArray policemanList = policeman.getPolicemanDetails();

        jsonObject.put("List", policemanList );

        out.write(jsonObject.toString());
        out.close();

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

    protected void checkPolicemanMobile_Number(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

//        HttpSession session = request.getSession();
            JSONObject jsonObject = new JSONObject();

            String mobile_number = request.getParameter("mobile_number");
            System.out.println(mobile_number);
            System.out.println("Came until error duplication in servlet");

            Policeman policeman = new Policeman();
            jsonObject.put("alert",  policeman.policemanNicCheck(mobile_number));

            out.write(jsonObject.toString());
            out.close();

        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("error block");
        }

    }

    protected void checkPolicemanEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

//        HttpSession session = request.getSession();
            JSONObject jsonObject = new JSONObject();

            String email = request.getParameter("email");
            System.out.println(email);
            System.out.println("Came until error duplication in servlet");

            Policeman policeman = new Policeman();
            jsonObject.put("alert",  policeman.policemanNicCheck(email));

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
        else if (action.equals("viewPoliceman"))
        {
            viewPoliceman(request, response);
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
        else if (action.equals("checkMobile_Number"))
        {
            checkPolicemanMobile_Number(request, response);
            System.out.println("Hi from Mobile Number Checking servelet");
        }
        else if (action.equals("checkEmail"))
        {
            checkPolicemanEmail(request, response);
            System.out.println("Hi from Email Checking servelet");
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

    private boolean checkValidations(String name, String police_id, String nic, String mobile_number, String email, String rank, String police_station) {
        boolean flagName = false; //flag = true means name validation is passed
        boolean flagPolice_ID = false; //flag = true means Police ID validation is passed
        boolean flagNIC = false; //flag = true means NIC validation is passed
        boolean flagMobile_Number = false; //flag = true means Mobile Number validation is passed
        boolean flagEmail = false; //flag = true means Email validation is passed
        boolean flagRank = false; //flag = true means Rank validation is passed
        boolean flagPolice_Station = false; //flag = true means Police Station is passed
        boolean flag = false; //flag = true means all the validations are passed

        if(name.trim() == "")
        {
            System.out.println("Name is empty");
            flagName = false;
        }
        else if (name.trim().length() < 3)
        {
            System.out.println("Name is too short");
            flagName = false;
        }
        else if (name.trim().length() > 50)
        {
            System.out.println("Name is too long");
            flagName = false;
        }
        else {
            System.out.println("Name is valid");
            flagName = true;
        }

        if (police_id.trim() == "")
        {
            System.out.println("Police ID is empty");
            flagPolice_ID = false;
        }
        else if (police_id.trim().matches("[0-9]+") == false)
        {
            System.out.println("Police ID should contain only digits");
            flagPolice_ID = false;
        }
        else if (police_id.trim().length() != 10)
        {
            System.out.println("Police ID should contain 10 digits");
            flagPolice_ID = false;
        }
        else {
            System.out.println("Police ID is valid");
            flagPolice_ID = true;
        }

        if (nic.trim() == "")
        {
            System.out.println("NIC is empty");
            flagNIC = false;
        }
        else {
            System.out.println("NIC is valid");
            flagNIC = true;
        }
        if (mobile_number.trim() == "")
        {
            System.out.println("Mobile Number is empty");
            flagMobile_Number = false;
        }
        else if (mobile_number.trim().matches("[0-9]+") == false)
        {
            System.out.println("Mobile Number should contain only digits");
            flagMobile_Number = false;
        }
        else if (mobile_number.trim().length() != 10)
        {
            System.out.println("Mobile Number should contain 10 digits");
            flagMobile_Number = false;
        }
        else {
            System.out.println("Mobile Number is valid");
            flagMobile_Number = true;
        }

        if (email.trim() == "")
        {
            System.out.println("Email is empty");
            flagEmail = false;
        }
        else if (email.trim().matches("^[A-Za-z0-9+_.-]+@(.+)$") == false)
        {
            System.out.println("Email is invalid");
            flagEmail = false;
        }
        else {
            System.out.println("Email is valid");
            flagEmail = true;
        }

        if (rank.trim() == "")
        {
            System.out.println("Rank is empty");
            flagRank = false;
        }
        else {
            System.out.println("Rank is valid");
            flagRank = true;
        }

        if (police_station.trim() == "")
        {
            System.out.println("Police Station is empty");
            flagPolice_Station = false;
        }
        else {
            System.out.println("Police Station is valid");
            flagPolice_Station = true;
        }
        if (flagName == true && flagPolice_ID == true && flagNIC == true && flagMobile_Number == true && flagEmail == true && flagRank == true && flagPolice_Station == true)
        {
            flag = true;
        }
        else {
            flag = false;
        }
        return flag;
    }

}
