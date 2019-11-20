package net.thumbtack.school.figures.v3;

import net.thumbtack.school.area.HasArea;
import net.thumbtack.school.colors.Color;
import net.thumbtack.school.colors.ColorErrorCode;
import net.thumbtack.school.colors.ColorException;
import net.thumbtack.school.colors.Colored;

import java.io.Serializable;

public abstract class Figure implements Colored, HasArea {

    private Color color;

    Figure(Color color) throws ColorException {

        this.color = color;
        if (color == null) {
            throw new ColorException((ColorErrorCode.NULL_COLOR));
        }
    }

    @Override
    public void setColor(Color color) throws ColorException {

        this.color = color;
        if (color == null) {
            throw new ColorException((ColorErrorCode.NULL_COLOR));
        }
    }


    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(String colorString) throws ColorException {

        this.color = Color.colorFromString(colorString);
    }

    abstract void moveRel(int dx, int dy);

    abstract boolean isInside(int x, int y);

    abstract boolean isInside(Point2D point);

}
