package com.cops.ntsf.model;

import com.cops.ntsf.dao.OffenceDAO;
import org.json.JSONArray;

public class Offence {
    private Integer offence_no;
    private String offence_type;
    private String description;
    private Integer amount;

    private Integer demerit_points;
    public Offence()
    {

    }
public Offence(Integer offence_no, String offence_type, String offence_name, String description, Integer amount) {
        this.offence_no = offence_no;
        this.offence_type = offence_type;
        this.description = description;
        this.amount = amount;
        this.demerit_points = demerit_points;
    }

    public Integer getOffence_no() {
        return offence_no;
    }

    public String getOffence_type() {
        return offence_type;
    }

    public String getDescription() {
        return description;
    }

    public Integer getAmount() {
        return amount;
    }
    public Integer getDemerit_points() {
        return demerit_points;
    }
    public void setOffence_no(Integer offence_no) {
        this.offence_no = offence_no;
    }
    public void setOffence_type(String offence_type) {
        this.offence_type = offence_type;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    public void setDemerit_points(Integer demerit_points) {
        this.demerit_points = demerit_points;
    }


    public JSONArray getOffenceDetails() {
        OffenceDAO offenceDAO = new OffenceDAO();
        JSONArray offenceDetailslist = offenceDAO.getOffenceDetailsList();
        return offenceDetailsList;
    }
}
