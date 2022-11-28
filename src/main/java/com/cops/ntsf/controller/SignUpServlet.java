package com.cops.ntsf.controller;

import com.cops.ntsf.constants.UserType;
import com.cops.ntsf.model.User;
import com.cops.ntsf.service.UserService;
import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SignUpServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Get request parameters
        UserType userType = UserType.fromId(Integer.parseInt(req.getParameter("user_type")));
        String nic = req.getParameter("nic");
        String email = req.getParameter("email");
        String mobileNo = req.getParameter("mobile_no");
        String password = req.getParameter("password");

        String loginId = null;

        switch (userType) {
            case DRIVER:
                loginId = req.getParameter("licence_no");
        }

        UserService userService = new UserService();
        User user = userService.getUserSignedUp(userType, nic, email, mobileNo, password, loginId);

        // Output response
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        out.write(new Gson().toJson(user));
        out.close();
    }
}
