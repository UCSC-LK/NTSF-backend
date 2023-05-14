package com.cops.ntsf.controller;

import com.cops.ntsf.model.Complaint;
import com.cops.ntsf.model.Policeman;
import com.cops.ntsf.service.Email;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Base64;
import java.util.function.DoubleToIntFunction;

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
                System.out.println("Authorized Rank: " + authorizedRank);
                String authorizedPosition = payloadJsonObject.getString("position");
                System.out.println("Authorized Position: " + authorizedPosition);

                if (authorizedRank.equals("policeman")) {
                    viewProfile(request, response);
                    viewProfilePictureInDashboard(request, response);
                    if (authorizedPosition.equals("trafficPolice")) {
                        if (action.equals("addFine")) {
//                            new FineServlet().addFine(request, response);
                        } else {
                            System.out.println("You are not authorized to access this page, Only trafficPolice are allowed to access this page");
                        }
                    } else if (authorizedPosition.equals("investigationOfficer")) {
                        if (action.equals("viewAppealsAsInvestigationOfficer")) {
//                            new ComplaintServlet().viewComplaintsAsInvestigationOfficer(request, response);
                            viewAppealsAsInvestigationOfficer(request, response);
                        } else if(action.equals("rejectAppeal"))
                        {
                            rejectAppeal(request, response);
                        } else
                        {
                            System.out.println("You are not authorized to access this page. Only investigationOfficer are allowed to access this page");
                        }
                    } else if (authorizedPosition.equals("courtSeargent")) {
                        if (action.equals("addComplaint")) {
//                            new ComplaintServlet().addComplaint(request, response);
                        } else {
                            System.out.println("You are not authorized to access this page. Only courtSeargent are allowed to access this page");
                        }
                    } else {
                        System.out.println("You are not authorized to access this page. Only Policemen are allowed to access this page");
                    }
                }

                else if (authorizedRank.equals("igp") || authorizedRank.equals("oic") || authorizedRank.equals("policeman")){
                    if (action.equals("viewProfile")) {
                        System.out.println("Redirecting to viewProfile in Policeman Servlet");
                        viewProfile(request, response);
                    } else if (action.equals("viewProfilePicture")) {
                        System.out.println("Redirecting to viewProfilePicture in Policeman Servlet");
                        viewProfilePicture(request, response);
                    } else if (action.equals("viewProfilePictureInDashboard")){
                        System.out.println("Redirecting to viewProfilePictureInDashboard in Policeman Servlet");
                        viewProfilePictureInDashboard(request, response);
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

    private void rejectAppeal(HttpServletRequest request, HttpServletResponse response) {
        try{
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");

            System.out.println("Came until the rejectAppeal method in Policeman Servlet");
            String complaint_no = request.getParameter("complaint_no");
            System.out.println("Complaint No: " + complaint_no);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("serverResponse", "Allowed");

            Complaint complaint = new Complaint();
            boolean rejectAppealAlert =  complaint.rejectAppealAsInvestigationOfficer(complaint_no);

            if (rejectAppealAlert == true){
            //    Email email = new Email();
            //    email.sendEmail("Appeal Rejected", "Your appeal has been rejected", complaint.getAppealEmail(complaint_no));
                System.out.println("Appeal Rejected Successfully");
            } else {
                System.out.println("Appeal Rejected Failed");
            }

            jsonObject.put("alert", rejectAppealAlert);
            out.write(jsonObject.toString());
            out.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void viewAppealsAsInvestigationOfficer(HttpServletRequest request, HttpServletResponse response) {
        try{
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");

            System.out.println("Came until the viewAppealsAsInvestigationOfficer method in Policeman Servlet");
            String police_station = request.getParameter("police_station");
            System.out.println("Police Station: " + police_station);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("serverResponse", "Allowed");

            Complaint complaint = new Complaint();
            JSONArray appealList = complaint.fetchAppealsAsInvestigationOfficer(police_station);

            jsonObject.put("List", appealList);
            out.write(jsonObject.toString());
            out.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void viewProfilePicture(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Came until the viewProfilePicture method in Policeman Servlet");
        String police_id = request.getParameter("police_id");
        String imagePath = request.getParameter("imagePath");

//        String imagePath = "D:\\project\\NTSF-backend\\src\\main\\webapp\\images\\profile_pictures\\1001001.jpeg"; // replace with your image path
        File file = new File(imagePath);

//        response.setContentType("image/jpeg"); // replace with your image type
        // get the content type dynamically based on the image file extension
        String contentType = getServletContext().getMimeType(file.getName());
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        response.setContentType(contentType);
        response.setContentLength((int) file.length());

        FileInputStream fis = new FileInputStream(file);
        OutputStream out = response.getOutputStream();

        byte[] buffer = new byte[4096];
        int bytesRead = -1;

        while ((bytesRead = fis.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }

        fis.close();
        out.flush();
        out.close();
    }

    private String getExtension(String fileName) {
        String extension = "";

        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i + 1);
        }

        return extension;
    }

    protected void viewProfile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Came until the viewProfile method in Policeman Servlet");

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        String police_id = request.getParameter("police_id");
        System.out.println("police_id: " + police_id);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("serverResponse", "Allowed");

        Policeman policeman = new Policeman();
        JSONArray profileInformation = policeman.getProfileDetails(police_id);

        jsonObject.put("List", profileInformation);

        out.write(jsonObject.toString());
        out.close();

    }
    protected void viewProfilePictureInDashboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Came until the viewProfilePictureInDashboard method in Policeman Servlet");
        String police_id = request.getParameter("police_id");

        String imagePath = "D:\\project\\NTSF-backend\\src\\main\\webapp\\images\\profile_pictures\\" + police_id + ".jpeg"; // Create path using police_id for dashboard
        System.out.println("imagePath: " + imagePath);

//        String imagePath = "D:\\project\\NTSF-backend\\src\\main\\webapp\\images\\profile_pictures\\1001001.jpeg"; // replace with your image path
        File file = new File(imagePath);

//        response.setContentType("image/jpeg"); // replace with your image type
        // get the content type dynamically based on the image file extension
        String contentType = getServletContext().getMimeType(file.getName());
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        response.setContentType(contentType);
        response.setContentLength((int) file.length());

        FileInputStream fis = new FileInputStream(file);
        OutputStream out = response.getOutputStream();

        byte[] buffer = new byte[4096];
        int bytesRead = -1;

        while ((bytesRead = fis.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }

        fis.close();
        out.flush();
        out.close();
    }




}
