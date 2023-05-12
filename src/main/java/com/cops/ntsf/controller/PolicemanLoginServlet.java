package com.cops.ntsf.controller;

import com.cops.ntsf.constants.AuthType;
import com.cops.ntsf.model.PoliceStation;
import com.cops.ntsf.model.Policeman;
import com.cops.ntsf.model.PolicemanAuth;
import com.cops.ntsf.service.Email;
import com.cops.ntsf.util.JwtUtils;
import com.cops.ntsf.util.OTPGenerator;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import static com.cops.ntsf.util.PasswordHashUtil.hashingPassword;

public class PolicemanLoginServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Came until the doPOST in PolicemanLoginServlet");
        String action = request.getParameter("action");
        System.out.println("Action: " + action);
        if (action.equals("login")) {
            login(request, response);
        } else if (action.equals("checkLoginUsername")) {
            checkLoginUsername(request, response);
        } else if (action.equals("changeFirstTimePassword")) {
            changeFirstTimePassword(request, response);
        } else if (action.equals("sendOTP")) {
            System.out.println("Redirecting to sendOTP");
            sendOTP(request, response);
        } else if (action.equals("verifyOTP")) {
            //verifyOTP(request, response);
        } else {
            System.out.println("Invalid action");
        }
    }

//    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = request.getParameter("action");
//
//        if (action.equals("sendOTP")) {
//            sendOTP(request, response);
//        } else {
//            System.out.println("Invalid action");
//        }
//    }

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            JSONObject jsonObject = new JSONObject();
            JSONArray loginResponse = new JSONArray();

            String police_id = request.getParameter("username");
            String password = request.getParameter("password");

            String hashedPassword = hashingPassword(password);

            System.out.println("Works until login servlet");

            System.out.println(police_id);
            System.out.println(password);
            System.out.println(hashedPassword);

            boolean firsTimeLogin = checkFirstTimeLogin(police_id);
            if (firsTimeLogin == true) {
                System.out.println("First time login");
                jsonObject.put("firstTimeLogin", true);
                Policeman policeman = new Policeman();
                loginResponse = policeman.loginFirstTime(police_id, hashedPassword, true);
            } else {
                System.out.println("Not first time login");
                jsonObject.put("firstTimeLogin", false);
                Policeman policeman = new Policeman();
                loginResponse = policeman.loginFirstTime(police_id, hashedPassword, false);
            }

            //Printing the login response in the servlet
            System.out.println("Printing the loginResponse in the servlet\n");
            System.out.println(loginResponse);

            boolean loginStatus = loginResponse.getJSONObject(0).getBoolean("authorization");
            System.out.println(loginStatus);

            if (loginStatus) {
                System.out.println("Login Successful and jwt token is being generated\n");
                JwtUtils jwtUtils = new JwtUtils(AuthType.POLICEMAN, loginResponse);
                jsonObject.put("jwt", jwtUtils.generateJwt()); //Adding the jwt token to the json object

            } else {
                System.out.println("Login Unsuccessful");
                jsonObject.put("jwt", "LoginUnsuccessful"); //To redirect to the login page at front end
            }

            System.out.println("Finally Printing the json object in the servlet\n");
            System.out.println(jsonObject);
            out.write(jsonObject.toString());
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void checkLoginUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            JSONObject jsonObject = new JSONObject();

            String police_id = request.getParameter("username");
            System.out.println(police_id);
            System.out.println("Came until checkLoginUsername duplication in servlet");

            Policeman policeman = new Policeman();
            jsonObject.put("alert", policeman.LoginUsernameCheck(police_id));

            out.write(jsonObject.toString());
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void changeFirstTimePassword(HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            JSONObject jsonObject = new JSONObject();

            String police_id = request.getParameter("username");
            String newpassword = request.getParameter("newpassword");

            String hashedPassword = hashingPassword(newpassword);

            PolicemanAuth policemanAuth = new PolicemanAuth();
            boolean alert = policemanAuth.changeFirstTimePassword(police_id, hashedPassword);

            jsonObject.put("alert", alert);

            out.write(jsonObject.toString());
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    protected boolean checkFirstTimeLogin(String police_id) {
        PolicemanAuth policemanAuth = new PolicemanAuth();
        System.out.println("Came until checkFirstTimeLogin in servlet");
        System.out.println(police_id + " is the police id");
        return policemanAuth.checkFirstLoginOrNot(police_id); //return true if it is a first time login, else false
    }

    protected void sendOTP(HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            JSONObject jsonObject = new JSONObject();

            String police_id = request.getParameter("police_id");

            PolicemanAuth policemanAuth = new PolicemanAuth();
            String otp = OTPGenerator.generateOTP();
            System.out.println("OTP is " + otp);
            boolean alert = policemanAuth.sendOTP(police_id, otp);
            System.out.println("Alert is " + alert);

            if (alert) {
                Email email = new Email();
                email.sendMail(getEmailForOTP(police_id), "OTP - National Traffic Spot Fine System", otp);
            }
            else {
                System.out.println("OTP not sent");
            }
            System.out.println("Alert is " + alert);
            jsonObject.put("serverResponse", "Allowed");
            jsonObject.put("alert", alert);

            out.write(jsonObject.toString());
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getEmailForOTP(String police_id) {
        Policeman policeman = new Policeman();
        return policeman.gettingEmailForOTP(police_id);
    }



}
