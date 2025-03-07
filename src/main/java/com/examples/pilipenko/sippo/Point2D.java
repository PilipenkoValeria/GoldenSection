package com.examples.pilipenko.sippo;

import java.util.Objects;

public class Point2D {
    private double x, y;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point2D() {
        x = 0;
        y = 0;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    public void print (){
        System.out.println("Coordinates : (" + x + "; " + y + ") ");
    }

    public Point2D (String coordinates) {
        try {
            int start = coordinates.indexOf("(");
            int end = coordinates.indexOf(")");
            int middle = coordinates.indexOf(";");
            this.x = Double.parseDouble(coordinates.substring(start + 1, middle));
            this.y = Double.parseDouble(coordinates.substring(middle + 1, end));
        }
        catch (Exception e) {
            throw new  IllegalArgumentException("Неверные данные");
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point2D point2D = (Point2D) o;
        return Double.compare(point2D.x, x) == 0 &&
                Double.compare(point2D.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return  "(" + x +
                ", " + y + ')';
    }
}
