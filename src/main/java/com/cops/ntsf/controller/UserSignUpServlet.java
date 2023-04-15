package com.cops.ntsf.controller;

import com.cops.ntsf.model.User;
import com.cops.ntsf.service.UserService;
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
        String email = req.getParameter("email");
        String mobileNo = req.getParameter("mobile_no");
        String password = req.getParameter("password");

        User user = null;
        if (checkValidations(nic, email, mobileNo, password)) {
            UserService userService = new UserService();
            user = userService.getUserSignedUp(nic, email, mobileNo, password);
        }

        // Output response
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        out.write(new Gson().toJson(user));
        out.close();
    }

    private boolean checkValidations(String nic, String email, String mobileNo, String password) {

        Validator validator = new Validator();

        if (validator.checkNICValidation(nic)) {
            validator.checkEmailValidation(email);
            {
                validator.checkMobileNoValidation(mobileNo);
                {
                    validator.checkPasswordValidation(password);
                }
            }
            return true;
        }
        return false;
    }
}
