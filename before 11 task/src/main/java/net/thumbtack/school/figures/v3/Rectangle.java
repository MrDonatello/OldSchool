package net.thumbtack.school.figures.v3;

import net.thumbtack.school.colors.Color;
import net.thumbtack.school.colors.ColorException;

import java.io.Serializable;
import java.util.Objects;

public class Rectangle extends Figure {

    private Point2D leftTop;
    private Point2D rightBottom;


    public Rectangle(Point2D leftTop, Point2D rightBottom, Color color) throws ColorException {

        super(color);
        this.leftTop = leftTop;
        this.rightBottom = rightBottom;
    }

    public Rectangle(Point2D leftTop, Point2D rightBottom, String color) throws ColorException {

        this(leftTop, rightBottom, Color.colorFromString(color));
    }

    public Rectangle(int xLeft, int yTop, int xRight, int yBottom, Color color) throws ColorException {

        super(color);
        this.leftTop = new Point2D(xLeft, yTop);
        this.rightBottom = new Point2D(xRight, yBottom);
    }


    public Rectangle(int xLeft, int yTop, int xRight, int yBottom, String color) throws ColorException {

        this(xLeft, yTop, xRight, yBottom, Color.colorFromString(color));
    }

    public Rectangle(int length, int width, Color color) throws ColorException {

        super(color);
        this.leftTop = new Point2D(0, 0 - width);
        this.rightBottom = new Point2D(length, 0);
    }

    public Rectangle(int length, int width, String color) throws ColorException {

        this(length, width, Color.colorFromString(color));
    }

    public Rectangle(Color color) throws ColorException {

        super(color);
        this.leftTop = new Point2D(0, -1);
        this.rightBottom = new Point2D(1, 0);
    }

    public Rectangle(String color) throws ColorException {

        this(Color.colorFromString(color));
    }

    public Point2D getTopLeft() {

        return leftTop;
    }

    public Point2D getBottomRight() {

        return rightBottom;
    }

    public void setTopLeft(Point2D topLeft) {

        this.leftTop = topLeft;
    }

    public void setBottomRight(Point2D bottomRight) {

        this.rightBottom = bottomRight;
    }

    public int getLength() {

        return rightBottom.getX() - leftTop.getX();
    }

    public int getWidth() {

        return Math.abs(leftTop.getY() - rightBottom.getY());
    }

    public void moveRel(int dx, int dy) {

        leftTop.setX(leftTop.getX() + dx);
        leftTop.setY(leftTop.getY() + dy);
        rightBottom.setX(rightBottom.getX() + dx);
        rightBottom.setY(rightBottom.getY() + dy);
    }

    public void enlarge(int nx, int ny) {

        rightBottom.setX(getLength() * nx + leftTop.getX());
        rightBottom.setY(getWidth() * ny + leftTop.getY());
    }

    public double getArea() {

        return getLength() * getWidth();
    }

    public double getPerimeter() {

        return (getLength() + getWidth()) * 2;
    }

    public boolean isInside(int x, int y) {

        return x >= leftTop.getX() && x <= rightBottom.getX() && y >= leftTop.getY() && y <= rightBottom.getY();
    }

    public boolean isInside(Point2D point) {

        return isInside(point.getX(), point.getY());
    }

    public boolean isIntersects(Rectangle rectangle) {

        for (int i = 0; i < rectangle.getLength(); i++) {
            for (int j = 0; j < rectangle.getWidth(); j++) {
                if (isInside(rectangle.leftTop.getX() + i, rectangle.leftTop.getY() + j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isInside(Rectangle rectangle) {

        for (int i = 0; i < rectangle.getLength(); i++) {
            for (int j = 0; j < rectangle.getWidth(); j++) {
                if (!isInside(rectangle.leftTop.getX() + i, rectangle.leftTop.getY() + j)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return getColor() == rectangle.getColor() &&
                Objects.equals(leftTop, rectangle.leftTop) &&
                Objects.equals(rightBottom, rectangle.rightBottom);
    }

    @Override
    public int hashCode() {

        return Objects.hash(leftTop, rightBottom, getColor());
    }
}
