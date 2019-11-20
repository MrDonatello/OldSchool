package net.thumbtack.school.figures.v3;

        import net.thumbtack.school.colors.Color;
        import net.thumbtack.school.colors.ColorException;

public class CircleFactory {

    private static int count;

    public static Circle createCircle(Point2D center, int radius, String colorString) throws ColorException {

        count++;
        return new Circle(center, radius, colorString);
    }

    public static Circle createCircle(Point2D center, int radius, Color color) throws ColorException {

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
