package com.cops.ntsf.controller;

import com.cops.ntsf.model.Complaint;
import com.cops.ntsf.util.FileUpload;
import com.cops.ntsf.util.Validator;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 20 * 1024 * 1024, // 20MB - Server accepts
        maxRequestSize = 400 * 1024 * 1024 // 400MB - Server rejects if exceeded
)

public class ComplaintServlet extends HttpServlet {

    private final String UPLOAD_DIRECTORY = "D:\\project\\NTSF-backend\\src\\main\\webapp\\images\\user\\footage";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action.equals("createComplaint")) {
            try {
                createComplaint(request, response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    protected void createComplaint(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String fineNo = request.getParameter("fine_no");
        String userId = request.getParameter("user_id");
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        // Retrieve the file part
        Part filePart = request.getPart("footage");

        // Upload the file and get the resulting file path
        String filePath = FileUpload.uploadFile(filePart, UPLOAD_DIRECTORY, userId);

        Validator validator = new Validator();
        int validateStatusCode = validator.validateParamsComplaint(title, description);

        switch (validateStatusCode) {
            case 0:
//                Complaint complaint = new Complaint(fineNo, userId, title, description);
                Complaint complaint = new Complaint(fineNo, userId, title, description, filePath);
                complaint.complaintAdded();

                // Output response
                PrintWriter out = response.getWriter();
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");

                out.write(new Gson().toJson(complaint));
                out.close();
            case 1:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Incorrect Title");
                break;
            case 2:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Incorrect Description");
                break;
            default:
                break;
        }
    }

    /**
     * View complaint in user side
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Get request parameters
        String user_id = req.getParameter("user_id");
        ArrayList<Complaint> complaintList;

        Complaint complaint = new Complaint(user_id);
        try {
            complaintList = complaint.getUserComplaintInfo();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Output response
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        out.write(new Gson().toJson(complaintList));
        out.close();
    }

    protected void viewComplaintsAsInvestigationOfficer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("serverResponse", "Allowed");

        Complaint complaint = new Complaint();
        JSONArray complaintListasInvestigationOfficer = complaint.getComplaintsDetailsAsInvestigationOfficer();

        jsonObject.put("List", complaintListasInvestigationOfficer);

        out.write(jsonObject.toString());
        out.close();
    }

    public static String renameFileName(String userId, String footage) throws Exception {
        String[] parts = footage.split("\\.");
        String extension = parts[1];
        String new_footage = userId + "." + extension;
        System.out.println("Profile Picture Name after change: " + new_footage);
        return new_footage;
    }
}
