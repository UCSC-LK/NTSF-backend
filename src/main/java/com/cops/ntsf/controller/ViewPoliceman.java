//package com.cops.ntsf.controller;
//
//import com.cops.ntsf.model.Policeman;
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//public class ViewPoliceman extends HttpServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        PrintWriter out = response.getWriter();
//        response.setContentType("text/html");
//
//        HttpSession session = request.getSession(false);
//
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("serverResponse", "Allowed");
//
//        Policeman policeman = new Policeman();
//        JSONArray policemanList = policeman.getPolicemanDetails();
//
//        jsonObject.put("List", policemanList );
//
//        out.write(jsonObject.toString());
//        out.close();
//
//    }
//
//}
