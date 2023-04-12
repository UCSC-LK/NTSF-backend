package com.cops.ntsf.controller;

import com.cops.ntsf.model.Fine;
import com.cops.ntsf.model.Offence;
import com.google.gson.Gson;
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
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;

public class FineServlet extends HttpServlet {
    protected void addFine(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //Fine Number is auto incremented
            String fineType = request.getParameter("fine_type");
            //Front end sends user_id as nic/licenseNo/vehicleNo depending on the fine type
            String offenceNo = request.getParameter("offence_no");
            String spotDescription = request.getParameter("spot_description");
            LocalDateTime imposedDateTime = LocalDateTime.now();
            LocalDateTime dueDateTime = imposedDateTime.plusDays(14);  //Adds 14 days to the imposed date
            String policeId = request.getParameter("police_id");
            String policeStation = request.getParameter("police_station");
            //Fine amount retrieved from the offence table using offence_no
            //Payment status is set to unpaid by default at database level

            System.out.println("Fine type: " + fineType);
            System.out.println("Offence no: " + offenceNo);
            System.out.println("Spot description: " + spotDescription);
            System.out.println("Police id: " + policeId);
            System.out.println("Police station: " + policeStation);
            System.out.println("Imposed date time: " + imposedDateTime);
            System.out.println("Due date time: " + dueDateTime);


            if (checkValidations(fineType, offenceNo, spotDescription, policeId, policeStation)) {
                if (fineType.equals("pedestrian")) {
                    String nic = request.getParameter("user_id");
                    if (checkNICValidations(nic)) {
                        String licenseNo = "null";
                        String vehicleNo = "null";
                        String drivenVehicleNo = "null";
                        Fine fine = new Fine(fineType, offenceNo, nic, licenseNo, vehicleNo, drivenVehicleNo, spotDescription, imposedDateTime, dueDateTime, policeId, policeStation);
                        fine.createFine();
                        System.out.println("reached");
                    } else {
                        System.out.println("Invalid NIC");
                    }
                } else if (fineType.equals("vehicle")) {
                    String vehicleNo = request.getParameter("user_id");

                    if (checkVehicleNoValidations(vehicleNo)) {
                        String nic = getNICByVehicleNo(vehicleNo);
                        System.out.println("NIC: " + nic);
                        String licenseNo = "null";
                        String drivenVehicleNo = "null";
                        Fine fine = new Fine(fineType, offenceNo, nic, licenseNo, vehicleNo, drivenVehicleNo, spotDescription, imposedDateTime, dueDateTime, policeId, policeStation);
                        fine.createFine();
                    } else {
                        System.out.println("Invalid vehicle number");
                    }

                } else if (fineType.equals("driver")) {
                    String licenseNo = request.getParameter("user_id");
                    String drivenVehicleNo = request.getParameter("driven_vehicle");

                    if (checkLicenseNoValidations(licenseNo)) {
                        String nic = getNICByLicenseNo(licenseNo);
                        System.out.println("NIC: " + nic);
                        String vehicleNo = "null";
                        Fine fine = new Fine(fineType, offenceNo, nic, licenseNo, vehicleNo, drivenVehicleNo, spotDescription, imposedDateTime, dueDateTime, policeId, policeStation);
                        fine.createFine();
                    }

                } else {
                    System.out.println("Invalid fine type");
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getNICByVehicleNo(String vehicleNo) {
        return "123456789V";
    }

    private String getNICByLicenseNo(String vehicleNo) {
        return "123456789V";
    }

    private boolean checkNICValidations(String nic) {
        return true;
    }

    private boolean checkVehicleNoValidations(String vehicleNo) {
        return true;
    }

    private boolean checkLicenseNoValidations(String licenseNo) {
        return true;
    }


    protected void viewFineAsOIC(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("serverResponse", "Allowed");

        Fine fine = null;
        fine = new Fine(fine.getNic());
        JSONArray fineListAsOIC = fine.getFineListAsOIC();

        jsonObject.put("List", fineListAsOIC);

        out.println(jsonObject.toString());
        out.close();

    }

    private boolean checkValidations(String fineType, String offenceNo, String spotDescription, String policeId, String policeStation) {
        boolean flagFineType = false; // flag = true means validation is passed
        boolean flagOffenceNo = false;
        boolean flagSpotDescription = false;
        boolean flagPoliceId = true; //made true temporarily ************************
        boolean flagPoliceStation = true; //made true temporarily********************
        boolean flag = false;

        if (fineType.equals("pedestrian") || fineType.equals("vehicle") || fineType.equals("driver")) {
            flagFineType = true;
            System.out.println("Valid fine type");
        } else {
            System.out.println("Invalid fine type");
            flagFineType = false;
        }

        if (offenceNo == null) {
            flagOffenceNo = false;
        } else if (offenceNo.length() > 3) {
            flagOffenceNo = false;
            System.out.println("Offence number should be less than or equal to 3 characters");
        } else if (!offenceNo.matches("[0-9]+")) {
            flagOffenceNo = false;
            System.out.println("Offence number should be a number");
        } else {
            System.out.println("Valid offence number");
            flagOffenceNo = true;
        }

        if (spotDescription == null) {
            flagSpotDescription = false;
        } else if (spotDescription.length() > 200) {
            flagSpotDescription = false;
            System.out.println("Spot description should be less than or equal to 200 characters");
        } else {
            System.out.println("Valid spot description");
            flagSpotDescription = true;
        }


        System.out.println("Checking validations in FIne Servlet");
        if (flagFineType && flagOffenceNo && flagSpotDescription && flagPoliceId && flagPoliceStation) {
            flag = true;
            System.out.println("All Validations passed");
        } else {
            flag = false;
        }
        return flag;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Came until the doPOST in Fine Servlet");
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
                System.out.println(authorizedRank);
                if (authorizedRank.equals("policeman")) {
                    if (authorizedPosition.equals("trafficPolice")) {
                        if (action.equals("addFine")) {
                            addFine(request, response);
                        }
                    } else {
                        System.out.println("You are not authorized to access this page");
                    }
                } else {
                    System.out.println("You are not authorized to access this page");
                }
            }

        } else {
            System.out.println("JWT signature verification failed");
        }

    }

    /*
    @ View fines in user side
    */
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        // Get request parameters
        String nic = req.getParameter("nic");
        String offence_no = req.getParameter("offence_no");

        Offence offence = new Offence(offence_no);
//        offence.getOffenceType();

        String offence_type = offence.getOffence_type();

        ArrayList<Fine> finesList;

        Fine fine = new Fine(nic, offence_type);
        try {
            finesList = fine.getUserFinesInfo();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Output response
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        out.write(new Gson().toJson(finesList));
        out.close();
    }
}