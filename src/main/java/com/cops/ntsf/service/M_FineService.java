package com.cops.ntsf.service;

import com.cops.ntsf.model.M_Fine;
import org.json.JSONObject;

import java.text.ParseException;
import java.time.LocalDateTime;

public class M_FineService {




    public String insertFineInfo(String licenceNo, String location, String fineNo, String description, String policeId, String fineType, String vehicleNo, LocalDateTime imposedDateTime , LocalDateTime dueDateTime) throws ParseException {
//        System.out.println("Inside insert fine info");


        JSONObject fineResponse = new JSONObject();
        M_Fine fine = new M_Fine(licenceNo,location,fineNo,description,policeId,fineType,vehicleNo,imposedDateTime,dueDateTime);
        Integer fine_number = fine.setFineInfo();
        if(fine_number != 0){
            fineResponse.put("Status","Success");
            fineResponse.put("fine_number",fine_number);

        }else {
            fineResponse.put("Status","Failed");
        }

        return fineResponse.toString();

    }


}