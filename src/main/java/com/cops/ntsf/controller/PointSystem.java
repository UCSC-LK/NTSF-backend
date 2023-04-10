package com.cops.ntsf.controller;

import com.cops.ntsf.model.Point;
import com.cops.ntsf.service.PointService;
import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class PointSystem extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Get request parameters
        String nic = req.getParameter("nic");

        PointService pointService = new PointService();
        Point point = pointService.getPointInfo(nic);

        // Output response
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        out.write(new Gson().toJson(point));
        out.close();

    }
}
