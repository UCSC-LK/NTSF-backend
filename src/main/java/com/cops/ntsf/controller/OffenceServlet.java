package com.cops.ntsf.controller;

import com.cops.ntsf.model.Offence;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.server.ServerCloneException;
import java.util.Base64;

public class OffenceServlet extends HttpServlet {

    protected void addOffence(HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            JSONObject jsonObject = new JSONObject();

//            int Offence_no = Integer.parseInt(request.getParameter("Offence_no"));
            String offence_noString = request.getParameter("offence_no");
            int offence_no = 0;
            if (offence_noString.matches("\\d+")) {
                offence_no = Integer.parseInt(offence_noString);
            } else {
                // Handle the case where the parameter contains non-numeric characters
                // e.g., show an error message to the user
            }

            String offence_type = request.getParameter("offence_type");
            String description = request.getParameter("description");
            int amount = Integer.parseInt(request.getParameter("amount"));
            int demerit_points = Integer.parseInt(request.getParameter("demerit_points"));

            System.out.println("Add offence method is called in the offence servlet");

            System.out.println(offence_no);
            System.out.println(offence_type);
            System.out.println(description);
            System.out.println(amount);
            System.out.println(demerit_points);

            if (checkValidations(offence_no, offence_type, description, amount, demerit_points)) {
//                jsonObject.put("status", "success");
//                jsonObject.put("message", "Offence added successfully");
            } else {
//                jsonObject.put("status", "error");
//                jsonObject.put("message", "Error occurred while adding offence");
            }

            out.print(jsonObject.toString());
            out.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private boolean checkValidations(int offence_no, String offence_type, String description, int amount, int demerit_points) {
        boolean flagOffence_no = false;
        boolean flagOffence_type = false;
        boolean flagDescription = false;
        boolean flagAmount = false;
        boolean flagDemerit_points = false;
        boolean flag = false;

        System.out.println("Check validations method is called in the offence servlet");

        if(offence_no <= 0)
        {
            flagOffence_no = false;
        }
        else if(offence_no >= 100)
        {
            flagOffence_no = false;
        }
        else
        {
            flagOffence_no = true;
        }

        if(offence_type == null)
        {
            flagOffence_type = false;
        }
        else if(offence_type.length() > 10)
        {
            flagOffence_type = false;
        }
        else
        {
            flagOffence_type = true;
        }

        if (description == null)
        {
            flagDescription = false;
        }
        else if(description.length() > 200)
        {
            flagDescription = false;
        }
        else
        {
            flagDescription = true;
        }

        if (amount <= 0)
        {
            flagAmount = false;
        }
        else if(amount >= 100000)
        {
            flagAmount = false;
        }
        else
        {
            flagAmount = true;
        }

        if (demerit_points <= 0)
        {
            flagDemerit_points = false;
        }
        else if(demerit_points >= 4)
        {
            flagDemerit_points = false;
        }
        else
        {
            flagDemerit_points = true;
        }

        if(flagOffence_no && flagOffence_type && flagDescription && flagAmount && flagDemerit_points)
        {
            flag = true;
        }
        else
        {
            flag = false;
        }
        return flag;
    }

    protected void viewOffence(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        System.out.println("View offence method is called in the offence servlet");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("serverResponse", "Allowed");

        Offence offence = new Offence();
        JSONArray offenceList = offence.getOffenceDetails();

        jsonObject.put("List", offenceList);
        out.write(jsonObject.toString());
        out.close();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Came until the doPost method in Offence Servlet");

        String action = request.getParameter("action");
        String contentType = request.getHeader("Content-type");
        String authorizationHeader = request.getHeader("Authorization");
        System.out.println("Authorization Header: " + authorizationHeader);
        System.out.println("Content Type: " + contentType);
        System.out.println("Action: " + action);

        String jwt = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7); //Extracts the jwt token removing the word Bearer prefix
            System.out.println(jwt);

            String[] jwtParts = jwt.split("\\.");
            String headerJson = new String(Base64.getUrlDecoder().decode(jwtParts[0]));
            String payloadJson = new String(Base64.getUrlDecoder().decode(jwtParts[1]));
            System.out.println(headerJson);
            System.out.println(payloadJson);


            String signature = jwtParts[2];
            String unsignedJwt = jwtParts[0] + "." + jwtParts[1];

            String calculatedSignature;
            try {
                Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
                SecretKeySpec secret_key = new SecretKeySpec("mysecret".getBytes(), "HmacSHA256");
                sha256_HMAC.init(secret_key);

                calculatedSignature = Base64.getUrlEncoder().encodeToString(sha256_HMAC.doFinal(unsignedJwt.getBytes()));

            } catch (Exception e) {
                throw new RuntimeException("Failed to calculate HMAC: " + e.getMessage());
            }

            if (!signature.equals(calculatedSignature)) {
                throw new RuntimeException("JWT signature verification failed as the signature is not matching");
            } else {
                System.out.println("JWT signature verification success");
                JSONObject payloadJsonObject = new JSONObject(payloadJson);
                String authorizedRank = payloadJsonObject.getString("rank");
                System.out.println(authorizedRank);
                if (authorizedRank.equals("igp")) {
                    if (action.equals("addOffence")) {
                        System.out.println("Redirecting to addOffence in Offence Servlet");
                        addOffence(request, response);
                    } else if (action.equals("viewOffence")) {
                        System.out.println("Redirecting to viewPoliceman in Policeman Servlet");
                        viewOffence(request, response);
                    }
                    else{
                        System.out.println("Invalid action"); //Could be changed later
                    }
                }
                else {
                    System.out.println("You are not authorized to access this page");
                }
            }

        }
        else {
            System.out.println("JWT signature verification failed");
        }

    }
}