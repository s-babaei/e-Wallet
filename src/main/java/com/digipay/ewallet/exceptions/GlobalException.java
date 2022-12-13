package com.digipay.ewallet.exceptions;

public class GlobalException extends RuntimeException{

    private final String exceptionType;

    public GlobalException(String exceptionType) {
        super();
        this.exceptionType = exceptionType;
    }



    public GlobalException(String message, String exceptionType) {
        super(message);
        this.exceptionType = exceptionType;
    }

    public GlobalException(String message, Throwable cause, String exceptionType) {
        super(message, cause);
        this.exceptionType = exceptionType;
    }

    public GlobalException(Throwable cause, String exceptionType) {
        super(cause);
        this.exceptionType = exceptionType;
    }

    protected GlobalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String exceptionType) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.exceptionType = exceptionType;
    }

    public String getExceptionType() {
        return exceptionType;
    }
}
