package com.ioc.exception;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2022-08-28 14:59
 **/
public class BeanDefinitionNotFoundException extends RuntimeException{

    public BeanDefinitionNotFoundException() {
    }

    public BeanDefinitionNotFoundException(String message) {
        super(message);
    }

    public BeanDefinitionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BeanDefinitionNotFoundException(Throwable cause) {
        super(cause);
    }

    public BeanDefinitionNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
