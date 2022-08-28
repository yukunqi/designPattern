package com.ioc.exception;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2022-08-28 15:49
 **/
public class CircleDependencyException extends RuntimeException{

    public CircleDependencyException() {
    }

    public CircleDependencyException(String message) {
        super(message);
    }

    public CircleDependencyException(String message, Throwable cause) {
        super(message, cause);
    }

    public CircleDependencyException(Throwable cause) {
        super(cause);
    }

    public CircleDependencyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
