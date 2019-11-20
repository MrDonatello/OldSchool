package net.thumbtack.school.boxes;

import net.thumbtack.school.area.HasArea;
import net.thumbtack.school.colors.ColorException;
import net.thumbtack.school.figures.v3.Figure;


class Box<T extends Figure> implements HasArea {

    private T obj;


    Box(T obj) {

        this.obj = obj;
    }


    public T getContent() {

        return obj;
    }

    public void setContent(T obj) {

        this.obj = obj;
    }

    @Override
    public double getArea() {

        return obj.getArea();
    }

    public boolean isAreaEqual(Box<?> box) {

        return this.getArea() == box.getArea();
    }

    public static boolean isAreaEqual(Box<?> box_1, Box<?> box_2) throws ColorException {

        return box_1.getArea() == box_2.getArea();
    }
}
