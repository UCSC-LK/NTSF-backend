package com.cops.ntsf.service;

import com.cops.ntsf.model.Point;

import java.sql.SQLException;

public class PointService {
    public Point getPointInfo(String nic) {
        Point point = new Point(nic);
        try {
            point.getPointInfo();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return point;
    }
}
