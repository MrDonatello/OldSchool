package net.thumbtack.school.figures.v1;

public class CircleFactory {

    private static int count;

    public static Circle createCircle(Point2D center, int radius) {
        count++;
        return new Circle(center, radius);
    }

    public static int getCircleCount() {

        return count;
    }

    public static void reset() {

        count = 0;
    }
}
