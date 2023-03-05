package com.cops.ntsf.controller;


import com.cops.ntsf.model.PoliceStation;
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

public class PoliceStationServlet extends HttpServlet {
    protected void addPoliceStation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    try{
        {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            HttpSession session = request.getSession();
            JSONObject jsonObject = new JSONObject();

            String branch_name = request.getParameter("branch_name");
            String address = request.getParameter("address");
            String district = request.getParameter("district");
            String province = request.getParameter("province");
            String contact_number = request.getParameter("contact_number");
            String email = request.getParameter("email");

            System.out.println("Works until Servlet");
            System.out.println(branch_name);
            System.out.println(address);
            System.out.println(district);
            System.out.println(province);
            System.out.println(contact_number);
            System.out.println(email);

            if (checkValidations(branch_name, address, district, province, contact_number, email))
            {
                PoliceStation policeStation = new PoliceStation(branch_name, address, district, province, contact_number, email);
                policeStation.policeStationAdded();
            }
            else
            {

            }
            out.write(jsonObject.toString());
            out.close();
        }
    } catch (Exception e)
    {
        e.printStackTrace();
    }
    }

    protected void loadPoliceStationOptionsList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            HttpSession session = request.getSession(false);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("serverResponse", "Allowed");

            PoliceStation policeStation = new PoliceStation();
            JSONArray policeStationOptionsList = policeStation.getPoliceStationOptions();

            jsonObject.put("List", policeStationOptionsList );

            out.write(jsonObject.toString());
            out.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    protected void viewPoliceStation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession(false);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("serverResponse", "Allowed");

        PoliceStation policeStation = new PoliceStation();
        JSONArray policeStationList = policeStation.getPoliceStationDetails();

        jsonObject.put("List", policeStationList );

        out.write(jsonObject.toString());
        out.close();

    }
    public  void  doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

                JSONObject payLoadJsonObject = new JSONObject(payloadJson);
                String authorizedRank = payLoadJsonObject.getString("rank");

                if (authorizedRank == "igp")
                {

                    if (action.equals("addPoliceStation")) {
                        addPoliceStation(request, response);
                    }
                    else if (action.equals("loadPoliceStationOptionsList")) {
                        loadPoliceStationOptionsList(request, response);
                    }
                    else if (action.equals("viewPoliceStation"))
                    {
                        viewPoliceStation(request, response);
                    }
                    else if (action.equals("checkBranch_Name"))
                    {
                        checkPoliceStationBranch_Name(request, response);
                        System.out.println("doPost > checkpoliceStationBranch_Name");
                    }
                    else if (action.equals("checkEmail"))
                    {
                        checkPoliceStationEmail(request, response);
                        System.out.println("doPost > checkpoliceStationEmail");
                    }
                    else if (action.equals("checkContact_Number"))
                    {
                        checkPoliceStationContact_Number(request, response);
                        System.out.println("doPost > checkpoliceStationContact_Number");
                    }
                }
                else
                {
                    System.out.println("You are not authorized to access this page");
                }
            }
        }
        else {
            System.out.println("JWT signature verification failed");
        }
    }















    private void checkPoliceStationContact_Number(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            HttpSession session = request.getSession();
            JSONObject jsonObject = new JSONObject();

            String contact_number = request.getParameter("contact_number");
            System.out.println(contact_number);

            PoliceStation policeStation = new PoliceStation();
            jsonObject.put("alert", policeStation.policeStationContact_NumberCheck(contact_number));

            out.write(jsonObject.toString());
            out.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    private void checkPoliceStationEmail(HttpServletRequest request, HttpServletResponse response) throws   ServletException, IOException {
        try{
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            HttpSession session = request.getSession();
            JSONObject jsonObject = new JSONObject();

            String email = request.getParameter("email");
            System.out.println(email);
            System.out.println("Came until error duplication in servlet");

            PoliceStation policeStation = new PoliceStation();
            jsonObject.put("alert", policeStation.policeStationEmailCheck(email));

            out.write(jsonObject.toString());
            out.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    private void checkPoliceStationBranch_Name(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            HttpSession session = request.getSession();
            JSONObject jsonObject = new JSONObject();

            String branch_name = request.getParameter("branch_name");
            System.out.println(branch_name);
            System.out.println("Came until error duplication in servlet");

            PoliceStation policeStation = new PoliceStation();
            jsonObject.put("alert", policeStation.policeStationBranch_NameCheck(branch_name));

            out.write(jsonObject.toString());
            out.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }




    private boolean checkValidations(String branch_name, String address, String district, String province, String contact_number, String email) {
        boolean flag = false; //flag = true means all the validations are passed
        if(branch_name.trim() == "")
        {
            System.out.println("Branch Name is empty");
            flag = false;
        }
        else if(branch_name.trim().length() < 3)
        {
            System.out.println("Branch Name is too short");
            flag = false;
        }
        else if (branch_name.trim().length() > 50)
        {
            System.out.println("Branch Name is too long");
            flag = false;
        }
        else
        {
            System.out.println("Branch Name is valid");
            flag = true;
        }

        if (address.trim() == "")
        {
            System.out.println("Address is empty");
            flag = false;
        }
        else if (address.trim().length() < 3)
        {
            System.out.println("Address is too short");
            flag = false;
        }
        else if (address.trim().length() > 100)
        {
            System.out.println("Address is too long");
            flag = false;
        }
        else
        {
            System.out.println("Address is valid");
            flag = true;
        }

        if (district.trim() == "")
        {
            System.out.println("District is empty");
            flag = false;
        }
        else
        {
            System.out.println("District is valid");
            flag = true;
        }

        if (province.trim() == "")
        {
            System.out.println("Province is empty");
            flag = false;
        }
        else
        {
            System.out.println("Province is valid");
            flag = true;
        }

        if(contact_number.trim() == "")
        {
            System.out.println("Contact Number is empty");
            flag = false;
        }
        else if (contact_number.trim().length() != 10)
        {
            System.out.println("Contact Number should contain 10 digits");
            flag = false;
        }
        else
        {
            System.out.println("Contact Number is valid");
            flag = true;
        }

        if (email.trim() == "")
        {
            System.out.println("Email is empty");
            flag = false;
        }
        else if (email.trim().matches("^[A-Za-z0-9+_.-]+@(.+)$") == false)
        {
            System.out.println("Email is invalid");
            flag = false;
        }
        else {
            System.out.println("Email is valid");
            flag = true;
        }
        return flag;
    }
}
