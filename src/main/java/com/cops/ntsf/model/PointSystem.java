package com.cops.ntsf.model;

import java.util.ArrayList;

public class PointSystem {
    private ArrayList<Point> points;

    public PointSystem() {
        this.points = new ArrayList<Point>();
    }

    public void addPoint(Point point) {
        this.points.add(point);
    }

    public void removePoint(Point point) {
        this.points.remove(point);
    }

    public double distanceBetween(Point point1, Point point2) {
        return point1.distanceTo(point2);
    }

    public ArrayList<Point> getPoints() {
        return this.points;
    }
}