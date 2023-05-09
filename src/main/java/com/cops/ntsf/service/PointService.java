package com.cops.ntsf.service;

import com.cops.ntsf.model.PointData;

public class PointService {
    public void reducePoints(Integer demeritPoints, String licenseNo) {
        PointData pointData = new PointData();
        int currentPoints = pointData.getCurrentPointsByNic(licenseNo);

        if (checkCurrentPoints(currentPoints)) {
            // Reduce points
            currentPoints = currentPoints - demeritPoints;
            pointData.updateCurrentPoints(currentPoints);
        } else {
            System.out.println("Licence got declined");
        }
    }

    /**
     * Check whether current point is less than or equal to 0
     *
     * @param currentPoints Current points
     * @return If current points greater than 0
     */
    public boolean checkCurrentPoints(int currentPoints) {
        return currentPoints > 0;
    }

//    public PointData reducePoints(String nic, String fineNo) {
//        Fine fine = new Fine(Integer.parseInt(fineNo), nic);
//        if (fine.getFineByFineNo() != null) {
//            Offence offence = new Offence(fine.getOffenceNo());
//            offence.getOffence();
//            int pointsToBeDone = offence.getDemerit_points();
//
//            PointData pointData = new PointData();
//            int currentPoints = pointData.getCurrentPoints();
//
//            if (checkCurrentPoints(currentPoints)) {
//                // Reduce points
//                currentPoints = currentPoints - pointsToBeDone;
//                pointData.updateCurrentPoints(currentPoints);
//            } else
//                System.out.println("Licence got declined");
//
//            return pointData;
//        }
//
//        PointData pointData = new PointData(nic);
//        try {
//            pointData.getPointInfo();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        return pointData;
//    }
}

