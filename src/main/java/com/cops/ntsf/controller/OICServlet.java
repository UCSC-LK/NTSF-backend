package com.cops.ntsf.controller;

import com.cops.ntsf.model.Policeman;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;

public class OICServlet extends HttpServlet {

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
                System.out.println(authorizedRank);
                if (authorizedRank.equals("oic")) {
                   if (action.equals("viewPoliceman")) {
                       System.out.println("Redirecting to viewPoliceman in OIC Servlet");
                       System.out.println("Police Station: " + request.getParameter("police_station"));
                       viewPoliceman(request, response);
                   }
                   else if (action.equals("updatePosition"))
                   {
                       System.out.println("Redirecting to editPosition in OIC Servlet");
                       System.out.println("Position: " + request.getParameter("position"));
                       updatePosition(request, response);
                   }
                } else if(authorizedRank.equals("policeman"))
                {
                    System.out.println("Redirecting to viewPoliceman in Policeman Servlet");
                    System.out.println("Police Station: " + request.getParameter("police_station"));
                    viewPoliceman(request, response);
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

    private void updatePosition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            System.out.println("Came until the editPosition method in OIC Servlet");
            String position = request.getParameter("position");
            String police_id = request.getParameter("police_id");
            System.out.println("Position: " + position);
            System.out.println("Policeman ID: " + police_id);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("serverResponse", "Allowed");

            Policeman policeman = new Policeman();
            policeman.positionEdited(position, police_id);

            out.write(jsonObject.toString());
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void viewPoliceman(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            System.out.println("Came until the viewPoliceman method in OIC Servlet");
            String police_station = request.getParameter("police_station");
            System.out.println("Police Station: " + police_station);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("serverResponse", "Allowed");

            Policeman policeman = new Policeman();
            JSONArray policemanList = policeman.getPolicemanDetailsAsOIC(police_station);

            jsonObject.put("List", policemanList);
            out.write(jsonObject.toString());
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

