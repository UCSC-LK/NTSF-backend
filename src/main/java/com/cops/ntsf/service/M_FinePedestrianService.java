package com.cops.ntsf.service;

import com.cops.ntsf.model.M_FinePedestrian;
import org.json.JSONObject;

import java.text.ParseException;

public class M_FinePedestrianService {

    public String insertFineInfo(String nic,String location,String fineNo,String description,String policeId,String fineType) throws ParseException {
//        System.out.println("Inside insert fine info");
        JSONObject fineResponse = new JSONObject();
        M_FinePedestrian finePedestrian = new M_FinePedestrian(nic,location,fineNo,description,policeId,fineType);
        Integer fine_number = finePedestrian.setFineInfo();
        if(fine_number != 0){
            fineResponse.put("Status","Success");
            fineResponse.put("fine_number",fine_number);

        }else {
            fineResponse.put("Status","Failed");
        }

        return fineResponse.toString();

    }


}