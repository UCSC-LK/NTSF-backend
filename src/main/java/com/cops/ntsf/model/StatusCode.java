//package com.cops.ntsf.model;
//
//import com.cops.ntsf.util.Validator;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class StatusCode {
//    private String nic;
//    private String password;
//    private String email;
//    private String mobileNo;
//    private String title;
//    private String description;
//
//    public int getValidateStatusCode() throws IOException {
//
//        int validateStatusCode = validateParams(nic, password, email, mobileNo, title, description);
//
//        switch (validateStatusCode) {
//            case 0:
//                break;
//            case 1:
//                HttpServletResponse resp = null;
//                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Incorrect NIC Number");
//                break;
//            case 2:
//                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Incorrect Password Format");
//                break;
//            case3:
//            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Incorrect Email");
//            break;
//            case4:
//            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Incorrect Complaint Title");
//            break;
//            case5:
//            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Incorrect Complaint Description");
//            break;
//            default:
//                break;
//        }
//        return validateStatusCode;
//    }
//
//
//    public int validateParams(String nic, String password, String email, String mobileNo, String title, String description) {
//        Validator validator = new Validator();
//
//        if (!validator.validateNIC(nic)) {
//            return 1;
//        } else if (!validator.validatePassword(password)) {
//            return 2;
//        } else if (!validator.validateEmail(email)) {
//            return 3;
//        } else if (!validator.validateMobileNo(mobileNo)) {
//            return 4;
//        } else if (!validator.validateTitle(title)) {
//            return 5;
//        } else if (!validator.validateDescription(description)) {
//            return 6;
//        }
//        return 0;
//    }
//}
