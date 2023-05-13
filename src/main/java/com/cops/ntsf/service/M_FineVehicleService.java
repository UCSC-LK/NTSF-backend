package com.cops.ntsf.service;

import com.cops.ntsf.model.M_FineVehicle;
import org.json.JSONObject;

import java.text.ParseException;

public class M_FineVehicleService {

    public String insertFineInfo(String nic,String location,String fineNo,String description,String policeId,String fineType,String vehicleNo) throws ParseException {
//        System.out.println("Inside insert fine info");
        JSONObject fineResponse = new JSONObject();
        M_FineVehicle fineVehicle = new M_FineVehicle(nic,location,fineNo,description,policeId,fineType,vehicleNo);
        Integer fine_number = fineVehicle.setFineInfo();
        if(fine_number != 0){
            fineResponse.put("Status","Success");
            fineResponse.put("fine_number",fine_number);

        }else {
            fineResponse.put("Status","Failed");
        }

        return fineResponse.toString();

    }


}