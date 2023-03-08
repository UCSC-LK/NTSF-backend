package com.cops.ntsf.model;

public class Offence {
    private Integer offence_no;
    private String offence_type;
    private String offence_name;
    private String description;
    private Integer amount;

    public Offence()
    {

    }
public Offence(Integer offence_no, String offence_type, String offence_name, String description, Integer amount) {
        this.offence_no = offence_no;
        this.offence_type = offence_type;
        this.offence_name = offence_name;
        this.description = description;
        this.amount = amount;
    }

    public Integer getOffence_no() {
        return offence_no;
    }

    public String getOffence_type() {
        return offence_type;
    }

    public String getOffence_name() {
        return offence_name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setOffence_no(Integer offence_no) {
        this.offence_no = offence_no;
    }

    public void setOffence_type(String offence_type) {
        this.offence_type = offence_type;
    }

    public void setOffence_name(String offence_name) {
        this.offence_name = offence_name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }


}
