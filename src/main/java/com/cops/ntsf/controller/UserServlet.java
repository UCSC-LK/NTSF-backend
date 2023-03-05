package com.cops.ntsf.controller;

import com.cops.ntsf.model.User;
import com.cops.ntsf.service.UserService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Get request parameters
//        UserType userType = UserType.fromId(Integer.parseInt(req.getParameter("user_type")));
//        String userId = req.getParameter("user_id");
        String nic = req.getParameter("nic");

        UserService userService = new UserService();
//        User user = userService.getUserInfo(userId);
        User user = userService.getUserInfo(nic);

        // Output response
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        out.write(new Gson().toJson(user));
        out.close();
    }


//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        // Get request parameters
//        String userId = req.getParameter("user_id");
////        UserType userType = UserType.fromId(Integer.parseInt(req.getParameter("user_type")));
//        String mobileNo = req.getParameter("mobile_no");
//        String email = req.getParameter("email");
//        String name = req.getParameter("name");
//        String address = req.getParameter("address");
//
//        UserService userService = new UserService();
//        User user = userService.updateUserInfo(userId, mobileNo, email, name, address);
//
//        // Output response
//        PrintWriter out = resp.getWriter();
//        resp.setContentType("application/json");
//        resp.setCharacterEncoding("utf-8");
//
//        out.write(new Gson().toJson(user));
//        out.close();
//    }
}