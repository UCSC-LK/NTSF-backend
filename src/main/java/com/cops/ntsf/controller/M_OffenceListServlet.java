package com.cops.ntsf.controller;


import com.cops.ntsf.dao.M_OffenceDAO;
import com.cops.ntsf.model.M_Offence;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class M_OffenceListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        List<M_Offence> list = M_OffenceDAO.getAllOffences();

        JSONObject offenceResponse = new JSONObject();
        JSONArray offences = new JSONArray();
        for (M_Offence offence : list) {
            JSONObject data = new JSONObject();

            data.put("offenceNo", String.valueOf(offence.getOffenceNo()));
            data.put("offenceType", offence.getOffenceType());
            data.put("description", offence.getDescription());
            data.put("pointWeight", offence.getPointWeight());
            data.put("amount", offence.getAmount());
            offences.put(data);
        }
        offenceResponse.put("offences", offences);
        out.write(offenceResponse.toString());

    }
}