package com.examples.pilipenko.sippo;

import java.io.*;
import java.util.function.Function;


public class GoldenSection {
    final private double tau = (1 + Math.sqrt(5)) / 2;

    public Point2D findExtremumMin(String fileName, Function<Double, Double> function) {
        try (FileReader fr = new FileReader(fileName); BufferedReader br = new BufferedReader(fr)) {
            String line = br.readLine();
            int startOfA = line.indexOf("(");
            int endOfA = line.indexOf(")");
            String a = line.substring(startOfA, endOfA + 1);

            int startOfB = line.indexOf("(", endOfA);
            int endOfB = line.indexOf(")", endOfA + 1);
            String b = line.substring(startOfB, endOfB + 1);

            Point2D left = new Point2D(a);
            Point2D right = new Point2D(b);

            double e = Double.parseDouble(line.substring(endOfB + 1));
            double x1, x2;

            Point2D point1 = new Point2D();
            Point2D point2 = new Point2D();

            if (left.getX() > right.getX()) {
                Point2D buffer = right;
                right = left;
                left = buffer;
            }
            do {
                x1 = right.getX() - (right.getX() - left.getX()) * tau;
                x2 = left.getX() + (right.getX() - left.getX()) * tau;

                point1.setX(x1);
                point1.setY(function.apply(x1));
                point2.setX(x2);
                point2.setY(function.apply(x2));

                if (function.apply(x1) >= function.apply(x2)) {
                    left.setX(point1.getX());
                } else {
                    right.setX(point2.getX());
                }
            } while (Math.abs(right.getX()) - left.getX() >= e);

            return new Point2D((left.getX() + right.getX()) / 2, function.apply((left.getX() + right.getX()) / 2));

        } catch (IOException ex) {
            throw new IllegalArgumentException("Неправильное имя файла или входной файл");
        }

    }


}

