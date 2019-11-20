package net.thumbtack.school.colors;

public enum ColorErrorCode {

    WRONG_COLOR_STRING("Wrong color"),
    NULL_COLOR("null color");

    private String errorString;

    ColorErrorCode(String errorString) {

        this.errorString = errorString;
    }

    public String getErrorString() {

        return errorString;
    }
}
