package com.cops.ntsf.controller;

import com.cops.ntsf.constants.UserType;
import com.cops.ntsf.model.Auth;
import com.cops.ntsf.model.Driver;
import com.cops.ntsf.model.User;
import com.cops.ntsf.service.AuthService;
import com.cops.ntsf.service.DriverService;
import com.cops.ntsf.service.UserService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SignUpServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get request parameters
        UserType userType = UserType.values()[Integer.parseInt(req.getParameter("user_type"))];

        String nic = req.getParameter("nic");
        String email = req.getParameter("email");
        String mobileNo = req.getParameter("mobile_no");

        String password = req.getParameter("password");

        String licenceNo = null;

        switch (userType) {
            case DRIVER:
                licenceNo = req.getParameter("licence_no");
        }

        UserService userService = new UserService();
        User user = userService.getUserSignedUp(userType, nic, email, mobileNo);

        DriverService driverService = new DriverService();
        Driver driver = driverService.getDriverSignedUp(licenceNo);

        AuthService authService = new AuthService();
        Auth auth = authService.getAuthSignedUp(password);

        // Output response
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        out.write(new Gson().toJson(user));
        out.close();

        out.write(new Gson().toJson(driver));
        out.close();

        out.write(new Gson().toJson(auth));
        out.close();
    }
}
