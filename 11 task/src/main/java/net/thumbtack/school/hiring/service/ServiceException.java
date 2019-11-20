package net.thumbtack.school.hiring.service;

public class ServiceException extends java.lang.Exception {

    private ErrorCode errorCode;

    public ServiceException(ErrorCode errorCode) {

        this.errorCode = errorCode;
    }

     ErrorCode getErrorCode() {

        return errorCode;
    }
}
