package com.cops.ntsf.controller;

import com.cops.ntsf.model.Offence;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.server.ServerCloneException;

public class OffenceServlet extends HttpServlet {

    protected void addOffence(HttpServletRequest request, HttpServletResponse response) {
        try
        {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            JSONObject jsonObject = new JSONObject();

            int Offence_no = Integer.parseInt(request.getParameter("Offence_no"));
            String Offence_type = request.getParameter("Offence_type");
            String description = request.getParameter("description");
            int amount = Integer.parseInt(request.getParameter("amount"));
            int demerit_points = Integer.parseInt(request.getParameter("demerit_points"));

            System.out.println("Add offence method is called in the offence servlet");

            System.out.println(Offence_no);
            System.out.println(Offence_type);
            System.out.println(description);
            System.out.println(amount);
            System.out.println(demerit_points);

            if(checkValidations(Offence_no, Offence_type, description, amount, demerit_points))
            {
                jsonObject.put("status", "success");
                jsonObject.put("message", "Offence added successfully");
            }
            else
            {
                jsonObject.put("status", "error");
                jsonObject.put("message", "Error occurred while adding offence");
            }

            out.print(jsonObject.toString());
            out.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    protected void viewOffence(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        System.out.println("View offence method is called in the offence servlet");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("serverResponse", "Allowed");

        Offence offence = new Offence();
        JSONArray offenceList = offence.getOffenceDetails();

        jsonObject.put("List", offenceList);
        out.write(jsonObject.toString());
        out.close();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServerCloneException, IOException {

    }
}