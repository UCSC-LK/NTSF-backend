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
import java.util.Base64;

public class OffenceServlet extends HttpServlet {

    protected void addOffence(HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            JSONObject jsonObject = new JSONObject();
            String amountString = request.getParameter("amount");
            int amount = 0;
            if (amountString.matches("\\d+")) {
                amount = Integer.parseInt(amountString);
            } else {
                // Handle the case where the parameter contains non-numeric characters
                // e.g., show an error message to the user
            }

            String demerit_pointsString = request.getParameter("demerit_point");
            int demerit_points = 0;
            if (demerit_pointsString.matches("\\d+")) {
                demerit_points = Integer.parseInt(demerit_pointsString);
            } else {
                // Handle the case where the parameter contains non-numeric characters
                // e.g., show an error message to the user
            }
            int offence_no = Integer.parseInt(request.getParameter("offence_no"));
            String offence_type = request.getParameter("offence_type");
            String description = request.getParameter("description");
            amount = Integer.parseInt(request.getParameter("amount"));
//            demerit_points = Integer.parseInt(request.getParameter("demerit_point"));

            System.out.println("Add offence method is called in the offence servlet");

            System.out.println("offence_no " + offence_no);
            System.out.println(offence_type);
            System.out.println(description);
            System.out.println(amount);
            System.out.println(demerit_points);

            if (checkValidations(offence_no, offence_type, description, amount, demerit_points)) {
//                jsonObject.put("status", "success");
//                jsonObject.put("message", "Offence added successfully");

                Offence offence = new Offence(offence_no, offence_type, description, amount, demerit_points);
                offence.offenceAdded();

            } else {
//                jsonObject.put("status", "error");
//                jsonObject.put("message", "Error occurred while adding offence");
            }

            out.print(jsonObject.toString());
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkValidations(int offence_no, String offence_type, String description, int amount, int demerit_points) {
        boolean flagOffence_no = true; //Made True temporarily
        boolean flagOffence_type = false;
        boolean flagDescription = false;
        boolean flagAmount = false;
        boolean flagDemerit_points = false;
        boolean flag = false;

        System.out.println("Check validations method is called in the offence servlet");

        if (offence_type == null) {
            flagOffence_type = false;
        } else if (offence_type.length() > 10) {
            flagOffence_type = false;
        } else {
            flagOffence_type = true;
        }

        if (description == null) {
            flagDescription = false;
        } else if (description.length() > 200) {
            flagDescription = false;
        } else {
            flagDescription = true;
        }

        if (amount <= 0) {
            flagAmount = false;
        } else if (amount >= 100000) {
            flagAmount = false;
        } else {
            flagAmount = true;
        }

        if (demerit_points <= 0) {
            flagDemerit_points = false;
        } else if (demerit_points >= 4) {
            flagDemerit_points = false;
        } else {
            flagDemerit_points = true;
        }

        if (flagOffence_type && flagDescription && flagAmount && flagDemerit_points) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }

    protected void viewOffenceByType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        System.out.println("View offence method is called in the offence servlet");

        String offenceType = request.getParameter("offence_type");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("serverResponse", "Allowed");

        Offence offence = new Offence();
        JSONArray offenceList = offence.getOffenceDetailsByType(offenceType);

        jsonObject.put("List", offenceList);
        out.write(jsonObject.toString());
        out.close();
    }

    protected void deleteOffence(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        System.out.println("Delete offence method is called in the offence servlet");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("serverResponse", "Allowed");

        int offence_no = Integer.parseInt(request.getParameter("offence_no"));
        Offence offence = new Offence();

        jsonObject.put("alert", offence.deleteOffenceDetails(offence_no));

        out.write(jsonObject.toString());
        out.close();
    }

    protected void checkOffenceDescription(HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            JSONObject jsonObject = new JSONObject();

            String description = request.getParameter("description");

            System.out.println("Check offence description method is called in the offence servlet");

            System.out.println(description);

            Offence offence = new Offence();
            jsonObject.put("alert", offence.offenceDescriptionCheck(description));

            out.print(jsonObject.toString());
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getOffenceNo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            System.out.println("Get offence no method is called in the offence servlet");

            JSONObject jsonObject = new JSONObject();

            String offence_type = request.getParameter("offence_type");
            System.out.println(offence_type);

            Offence offence = new Offence();
            jsonObject.put("offence_no", offence.OffenceNoGet(offence_type));
            System.out.println(offence.OffenceNoGet(offence_type));
            System.out.println(jsonObject.toString());

            out.print(jsonObject.toString());
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fetchOffence(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            System.out.println("Fetch offence method is called in the offence servlet");

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("serverResponse", "Allowed");

            int offence_no = Integer.parseInt(request.getParameter("offence_no"));
            System.out.println(offence_no);

            Offence offence = new Offence();
            JSONArray fetchedOffenceList = offence.fetchOffenceDetails(offence_no);

            jsonObject.put("List", fetchedOffenceList);

            out.write(jsonObject.toString());
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                System.out.println("Action: " + action);
                if (authorizedRank.equals("igp")) {
                    if (action.equals("addOffence")) {
                        addOffence(request, response);
                    } else if (action.equals("viewOffenceByType")) {
                        viewOffenceByType(request, response);
                    } else if (action.equals("deleteOffence")) {
                        deleteOffence(request, response);
                    } else if (action.equals("updateOffence")) {
                        //updateOffence(request, response);
                    } else if (action.equals("checkOffenceDescription")) {
                        checkOffenceDescription(request, response);
                    } else if (action.equals("getOffenceNo")) {
                        System.out.println("Redirecting to getOffenceNo in Offence Servlet");
                        getOffenceNo(request, response);
                    } else {
                        System.out.println("Invalid action"); //Could be changed later
                    }
                } else if (authorizedRank.equals("oic")) {
                    if (action.equals("viewOffenceByType")) {
                        System.out.println("Redirecting to viewOffenceByType in Offence Servlet");
                        viewOffenceByType(request, response);
                    } else {
                        System.out.println("Invalid action"); //Could be changed later
                    }

                } else if (authorizedRank.equals("policeman")) {
                    if (action.equals("viewOffenceByType")) {
                        viewOffenceByType(request, response);
                    } else {
                        System.out.println("Invalid action"); //Could be changed later
                    }
                } else {
                    System.out.println("You are not authorized to access this page");
                }
            }

        } else {
            System.out.println("JWT signature verification failed");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Came until the doGet method in Offence Servlet");

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
                System.out.println("Action: " + action);
                if (authorizedRank.equals("igp")) {
                    if (action.equals("fetchOffence")) {
                        fetchOffence(request, response);
                    } else {
                        System.out.println("Invalid action"); //Could be changed later
                    }
                } else if (authorizedRank.equals("oic")) {

                } else if (authorizedRank.equals("policeman")) {

                } else {
                    System.out.println("You are not authorized to access this page");
                }
            }
        }
    }
}

