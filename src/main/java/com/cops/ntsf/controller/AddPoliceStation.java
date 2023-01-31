package com.cops.ntsf.controller;


import com.cops.ntsf.dao.PoliceStationDAO;
import com.cops.ntsf.model.PoliceStation;
import org.json.JSONObject;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
public class AddPoliceStation extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
       PrintWriter out = response.getWriter();
       response.setContentType("text/html");

       HttpSession session = request.getSession();
       JSONObject jsonObject = new JSONObject();
       String station_id = request.getParameter("station_id");
       String name = request.getParameter("name");

       System.out.println(station_id);
       System.out.println(name);

       PoliceStation PoliceStation = new PoliceStation(station_id, name);
       PoliceStation.PoliceStationAdded();

       out.write(jsonObject.toString());
       out.close();
    }
}
