package com.cops.ntsf.controller;

import com.cops.ntsf.model.Driver;
import com.cops.ntsf.model.Fine;
import com.cops.ntsf.model.FinesByOffenceType;
import com.cops.ntsf.model.Offence;
import com.cops.ntsf.service.FineService;
import com.cops.ntsf.service.PointService;
import com.cops.ntsf.util.ParseJSON;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Base64;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1MB
        maxFileSize = 2000 * 1024 * 1024, // 2000MB
        maxRequestSize = 4000 * 1024 * 1024 // 4000MB
)

public class FineServlet extends HttpServlet {

    private final String FINE_FOOTAGE_UPLOAD_DIRECTORY = "D:\\project\\NTSF-backend\\src\\main\\webapp\\images\\fine_footage";
    ;

    protected void addFine(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            JSONObject jsonObject = new JSONObject();

            boolean alert = false;
            //Fine Number is auto incremented
            String offenceType = request.getParameter("offence_type");
            //Front end sends user_id as nic/licenseNo/vehicleNo depending on the fine type
            String offenceNo = request.getParameter("offence_no");
            String spotDescription = request.getParameter("spot_description");
            LocalDateTime imposedDateTime = LocalDateTime.now();
            LocalDateTime dueDateTime = imposedDateTime.plusDays(14);  //Adds 14 days to the imposed date
            String policeId = request.getParameter("police_id");
            String policeStation = request.getParameter("police_station");
            //Fine amount retrieved from the offence table using offence_no

            //Fine footage is uploaded to the server
            Part filePart = request.getPart("footage_file");
            System.out.println("filePart: " + filePart);
            InputStream fileContent = filePart.getInputStream();
            System.out.println("fileContent: " + fileContent);
            String fileName = filePart.getSubmittedFileName();

            String latitude = request.getParameter("latitude");
            String longitude = request.getParameter("longitude");

            //Payment status is set to unpaid by default at database level

            System.out.println("Offence Type: " + offenceType);
            System.out.println("Offence no: " + offenceNo);
            System.out.println("Spot description: " + spotDescription);
            System.out.println("Police id: " + policeId);
            System.out.println("Police station: " + policeStation);
            System.out.println("Imposed date time: " + imposedDateTime);
            System.out.println("Due date time: " + dueDateTime);
            System.out.println("File name: " + fileName);
            System.out.println("Latitude: " + latitude);
            System.out.println("Longitude: " + longitude);

            String renamedFileName = renameProfilePicture(fileName); //rename the file name
            String filePath = FINE_FOOTAGE_UPLOAD_DIRECTORY + File.separator + renamedFileName; //create the file path

            // Create the directory if it doesn't exist - Remove this snippet if not necessary
            File directory = new File(FINE_FOOTAGE_UPLOAD_DIRECTORY);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            //Store the file to the specified file path
            OutputStream outputStream = new FileOutputStream(filePath);
            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = fileContent.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
//                System.out.println("buffer: " + buffer);
//                System.out.println("bytesRead: " + bytesRead);
//                System.out.println("outputStream: " + outputStream);
//                System.out.println("fileContent: " + fileContent);
//                System.out.println("filePath: " + filePath);
//                System.out.println("fileName: " + fileName);
//                System.out.println("renamedFileName: " + renamedFileName);
            }
            outputStream.close();
            fileContent.close();

            //File  not validated************
            if (checkValidations(offenceType, offenceNo, spotDescription, policeId, policeStation)) {
                if (offenceType.equals("pedestrian")) {
                    String nic = request.getParameter("user_id");
                    if (checkNICValidations(nic)) {
                        String licenseNo = null;
                        String vehicleNo = null;
                        String drivenVehicleNo = null;
                        Fine fine = new Fine(offenceNo, nic, licenseNo, vehicleNo, drivenVehicleNo, spotDescription, imposedDateTime, dueDateTime, policeId, policeStation, fileName, latitude, longitude);

                        /**
                         * Here the creation of fines happen for pedestrian
                         */
                        alert = fine.createFine();
                        System.out.println("reached");
                    } else {
                        System.out.println("Invalid NIC");
                    }
                } else if (offenceType.equals("vehicle")) {
                    String vehicleNo = request.getParameter("user_id");

                    if (checkVehicleNoValidations(vehicleNo)) {
                        String nic = getNICByVehicleNo(vehicleNo);
                        System.out.println("NIC: " + nic);
                        String licenseNo = null;
                        String drivenVehicleNo = null;
                        Fine fine = new Fine(offenceNo, nic, licenseNo, vehicleNo, drivenVehicleNo, spotDescription, imposedDateTime, dueDateTime, policeId, policeStation, fileName, latitude, longitude);

                        /**
                         * Here the creation of fines happen for vehicle
                         */
                        alert = fine.createFine();
                    } else {
                        System.out.println("Invalid vehicle number");
                    }

                } else if (offenceType.equals("driver")) {
                    String licenseNo = request.getParameter("user_id");
                    String drivenVehicleNo = request.getParameter("driven_vehicle");

                    if (checkLicenseNoValidations(licenseNo)) {
                        /**
                         * Get Nic from driver table using NIC
                         */
                        Driver driver = new Driver(licenseNo);
                        String nic = driver.getNICByLicenseNo();

                        System.out.println("NIC: " + nic);
                        String vehicleNo = null;
                        Fine fine = new Fine(offenceNo, nic, licenseNo, vehicleNo, drivenVehicleNo, spotDescription, imposedDateTime, dueDateTime, policeId, policeStation, fileName, latitude, longitude);

                        /**
                         * Here the creation of fines happen for driver
                         */
                        alert = fine.createFine();

                        // Step 1 - Getting demerit points related to current fine
                        Offence offence = new Offence();
                        Integer demeritPoints = offence.getDemeritPointsByOffenceNo(offenceNo);

                        // Step 2 - Point Reduction
                        PointService pointService = new PointService();
                        pointService.reducePoints(demeritPoints, nic);
                    }

                } else {
                    System.out.println("Invalid offence type");
                }

                if (alert) {
                    System.out.println("Fine Added Successfully");
                    jsonObject.put("serverResponse", "Not Allowed");
                    jsonObject.put("alert", alert);
                } else {
                    System.out.println("Fine Adding Failed");
                    jsonObject.put("serverResponse", "Allowed");
                    jsonObject.put("alert", alert);
                }

                out.write(jsonObject.toString());
                out.close();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getNICByVehicleNo(String vehicleNo) {
        return "123456789V";
    }

//    private String getNICByLicenseNo(String licenseNo) {
//        DriverDAO driverDAO = new DriverDAO();
//        return driverDAO.fetchNICByLicenseNo();
//    }

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
        System.out.println("Reached viewFineAsOIC");
        String policeStation = request.getParameter("police_station");
        String offenceType = request.getParameter("offence_type");
        String paymentStatus = request.getParameter("payment_status");
        System.out.println("Police station is " + policeStation);
        System.out.println("Offence Type is " + offenceType);
        System.out.println("Payment Status is " + paymentStatus);

        Fine fine = new Fine();
        JSONArray fineListAsOIC = fine.getFineListAsOIC(policeStation, offenceType, paymentStatus);

        jsonObject.put("List", fineListAsOIC);

        out.println(jsonObject.toString());
        out.close();

    }

    private boolean checkValidations(String offenceType, String offenceNo, String spotDescription, String policeId, String policeStation) {
        boolean flagOffenceType = false; // flag = true means validation is passed
        boolean flagOffenceNo = false;
        boolean flagSpotDescription = false;
        boolean flagPoliceId = true; //made true temporarily ************************
        boolean flagPoliceStation = true; //made true temporarily********************
        boolean flag = false;

        if (offenceType.equals("pedestrian") || offenceType.equals("vehicle") || offenceType.equals("driver")) {
            flagOffenceType = true;
            System.out.println("Valid offence type");
        } else {
            System.out.println("Invalid offence type");
            flagOffenceType = false;
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
        if (flagOffenceType && flagOffenceNo && flagSpotDescription && flagPoliceId && flagPoliceStation) {
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
                            System.out.println("Redirecting to addFine method");
                            addFine(request, response);
                        }
                    } else {
                        System.out.println("You are not authorized to access this page");
                    }
                } else if (authorizedRank.equals("oic")) {
                    if (action.equals("viewFineAsOIC")) {
                        System.out.println("Redirecting to viewFineAsOIC method");
                        viewFineAsOIC(request, response);
                    }
                } else {
                    System.out.println("You are not authorized to access this page");
                }
            }

        } else {
            System.out.println("JWT signature verification failed");
        }

    }

    /**
     * Get fines info of a user
     *
     * @param req  HTTP request
     * @param resp HTTP response
     * @throws IOException Exception
     */
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        // Get request parameters
        String nic = req.getParameter("nic");

        FineService fineService = new FineService();

        FinesByOffenceType finesList = null;

        try {
            finesList = fineService.getFinesInfo(nic);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Output response
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        // To get local date time
        out.write(ParseJSON.parseToJSONString(finesList));
        out.close();
    }

    public static String renameProfilePicture(String file_footage) throws Exception {

        /*Check what the current fine number is and add 1 to it and return*/
        Fine fine = new Fine();
        int fineNo = fine.getCurrentFineNoForFootage() + 1;
        System.out.println("Profile Picture Name before change: " + file_footage);
        String[] parts = file_footage.split("\\.");
        String extension = parts[1];
        String new_file_footage = fineNo + "." + extension;
        System.out.println("Profile Picture Name after change: " + new_file_footage);
        return new_file_footage;
    }
}