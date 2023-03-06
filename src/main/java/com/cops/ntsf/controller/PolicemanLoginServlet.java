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

import static com.cops.ntsf.controller.IgpServlet.hashingPassword;

public class PolicemanLoginServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action.equals("login")) {
            login(request, response);
        } else if (action.equals("checkLoginUsername")) {
            checkLoginUsername(request, response);
        }

    }


    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            JSONObject jsonObject = new JSONObject();

            String police_id = request.getParameter("username");
            String password = request.getParameter("password");

            String hashedPassword = hashingPassword(password);

            System.out.println("Works until login servlet");

            System.out.println(police_id);
            System.out.println(password);
            System.out.println(hashedPassword);
            Policeman policeman = new Policeman();
            JSONArray loginResponse = policeman.login(police_id, hashedPassword);

            //Printing the login response in the servlet
            System.out.println("Printing the loginResponse in the servlet\n");
            System.out.println(loginResponse);

            boolean loginStatus = loginResponse.getJSONObject(0).getBoolean("authorization");
            System.out.println(loginStatus);

            if (loginStatus == true) {
                System.out.println("Login Successful and jwt token is being generated\n");
                /*Generating JWT token*/
                //Creating the header
                JSONObject header = new JSONObject();
                header.put("alg", "HS256");
                header.put("typ", "JWT");

                //Creating the payload
                JSONObject payload = new JSONObject();
                payload.put("police_id", loginResponse.getJSONObject(0).getString("police_id"));
                payload.put("rank", loginResponse.getJSONObject(0).getString("rank"));
                payload.put("position", loginResponse.getJSONObject(0).getString("position"));

                //Encoding the header and payload objects into base64url format
                String base64UrlHeader = Base64.getUrlEncoder().encodeToString(header.toString().getBytes());
                String base64UrlPayload = Base64.getUrlEncoder().encodeToString(payload.toString().getBytes());

                //Concatenate the encoded header and payload with a period ("."):
                String base64UrlHeaderAndPayload = base64UrlHeader + "." + base64UrlPayload;

                //sign the jwt with a secret key
                Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
                SecretKeySpec secret_key = new SecretKeySpec("mysecret".getBytes(), "HmacSHA256");
                sha256_HMAC.init(secret_key);

                String signature = Base64.getUrlEncoder().encodeToString(sha256_HMAC.doFinal(base64UrlHeaderAndPayload.getBytes()));

                //Concatenate the encoded header, payload and signature with a period ("."):
                String jwt = base64UrlHeaderAndPayload + "." + signature;
                System.out.println("JWT token is generated\n");
                System.out.println(jwt);
                jsonObject.put("jwt", jwt); //Adding the jwt token to the json object

            }
            else {
                System.out.println("Login Unsuccessful");
                jsonObject.put("jwt", "LoginUnsuccessful"); //To redirect to the login page at front end
            }

            System.out.println("Finally Printing the json object in the servlet\n");
            System.out.println(jsonObject);
            out.write(jsonObject.toString());
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void checkLoginUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            HttpSession session = request.getSession();
            JSONObject jsonObject = new JSONObject();

            String police_id = request.getParameter("username");
            System.out.println(police_id);
            System.out.println("Came until checkLoginUsername duplication in servlet");

            Policeman policeman = new Policeman();
            jsonObject.put("alert",  policeman.LoginUsernameCheck(police_id));

            out.write(jsonObject.toString());
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
