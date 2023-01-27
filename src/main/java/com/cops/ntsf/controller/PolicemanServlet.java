package com.cops.ntsf.controller;
import com.cops.ntsf.model.Policeman;
import com.cops.ntsf.service.PolicemanService;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class PolicemanServlet extends HttpServlet {
    // IGP Add policeman to the system
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        JSONObject jsonObject = new JSONObject();

        String name = request.getParameter("name");
        String policeId = request.getParameter("police_id");
        String nic = request.getParameter("nic");
        String rank = request.getParameter("rank");
        String policeStation = request.getParameter("police_station");

        System.out.println("Works until Servlet");
        System.out.println(name);
        System.out.println(policeId);
        System.out.println(nic);
        System.out.println(rank);
        System.out.println(policeStation);

        Policeman policeman = new Policeman(name, policeId, nic, rank, policeStation);
        policeman.policemanAdded();

        out.write(jsonObject.toString());
        out.close();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPut(req, resp);
        // Get request parameters
        String policeId = req.getParameter("police_id");
        String rank = req.getParameter("rank");

        PolicemanService policemanService = new PolicemanService();
        Policeman policeman = policemanService.updatePolicemanRank(policeId, rank);

        // output Response
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

//        out.write(new Gson().toJson(policeman));
        out.close();
    }
}
