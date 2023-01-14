package com.cops.ntsf.controller;

import com.cops.ntsf.service.AuthService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Get request parameters
        String nic = req.getParameter("nic");
        String password = req.getParameter("password");

        AuthService authService = new AuthService();

        // Output response
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        out.write(authService.verifyLogin(nic, password));
        out.close();
    }
}