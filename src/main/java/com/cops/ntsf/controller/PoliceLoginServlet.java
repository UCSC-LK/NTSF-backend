////package com.cops.ntsf.controller;
////
////import com.cops.ntsf.model.Policeman;
////import com.cops.ntsf.model.PolicemanLogin;
////
////import javax.servlet.ServletException;
////import javax.servlet.http.HttpServlet;
////import javax.servlet.http.HttpServletRequest;
////import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//public class PoliceLoginServlet extends HttpServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = request.getParameter("action");
//        if (action.equals("login")) {
//            login(request, response);
//        }
//    }
//
//    protected void login(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException{
//    try{
//        PrintWriter out = response.getWriter();
//        response.setContentType("text/html");
//
//        String police_id = request.getParameter("username");
//        String password = request.getParameter("password");
//
//        System.out.println("Works until login servlet");
//        System.out.println(police_id);
//        System.out.println(password);
//
//        PolicemanLogin policemanLogin = new PolicemanLogin(police_id, password);
//        policemanLogin.login();
//
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
//    }
//}
