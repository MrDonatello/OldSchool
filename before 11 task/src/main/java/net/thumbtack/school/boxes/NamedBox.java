package net.thumbtack.school.boxes;


import net.thumbtack.school.figures.v3.Figure;

public class NamedBox<T extends Figure> extends Box {

    private String name;


    NamedBox(T ob, String name) {
        super(ob);
        this.name = name;
    }

    @Override
    public T getContent() {

        return (T) super.getContent();
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }
}
