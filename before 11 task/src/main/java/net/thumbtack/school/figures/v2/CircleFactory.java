package net.thumbtack.school.figures.v2;


public class CircleFactory {

    private static int count;

    public static Circle createCircle(Point2D center, int radius, int color) {
        count++;
        return new Circle(center, radius, color);
    }

    public static int getCircleCount() {

        return count;
    }

    public static void reset() {

        count = 0;
    }
}
