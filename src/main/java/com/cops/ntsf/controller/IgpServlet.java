package com.cops.ntsf.controller;

import com.cops.ntsf.model.Policeman;
import com.cops.ntsf.util.JwtUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Random;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1MB
        maxFileSize = 20 * 1024 * 1024, // 20MB
        maxRequestSize = 400 * 1024 * 1024 // 400MB
)
public class IgpServlet extends HttpServlet {
    /*Profile Picture upload*/
    private final String UPLOAD_DIRECTORY = "D:\\project\\NTSF-backend\\src\\main\\webapp\\images\\profile_pictures"; //working properly

    protected void addPoliceman(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            boolean alert = false;

            String currentDir = System.getProperty("user.dir");
            System.out.println("Current directory: " + currentDir);


            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            JSONObject jsonObject = new JSONObject();

            String name = request.getParameter("name");
            String police_id = request.getParameter("police_id");
            String nic = request.getParameter("nic");
            String mobile_number = request.getParameter("mobile_number");
            String email = request.getParameter("email");
            String rank = request.getParameter("rank");
            String police_station = request.getParameter("police_station");
            String grade = request.getParameter("grade");

            /*Profile picture upload*/
            Part filePart = request.getPart("profile_picture");
            System.out.println("filePart: " + filePart);
            InputStream fileContent = filePart.getInputStream();
            System.out.println("fileContent: " + fileContent);
            String fileName = filePart.getSubmittedFileName();
            System.out.println("name: " + name);
            System.out.println("police_id: " + police_id);
            System.out.println("nic: " + nic);
            System.out.println("mobile_number: " + mobile_number);
            System.out.println("email: " + email);
            System.out.println("rank: " + rank);
            System.out.println("police_station: " + police_station);
            System.out.println("grade: " + grade);
            System.out.println("fileName: " + fileName);

            String renamedFileName = renameProfilePicture(police_id, fileName); //rename the file name
            String filePath = UPLOAD_DIRECTORY + File.separator + renamedFileName; //create the file path

            // Create the directory if it doesn't exist - Remove this snippet if not necessary
            File directory = new File(UPLOAD_DIRECTORY);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            //Store the file to the specified file path
            OutputStream outputStream = new FileOutputStream(filePath);
            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = fileContent.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
                System.out.println("buffer: " + buffer);
                System.out.println("bytesRead: " + bytesRead);
                System.out.println("outputStream: " + outputStream);
                System.out.println("fileContent: " + fileContent);
                System.out.println("filePath: " + filePath);
                System.out.println("fileName: " + fileName);
                System.out.println("renamedFileName: " + renamedFileName);
            }
            outputStream.close();
            fileContent.close();

            System.out.println("filePath: " + filePath);

            System.out.println("addPoliceman method in IGPServlet invoked");
            if (checkValidations(name, police_id, nic, mobile_number, email, rank, police_station)) {
                PasswordGenerator passwordGenerator = new PasswordGenerator();
                String password = passwordGenerator.generatePassword();
                String hashedPassword = hashingPassword(password);
                System.out.println(password);
                Policeman policeman = new Policeman(name, police_id, nic, mobile_number, email, rank, police_station, hashedPassword, filePath);
                alert = policeman.policemanAdded();

                jsonObject.put("alert", alert);
            } else {
                out.write(jsonObject.toString());
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void viewPoliceman(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("serverResponse", "Allowed");

        Policeman policeman = new Policeman();
        JSONArray policemanList = policeman.getPolicemanDetails();

        jsonObject.put("List", policemanList);

        out.write(jsonObject.toString());
        out.close();

    }

    protected void fetchPoliceman(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            String police_id = request.getParameter("police_id");

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("serverResponse", "Allowed");

            Policeman policeman = new Policeman();
            JSONArray fetchedpolicemanList = policeman.fetchPolicemanDetails(police_id);

            jsonObject.put("List", fetchedpolicemanList);

            out.write(jsonObject.toString());
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void editPoliceman(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("serverResponse", "Allowed");

            String name = request.getParameter("name");
            String police_id = request.getParameter("police_id");
            String nic = request.getParameter("nic");
            String mobile_number = request.getParameter("mobile_number");
            String email = request.getParameter("email");
            String rank = request.getParameter("rank");
            String police_station = request.getParameter("police_station");

            if (checkValidations(name, police_id, nic, mobile_number, email, rank, police_station)) {
                Policeman policeman = new Policeman(name, police_id, nic, mobile_number, email, rank, police_station);
                policeman.policemanEdited();
            } else {

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void removePoliceman(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("serverResponse", "Allowed");

            String police_id = request.getParameter("police_id");
            System.out.println(police_id);

            Policeman policeman = new Policeman();
            jsonObject.put("alert", policeman.deletePolicemanDetails(police_id));

            out.write(jsonObject.toString());
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void checkPolicemanPolice_ID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            JSONObject jsonObject = new JSONObject();

            String police_id = request.getParameter("police_id");
            System.out.println(police_id);

            Policeman policeman = new Policeman();
            jsonObject.put("alert", policeman.policemanPolice_IDCheck(police_id));

            out.write(jsonObject.toString());
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error block");
        }
    }

    protected void checkPolicemanNic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            JSONObject jsonObject = new JSONObject();

            String nic = request.getParameter("nic");
            System.out.println(nic);

            Policeman policeman = new Policeman();
            jsonObject.put("alert", policeman.policemanNicCheck(nic));

            out.write(jsonObject.toString());
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error block");
        }

    }

    protected void checkPolicemanMobile_Number(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            JSONObject jsonObject = new JSONObject();

            String mobile_number = request.getParameter("mobile_number");
            System.out.println(mobile_number);

            Policeman policeman = new Policeman();
            jsonObject.put("alert", policeman.policemanNicCheck(mobile_number));

            out.write(jsonObject.toString());
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error block");
        }

    }

    protected void checkPolicemanEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            JSONObject jsonObject = new JSONObject();

            String email = request.getParameter("email");
            System.out.println(email);

            Policeman policeman = new Policeman();
            jsonObject.put("alert", policeman.policemanNicCheck(email));

            out.write(jsonObject.toString());
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error block");
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        String contentType = request.getHeader("Content-type");
        String authorizationHeader = request.getHeader("Authorization");
        System.out.println("Authorization Header: " + authorizationHeader);
        System.out.println("Content Type: " + contentType);
        System.out.println("Action: " + action);

        JwtUtils jwtUtils = new JwtUtils(authorizationHeader);

        if (jwtUtils.verifyJwtAuthentication()) {
            String authorizedRank = jwtUtils.getAuthPayload().getString("rank");
            System.out.println(authorizedRank);
            if (authorizedRank.equals("igp")) {
                if (action.equals("addPoliceman")) {
                    addPoliceman(request, response);
                } else if (action.equals("viewPoliceman")) {
                    System.out.println("Redirecting to viewPoliceman in Policeman Servlet");
                    viewPoliceman(request, response);
                } else if (action.equals("fetchPoliceman")) {
                    fetchPoliceman(request, response);
                } else if (action.equals("updatePoliceman")) {
                    editPoliceman(request, response);
                } else if (action.equals("deletePoliceman")) {
                    removePoliceman(request, response);
                } else if (action.equals("checkPoliceman_ID")) {
                    checkPolicemanPolice_ID(request, response);
                    System.out.println("Hi");
                } else if (action.equals("checkNIC")) {
                    checkPolicemanNic(request, response);
                    System.out.println("Hi from NIC Checking servelet");
                } else if (action.equals("checkMobile_Number")) {
                    checkPolicemanMobile_Number(request, response);
                    System.out.println("Hi from Mobile Number Checking servelet");
                } else if (action.equals("checkEmail")) {
                    checkPolicemanEmail(request, response);
                    System.out.println("Hi from Email Checking servelet");
                }
            } else {
                System.out.println("You are not authorized to access this page");
            }
        } else {
            System.out.println("JWT signature verification failed");
        }
    }

    private boolean checkValidations(String name, String police_id, String nic, String mobile_number, String email, String rank, String police_station) {
        boolean flagName = false; //flag = true means name validation is passed
        boolean flagPolice_ID = false; //flag = true means Police ID validation is passed
        boolean flagNIC = false; //flag = true means NIC validation is passed
        boolean flagMobile_Number = false; //flag = true means Mobile Number validation is passed
        boolean flagEmail = false; //flag = true means Email validation is passed
        boolean flagRank = false; //flag = true means Rank validation is passed
        boolean flagPolice_Station = false; //flag = true means Police Station is passed
        boolean flag = false; //flag = true means all the validations are passed

        if (name.trim() == "") {
            System.out.println("Name is empty");
            flagName = false;
        } else if (name.trim().length() < 3) {
            System.out.println("Name is too short");
            flagName = false;
        } else if (name.trim().length() > 50) {
            System.out.println("Name is too long");
            flagName = false;
        } else {
            System.out.println("Name is valid");
            flagName = true;
        }

        if (police_id.trim() == "") {
            System.out.println("Police ID is empty");
            flagPolice_ID = false;
        } else if (police_id.trim().matches("[0-9]+") == false) {
            System.out.println("Police ID should contain only digits");
            flagPolice_ID = false;
        } else if (police_id.trim().length() != 10) {
            System.out.println("Police ID should contain 10 digits");
            flagPolice_ID = false;
        } else {
            System.out.println("Police ID is valid");
            flagPolice_ID = true;
        }

        if (nic.trim() == "") {
            System.out.println("NIC is empty");
            flagNIC = false;
        } else {
            System.out.println("NIC is valid");
            flagNIC = true;
        }
        if (mobile_number.trim() == "") {
            System.out.println("Mobile Number is empty");
            flagMobile_Number = false;
        } else if (mobile_number.trim().matches("[0-9]+") == false) {
            System.out.println("Mobile Number should contain only digits");
            flagMobile_Number = false;
        } else if (mobile_number.trim().length() != 10) {
            System.out.println("Mobile Number should contain 10 digits");
            flagMobile_Number = false;
        } else {
            System.out.println("Mobile Number is valid");
            flagMobile_Number = true;
        }

        if (email.trim() == "") {
            System.out.println("Email is empty");
            flagEmail = false;
        } else if (email.trim().matches("^[A-Za-z0-9+_.-]+@(.+)$") == false) {
            System.out.println("Email is invalid");
            flagEmail = false;
        } else {
            System.out.println("Email is valid");
            flagEmail = true;
        }

        if (rank.trim() == "") {
            System.out.println("Rank is empty");
            flagRank = false;
        } else {
            System.out.println("Rank is valid");
            flagRank = true;
        }

        if (police_station.trim() == "") {
            System.out.println("Police Station is empty");
            flagPolice_Station = false;
        } else {
            System.out.println("Police Station is valid");
            flagPolice_Station = true;
        }
        if (flagName == true && flagPolice_ID == true && flagNIC == true && flagMobile_Number == true && flagEmail == true && flagRank == true && flagPolice_Station == true) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }

    public class PasswordGenerator {
        public String generatePassword() {
            StringBuilder password = new StringBuilder();
            Random rnd = new Random();
            for (int i = 0; i < 10; i++) {
                int ascii = rnd.nextInt(74) + 48;
                if ((ascii >= 58 && ascii <= 64) || (ascii >= 91 && ascii <= 96)) {
                    i--;
                    continue;
                }
                password.append((char) ascii);
            }
            return password.toString();
        }
    }

    public static String hashingPassword(String password) throws Exception {
        String originalString = password;
        System.out.println("Original String to hash: " + originalString);
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(originalString.getBytes("UTF-8"));
        String encoded = Base64.getEncoder().encodeToString(hash);
        System.out.println("Hash: " + encoded);
        return encoded;
    }

    public static String renameProfilePicture(String police_id, String profile_picture) throws Exception {
        System.out.println("Profile Picture Name before change: " + profile_picture);
        String[] parts = profile_picture.split("\\.");
        String extension = parts[1];
        String new_profile_picture = police_id + "." + extension;
        System.out.println("Profile Picture Name after change: " + new_profile_picture);
        return new_profile_picture;
    }
}
