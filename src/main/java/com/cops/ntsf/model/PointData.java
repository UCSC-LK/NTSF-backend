package com.cops.ntsf.model;

import com.cops.ntsf.dao.PointSystemDAO;

import java.sql.Date;

public class PointData {
    private String nic;

    private Integer maxPointLimit;

    private Integer minPointLimit;

    private int initialPoints;

    private int currentPoints;

    private Date maxRecoveryDate;

    public PointData(String nic) {
        this.nic = nic;
    }

    public PointData() {
    }

    public void getPointInfo(String nic) {
        PointSystemDAO pointSystemDAO = new PointSystemDAO();
        pointSystemDAO.getPointInfo(this);
    }

    public String getNic() {
        return nic;
    }

    public void setMaxPointLimit(Integer maxPointLimit) {
        this.maxPointLimit = maxPointLimit;
    }

    public void setMinPointLimit(Integer minPointLimit) {
        this.minPointLimit = minPointLimit;
    }

    public Integer getMaxPointLimit() {
        return maxPointLimit;
    }

    public Integer getMinPointLimit() {
        return minPointLimit;
    }

    public void setInitialPoints(int initialPoints) {
        this.initialPoints = initialPoints;
    }

    public int getInitialPoints() {
        return initialPoints;
    }

    public void setCurrentPoints(int currentPoints) {
        this.currentPoints = currentPoints;
    }

    public int getRemainingPoints() {
        return currentPoints;
    }

    public void setMaxRecoveryDate(Date maxRecoveryDate) {
        this.maxRecoveryDate = maxRecoveryDate;
    }

    public Date getMaxRecoveryDate() {
        return maxRecoveryDate;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public int getCurrentPoints() {
        return currentPoints;
    }

    public int getCurrentPointsByNic(String nic) {
        PointSystemDAO pointSystemDAO = new PointSystemDAO();
        return pointSystemDAO.fetchCurrentPointsByNic(this);
    }

    public void updateCurrentPoints(int currentPoints) {
        PointSystemDAO pointSystemDAO = new PointSystemDAO();
        pointSystemDAO.updateCurrentPoints(this);
    }
}
