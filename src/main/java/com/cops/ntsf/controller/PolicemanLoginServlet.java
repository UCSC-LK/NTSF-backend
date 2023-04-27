package com.cops.ntsf.controller;

import com.cops.ntsf.constants.AuthType;
import com.cops.ntsf.model.Policeman;
import com.cops.ntsf.util.JwtUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import static com.cops.ntsf.util.PasswordHashUtil.hashingPassword;

public class PolicemanLoginServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action.equals("login")) {
            login(request, response);
        } else if (action.equals("checkLoginUsername")) {
            checkLoginUsername(request, response);
        }

    }


    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            JSONObject jsonObject = new JSONObject();

            String police_id = request.getParameter("username");
            String password = request.getParameter("password");

            String hashedPassword = hashingPassword(password);

            System.out.println("Works until login servlet");

            System.out.println(police_id);
            System.out.println(password);
            System.out.println(hashedPassword);
            Policeman policeman = new Policeman();
            JSONArray loginResponse = policeman.login(police_id, hashedPassword);

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

            HttpSession session = request.getSession();
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

}
