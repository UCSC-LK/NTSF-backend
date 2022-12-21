//package com.cops.ntsf.controller;
//
//import com.cops.ntsf.dao.ComplaintDAO;
//import com.cops.ntsf.model.Complaint;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class ViewComplaint extends HttpServlet {
//    protected void doget(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////        String title = request.getParameter("title");
////        String description = request.getParameter("description");
////        String complaint_No;
////        String user_id;
//
//        Complaint complaintDetails = ComplaintDAO.viewComplaint();
//        request.setAttribute("complaintDetails", complaintDetails);
//        RequestDispatcher dis = request.getRequestDispatcher("complaints.jsp");
//        dis.forward(request, response);
//
//        System.out.println(complaintDetails.toString());
//    }
//}