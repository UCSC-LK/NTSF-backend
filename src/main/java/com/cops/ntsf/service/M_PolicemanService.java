package com.cops.ntsf.service;

import com.cops.ntsf.model.*;
import org.json.JSONObject;

public class M_PolicemanService {

    public String verifyLogin(String police_id, String input_password) {

         M_Policeman policeman = new M_Policeman();
         policeman.setPoliceID(police_id);
         policeman.getPolicemanFromPoliceId();


        JSONObject loginResponse = new JSONObject();
        loginResponse.put("loggedIn", verifyPassword(policeman.getPassword(), input_password));
        loginResponse.put("police_id", police_id);
        boolean police_rank = false;
        loginResponse.put("loginAuthorizingBackend",police_rank);

        return loginResponse.toString();
    }

    public Boolean verifyPassword(String password, String input_password) {

         if(password == null){
             return false;
         }else {
             return password.equals(input_password);
         }
    }
}