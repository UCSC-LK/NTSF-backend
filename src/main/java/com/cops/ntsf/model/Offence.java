package com.cops.ntsf.model;

import com.cops.ntsf.dao.OffenceDAO;
import org.json.JSONArray;

public class Offence {
    private int offence_no;
    private String offence_type;
    private String description;
    private int amount;

    private int demerit_points;

    public Offence() {

    }

    public Offence(int offence_no, String offence_type, String description, int amount, int demerit_points) {
        this.offence_no = offence_no;
        this.offence_type = offence_type;
        this.description = description;
        this.amount = amount;
        this.demerit_points = demerit_points;
    }

    public Offence(String speeding, int i, int i1) {
    }

    public Offence(Integer offenceNo) {
        this.offence_no = offenceNo;
    }

    public int getOffence_no() {
        return offence_no;
    }

    public String getOffence_type() {
        return offence_type;
    }

    public String getDescription() {
        return description;
    }

    public int getAmount(int amount) {
        return amount;
    }

    public int getDemerit_points() {
        return demerit_points;
    }

    public void setOffence_no(int offence_no) {
        this.offence_no = offence_no;
    }

    public void setOffence_type(String offence_type) {
        this.offence_type = offence_type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setDemerit_points(int demerit_points) {
        this.demerit_points = demerit_points;
    }


    public JSONArray getOffenceDetailsByType(String offenceType) {
        OffenceDAO offenceDAO = new OffenceDAO();
        JSONArray offenceDetailslistByType = offenceDAO.getOffenceDetailsListByType(offenceType);
        return offenceDetailslistByType;
    }

    public void offenceAdded() {
        OffenceDAO offenceDAO = new OffenceDAO();
        offenceDAO.createOffence(this);
    }

    public boolean deleteOffenceDetails(int offence_no) {
        OffenceDAO offenceDAO = new OffenceDAO();
        boolean deleteOffenceDetailResult = offenceDAO.deleteOffence(offence_no);
        return deleteOffenceDetailResult;
    }

    public boolean offenceDescriptionCheck(String description) {
        OffenceDAO offenceDAO = new OffenceDAO();
        boolean offenceDescriptionCheckResult = offenceDAO.offenceDescriptionCheck(description);
        return offenceDescriptionCheckResult;
    }

    public Offence(String offence_no) {
        this.offence_no = Integer.parseInt(offence_no);
    }

    public void getOffence() {
        OffenceDAO offenceDAO = new OffenceDAO();
        offenceDAO.fetchOffenceByOffenceNo(this);
    }

    public int getAmount() {
        return amount;
    }

    public int OffenceNoGet(String offence_type) {
        System.out.println("Came to OffenceNoGet in Offence mOdel");
        System.out.println("Offence Type: " + offence_type);
        OffenceDAO offenceDAO = new OffenceDAO();
        int offenceNo = offenceDAO.fetchOffenceNo(offence_type);
        return offenceNo;
    }
}
