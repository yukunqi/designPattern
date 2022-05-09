package com.ioc.context;

public interface ApplicationContext {

    <T> T getBean(Class<T> beanClass);
}
