package net.thumbtack.school.figures.v2;

abstract class Figure implements Colored {

    private int color;

    Figure(int color) {

        this.color = color;
    }


    @Override
    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public int getColor() {
        return color;
    }

    abstract double getArea();

    abstract void moveRel(int dx, int dy);

    abstract boolean isInside(int x, int y);

    abstract boolean isInside(Point2D point);
}
