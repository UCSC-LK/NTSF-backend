package com.cops.ntsf.controller;
import com.cops.ntsf.constants.PoliceRank;
import com.cops.ntsf.model.Policeman;
import com.cops.ntsf.service.PolicemanService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class PolicemanServlet extends HttpServlet {
    // IGP Add policeman to the system
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//
//        PrintWriter out = response.getWriter();
//        response.setContentType("text/html");
//
////        HttpSession session = request.getSession();
//        JSONObject jsonObject = new JSONObject();
//
//        String name = request.getParameter("name");
//        String policeId = request.getParameter("police_id");
//        String nic = request.getParameter("nic");
//        String rank = request.getParameter("rank");
//        String policeStation = request.getParameter("police_station");
//
//        System.out.println("Works until Servlet");
//        System.out.println(name);
//        System.out.println(policeId);
//        System.out.println(nic);
//        System.out.println(rank);
//        System.out.println(policeStation);
//
//        Policeman policeman = new Policeman(name, policeId, nic, rank, policeStation);
//        policeman.policemanAdded();
//
//        out.write(jsonObject.toString());
//        out.close();
//    }

    // Policeman sign up
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Get request parameters
        String policeId = req.getParameter("police_id");
        String name = req.getParameter("name");
        String nic = req.getParameter("nic");
        String email = req.getParameter("email");
        String mobileNo = req.getParameter("mobile_no");
        String password = req.getParameter("password");

        PolicemanService policemanService = new PolicemanService();
        Policeman policeman = policemanService.getPolicemanSignedUp(policeId, name, nic, email, mobileNo, password);

        // Output response
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        out.write(new Gson().toJson(policeman));
        out.close();
    }

    // IGP add rank and station to policeman
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPut(req, resp);
        // Get request parameters
        String policeId = req.getParameter("police_id");
        PoliceRank policeRank = PoliceRank.valueOf(req.getParameter("police_rank"));
        String policeStation = req.getParameter("police_station");

        PolicemanService policemanService = new PolicemanService();
        Policeman policeman = policemanService.updatePolicemanRankStation(policeId, policeRank, policeStation);
//        Policeman policeman = policemanService.updatePolicemanRankStation(policeId, policeStation);

        // output Response
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        out.write(new Gson().toJson(policeman));
        out.close();
    }

//    // Police Login
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        // Get request parameters
//        PoliceRank policeRank = PoliceRank.fromId(Integer.parseInt(req.getParameter("police_rank")));
//        String policeId = req.getParameter("police_id");
//        String password = req.getParameter("password");
//
//        PolicemanService policemanService = new PolicemanService();
//        Policeman policeman = policemanService.getPolicemanInfo(policeRank, policeId, password);
//
//        // Output response
//        PrintWriter out = resp.getWriter();
//        resp.setContentType("application/json");
//        resp.setCharacterEncoding("utf-8");
//
//        out.write(new Gson().toJson(policeman));
//        out.close();
//    }
}
