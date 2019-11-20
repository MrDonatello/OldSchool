package net.thumbtack.school.boxes;

import net.thumbtack.school.figures.v3.Figure;

class ArrayBox<T extends Figure> {

    private T[] arrayBox;
    private int i;
    private int length;


    ArrayBox(T[] arrayBox) {

        this.arrayBox = arrayBox;
        this.length = arrayBox.length;
    }

    public T[] getContent() {

        return arrayBox;
    }

    public void setContent(T[] arrayBox) {

        this.arrayBox = arrayBox;
    }

    public T getElement() {

        return arrayBox[i];
    }

    public void setElement(int i) {

        this.i = i;
    }

    public boolean isSameSize(ArrayBox<?> arrayBox) {

        return this.arrayBox.length == arrayBox.length;
    }
}
