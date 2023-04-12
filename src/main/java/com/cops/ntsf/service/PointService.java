package com.cops.ntsf.service;

import com.cops.ntsf.model.Fine;
import com.cops.ntsf.model.Offence;
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

    public Point reducePoints(String nic, String fineNo) {
        Fine fine = new Fine(Integer.parseInt(fineNo), nic);
        if (fine.getFineByFineNo() != null) {
            Offence offence = new Offence(fine.getOffenceNo());
            offence.getOffence();
            int pointsToBeDone = offence.getDemerit_points();

            Point point = new Point();
            int currentPoints = point.getCurrentPoints();

            // Reduce points
            currentPoints = currentPoints - pointsToBeDone;

            point.updateCurrentPoints(currentPoints);

            return point;
        }


        Point point = new Point(nic);
        try {
            point.getPointInfo();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return point;
    }
}
