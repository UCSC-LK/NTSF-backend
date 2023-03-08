package com.cops.ntsf.controller;

import org.json.JSONObject;

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

            String Offence = request.getParameter("name");
            St

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServerCloneException, IOException {

    }
}