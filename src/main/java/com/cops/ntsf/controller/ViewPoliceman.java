//package com.cops.ntsf.controller;
//
//import com.cops.ntsf.dao.PolicemanDAO;
//import com.cops.ntsf.model.Policeman;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//public class ViewPoliceman extends HttpServlet {
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        List<Policeman> policemanDetails = PolicemanDAO.viewPoliceman();
//        RequestDispatcher dis = request.getRequestDispatcher("policeman.jsp");
//        dis.forward(request, response);
//
//    }
//
//}
