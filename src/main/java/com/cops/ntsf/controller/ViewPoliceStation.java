package com.cops.ntsf.controller;

import com.cops.ntsf.model.PoliceStation;
import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

public class ViewPoliceStation extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String name = req.getParameter("name");
        ArrayList<PoliceStation> PoliceStationList;

        PoliceStation PoliceStation = new PoliceStation(station_id);
        try{
            PoliceStationList = PoliceStation.getPoliceStationInfo();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        out.write(new Gson().toJson(PoliceStationList));
        out.close();
    }
}
