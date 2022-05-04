package com.designPattern.exception;

public class StatFormatException extends RuntimeException{
    public StatFormatException() {
    }

    public StatFormatException(String message) {
        super(message);
    }

    public StatFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public StatFormatException(Throwable cause) {
        super(cause);
    }

    public StatFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
