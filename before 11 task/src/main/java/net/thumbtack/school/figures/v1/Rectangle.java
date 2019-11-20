package net.thumbtack.school.figures.v1;

import java.util.Objects;

public class Rectangle {


    private Point2D leftTop;
    private Point2D rightBottom;


    public Rectangle(Point2D leftTop, Point2D rightBottom) {

        this.leftTop = leftTop;
        this.rightBottom = rightBottom;
    }

    public Rectangle(int xLeft, int yTop, int xRight, int yBottom) {

        this.leftTop = new Point2D(xLeft, yTop);
        this.rightBottom = new Point2D(xRight, yBottom);
    }

    public Rectangle(int length, int width) {

        this.leftTop = new Point2D(0, 0 - width);
        this.rightBottom = new Point2D(length, 0);
    }

    public Rectangle() {

        this.leftTop = new Point2D(0, -1);
        this.rightBottom = new Point2D(1, 0);
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
        return Objects.equals(leftTop, rectangle.leftTop) &&
                Objects.equals(rightBottom, rectangle.rightBottom);
    }

    @Override
    public int hashCode() {

        return Objects.hash(leftTop, rightBottom);
    }
}
