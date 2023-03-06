package com.cops.ntsf.controller;

import org.json.JSONObject;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

public class PolicemanServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Came until the doPOST in Policeman Servlet");
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
                String authorizedPosition = payloadJsonObject.getString("position");
                if (authorizedRank == "policeman") {
                    if (authorizedPosition == "trafficPolice") {
                        if (action.equals("addFine")) {
                            new FineServlet().addFine(request, response);
                        } else {
                            System.out.println("You are not authorized to access this page, Only trafficPolice are allowed to access this page");
                        }
                    } else if (authorizedPosition == "investigationOfficer") {
                        if (action.equals("viewComplaintsAsInvestigationOfficer")) {
                            new ComplaintServlet().viewComplaintsAsInvestigationOfficer(request, response);
                        } else {
                            System.out.println("You are not authorized to access this page. Only investigationOfficer are allowed to access this page");
                        }
                    } else if (authorizedPosition == "courtSeargent") {
                        if (action.equals("addComplaint")) {
//                            new ComplaintServlet().addComplaint(request, response);
                        } else {
                            System.out.println("You are not authorized to access this page. Only courtSeargent are allowed to access this page");
                        }
                    } else {
                        System.out.println("You are not authorized to access this page. Only Policemen are allowed to access this page");
                    }
                }
            }
        }
        else {
            System.out.println("JWT signature verification failed");
        }
    }


}
