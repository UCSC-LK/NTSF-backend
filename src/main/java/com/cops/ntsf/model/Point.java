package com.cops.ntsf.model;

import com.cops.ntsf.dao.PointSystemDAO;

import java.sql.Date;
import java.sql.SQLException;

public class Point {
    private double x;
    private double y;
    private String nic;
    private Integer maxPointLimit;
    private Integer minPointLimit;
    private int initialPoints;
    private int remainingPoints;
    private Date maxRecoveryDate;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point(String nic) {

    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double distanceTo(Point other) {
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    public void getPointInfo() throws SQLException {
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

    public void setMinPointLimit(int min_point_limit) {
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

    public void setRemainingPoints(int remainingPoints) {
        this.remainingPoints = remainingPoints;
    }

    public int getRemainingPoints() {
        return remainingPoints;
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
}
