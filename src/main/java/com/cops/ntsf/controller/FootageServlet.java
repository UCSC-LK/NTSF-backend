package com.cops.ntsf.controller;

import com.cops.ntsf.model.Footage;
import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class FootageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        // Get request parameters
        String complaintNo = req.getParameter("complaint_no");
        String footageFile = req.getParameter("footage_file");

        Footage footage = new Footage();
        footage.uploadFootage(complaintNo, footageFile);

        // Output response
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        out.write(new Gson().toJson(footage));
        out.close();
    }
}
