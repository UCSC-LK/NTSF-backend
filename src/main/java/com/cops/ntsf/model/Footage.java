package com.cops.ntsf.model;

import com.cops.ntsf.dao.FootageDAO;

public class Footage {
    private String complaintNo;
    private String footageFile;

    public void uploadFootage(String complaintNo, String footageFile) {
        FootageDAO footageDAO = new FootageDAO();
        footageDAO.uploadFootage(this);
    }

    public String getComplaintNo() {
        return complaintNo;
    }

    public void setComplaintNo(String complaintNo) {
        this.complaintNo = complaintNo;
    }

    public String getFootageFile() {
        return footageFile;
    }

    public void setFootageFile(String footageFile) {
        this.footageFile = footageFile;
    }
}
