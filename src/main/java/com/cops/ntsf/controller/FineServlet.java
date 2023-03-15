package com.cops.ntsf.controller;

import com.cops.ntsf.model.Fine;
import org.json.JSONObject;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;

public class FineServlet extends HttpServlet {
    protected void addFine(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            //Fine Number is auto incremented
            String fineType = request.getParameter("fine_type");
            //Front end sends user_id as nic/licenseNo/vehicleNo
            String drivenVehicle = request.getParameter("driven_vehicle");
            String offenceNo = request.getParameter("offence_no");
            String spotDescription = request.getParameter("spot_description");
            LocalDateTime imposedDateTime = LocalDateTime.now();
            LocalDateTime dueDateTime = imposedDateTime.plusDays(14);  //Adds 14 days to the imposed date
            String policeId = request.getParameter("police_id");
            String policeStation = request.getParameter("police_station");
            //Fine amount retrieved from the offence table using offence_no
            //Payment status is set to unpaid by default at database level

            if(checkValidations(fineType, offenceNo, spotDescription, policeId, policeStation)){
                if (fineType.equals("pedestrian")){
                    String nic = request.getParameter("user_id");
                    if (checkNICValidations(nic))
                    {
                        String licenseNo  = "null";
                        String vehicleNo = "null";
                        Fine fine = new Fine(fineType, offenceNo, nic, licenseNo, vehicleNo, spotDescription, imposedDateTime, dueDateTime, policeId, policeStation);
                        fine.createFine();
                    }
                    else {
                        System.out.println("Invalid NIC");
                    }
                }
                else if (fineType.equals("vehicle")){
                    String vehicleNo = request.getParameter("user_id");

                    if(checkVehicleNoValidations(vehicleNo)){
                        String nic = getNICByVehicleNo(vehicleNo);
                        String licenseNo = "null";
                        Fine fine = new Fine(fineType, offenceNo, nic, licenseNo, vehicleNo, spotDescription, imposedDateTime, dueDateTime, policeId, policeStation);
                    }
                    else {
                        System.out.println("Invalid vehicle number");
                    }

                }
                else if (fineType.equals("driver")){
                    String licenseNo = request.getParameter("user_id");

                    if (checkLicenseNoValidations(licenseNo)){
                        String nic = getNICByLicenseNo(licenseNo);
                        String vehicleNo = "null";
                        Fine fine = new Fine(fineType, offenceNo, nic, licenseNo, vehicleNo, spotDescription, imposedDateTime, dueDateTime, policeId, policeStation);

                    }

                }
                else{
                    System.out.println("Invalid fine type");
                }
            }


        } catch (Exception e){
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


    private boolean checkValidations(String fineType, String offenceNo, String spotDescription, String policeId, String policeStation) {
        boolean flagFineType = false; // flag = true means validation is passed
        boolean flagOffenceNo = false;
        boolean flagSpotDescription = false;
        boolean flagPoliceId = false;
        boolean flagPoliceStation = false;
        boolean flag = false;

        if (fineType.equals("pedestrian") || fineType.equals("vehicle") || fineType.equals("driver")){
            flagFineType = true;
        }
        else{
            System.out.println("Invalid fine type");
            flagFineType = false;
        }

        if (offenceNo == null){
            flagOffenceNo = false;
        }
        else if (offenceNo.length() > 3)
        {
            flagOffenceNo = false;
            System.out.println("Offence number should be less than or equal to 3 characters");
        }
        else if (!offenceNo.matches("[0-9]+")){
            flagOffenceNo = false;
            System.out.println("Offence number should be a number");
        }
        else{
            System.out.println("Valid offence number");
            flagOffenceNo = false;
        }

        if (spotDescription == null){
            flagSpotDescription = false;
        }
        else if (spotDescription.length() > 200){
            flagSpotDescription = false;
            System.out.println("Spot description should be less than or equal to 200 characters");
        }
        else{
            System.out.println("Valid spot description");
            flagSpotDescription = true;
        }



        System.out.println("Checking validations in FIne Sev");
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
                    if(authorizedPosition.equals("trafficPolice")){
                        if(action.equals("addFine")){
                            addFine(request, response);
                        }
                    }
                    else {
                        System.out.println("You are not authorized to access this page");
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
