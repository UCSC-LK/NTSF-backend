package com.cops.ntsf.controller;

import com.cops.ntsf.model.User;
import com.cops.ntsf.service.UserService;
import com.cops.ntsf.util.PasswordHashUtil;
import com.cops.ntsf.util.Validator;
import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserSignUpServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        // Get request parameters
        String nic = req.getParameter("nic");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String mobileNo = req.getParameter("mobile_no");

        String hashedPassword;
        try {
            hashedPassword = PasswordHashUtil.hashingPassword(password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Validator validator = new Validator();
        int validateStatusCode = validator.validateParams(nic, password, email, mobileNo);

        switch (validateStatusCode) {
            case 0:
                UserService userService = new UserService();
                User user = userService.getUserSignedUp(nic, hashedPassword, email, mobileNo);

                // Output response
                PrintWriter out = resp.getWriter();
                resp.setContentType("application/json");
                resp.setCharacterEncoding("utf-8");

                out.write(new Gson().toJson(user));
                out.close();
            case 1:
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Incorrect NIC Number");
                break;
            case 2:
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Incorrect Password Format");
                break;
            case 3:
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Incorrect Email");
                break;
            case 4:
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Incorrect Mobile Number");
                break;
            default:
                break;
        }
    }
}
