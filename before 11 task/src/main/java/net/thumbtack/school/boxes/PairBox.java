package net.thumbtack.school.boxes;

import net.thumbtack.school.area.HasArea;
import net.thumbtack.school.figures.v3.Figure;

class PairBox<T extends Figure, V extends Figure> implements HasArea {

    private T first;
    private V second;


    PairBox(T first, V second) {

        this.first = first;
        this.second = second;
    }

    public T getFirst() {

        return first;
    }

    public void setFirst(T first) {

        this.first = first;
    }


    public V getSecond() {

        return second;
    }

    public void setSecond(V second) {

        this.second = second;
    }

    @Override
    public double getArea() {

        return second.getArea() + first.getArea();
    }

    public boolean isAreaEqual(PairBox pairBox) {

        return this.getArea() == pairBox.getArea();
    }

    public static boolean isAreaEqual(PairBox pairBox_1, PairBox pairBox_2) {

        return pairBox_1.getArea() == pairBox_2.getArea();
    }
}
