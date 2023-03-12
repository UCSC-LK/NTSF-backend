package com.cops.ntsf.model;

import com.cops.ntsf.dao.OffenceDAO;
import org.json.JSONArray;

public class Offence {
    private int offence_no;
    private String offence_type;
    private String description;
    private int amount;

    private int demerit_points;
    public Offence()
    {

    }
public Offence(String offence_type, String description, int amount, int demerit_points) {
        this.offence_type = offence_type;
        this.description = description;
        this.amount = amount;
        this.demerit_points = demerit_points;
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

    public int getAmount() {
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


    public JSONArray getOffenceDetails() {
        OffenceDAO offenceDAO = new OffenceDAO();
        JSONArray offenceDetailslist = offenceDAO.getOffenceDetailsList();
        return offenceDetailslist;
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
}
