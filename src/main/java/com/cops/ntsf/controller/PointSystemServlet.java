package com.cops.ntsf.controller;

import com.cops.ntsf.model.PointData;
import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class PointSystemServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Get request parameters
        String nic = req.getParameter("nic");

        PointData pointData = new PointData();

        pointData.getPointInfo(nic);

        // Output response
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

//        out.write(ParseJSON.parseToJSONString(pointData));
        out.write(new Gson().toJson(pointData));
        out.close();

    }

//    public void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String nic = req.getParameter("nic");
//        String fineNo = req.getParameter("fineNo");
//
//        PointService pointService = new PointService();
//        PointData point = pointService.reducePoints(nic, fineNo);
//
//        // Output response
//        PrintWriter out = resp.getWriter();
//        resp.setContentType("application/json");
//        resp.setCharacterEncoding("utf-8");
//
//        out.write(new Gson().toJson(point));
//        out.close();
//    }
}
