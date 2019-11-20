package net.thumbtack.school.colors;


public enum Color {

    RED, GREEN, BLUE;

    public static Color colorFromString(String colorString) throws ColorException {

        try {
            Color.valueOf(colorString);
        } catch (IllegalArgumentException e) {
            throw new ColorException(ColorErrorCode.WRONG_COLOR_STRING);
        } catch (NullPointerException e) {
            throw new ColorException((ColorErrorCode.NULL_COLOR));
        }
        return Color.valueOf(colorString);
    }
}
