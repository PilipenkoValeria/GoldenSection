package com.examples.pilipenko.sippo;

import org.junit.Test;
import java.util.function.Function;
import static org.junit.Assert.*;

public class GoldenSectionTest {
    @Test
    public void constructorTest0() {
        Point2D point = new Point2D(0, 1);
        point.print();
        assertEquals(0.0, point.getX(), 0.001);
        assertEquals(1.0, point.getY(), 0.001);
    }

    @Test
    public void constructorTest1() {
        Point2D point = new Point2D();
        assertEquals(0.0, point.getX(), 0.001);
        assertEquals(0.0, point.getY(), 0.001);
    }

    @Test
    public void constructorTest2() {
        String str1 = "(0;1)";
        String str2 = "(0.0;1.0)";
        String str3 = " ( 0 ;  1  ) ";
        Point2D point1 = new Point2D(str1);
        Point2D point2 = new Point2D(str2);
        Point2D point3 = new Point2D(str3);

        assertEquals(0, point1.getX(), 0.001);
        assertEquals(0, point2.getX(), 0.001);
        assertEquals(0, point3.getX(), 0.001);
        assertEquals(1, point1.getY(), 0.001);
        assertEquals(1, point2.getY(), 0.001);
        assertEquals(1, point3.getY(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorTest3() {
        String str1 = "        ";
        Point2D point1 = new Point2D(str1);
    }

    @Test
    public void equalsTest() {
        Point2D point1 = new Point2D(0, 1);
        Point2D point2 = new Point2D(0, 1);
        Point2D point3 = new Point2D(0, 0);
        Point2D point4 = new Point2D();

        assertEquals(point1, point2);
        assertEquals(point3, point4);
        assertNotEquals(point1, point3);
    }

    @Test
    public void GoldenSectionTest() {
        GoldenSection goldenSection = new GoldenSection();
        Function<Double, Double> function1 = (x) -> {
            return 2 * Math.pow(x, 2) + 3 * Math.exp(-x);//2x^2+3e^(-x)
        };
        Function<Double, Double> function2 = (x) -> {
            return 3 * Math.pow(x, 2) + 4 * Math.exp(x);//3x^2+4e^(x)
        };
        Function<Double, Double> function3 = (x) -> {
            return Math.log(x) * x - Math.pow(x,3) + 7;//log(x)*x-x^3+7
        };

        assertEquals( 0.46910939945091, goldenSection.findExtremumMin("in.txt", function1).getX(), 0.001);
        assertEquals( 2.3168, goldenSection.findExtremumMin("in.txt", function1).getY(), 0.001);

        assertEquals(0.00036677693739328, goldenSection.findExtremumMin("in.txt", function2).getX(), 0.001);
        assertEquals(4.0015, goldenSection.findExtremumMin("in.txt", function2).getY(), 0.001);

        assertEquals(0.99963322306261, goldenSection.findExtremumMin("in.txt", function3).getX(), 0.001);
        assertEquals(6.0009, goldenSection.findExtremumMin("in.txt", function3).getY(), 0.001);
    }
}
