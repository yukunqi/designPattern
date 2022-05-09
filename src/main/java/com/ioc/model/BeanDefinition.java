package com.ioc.model;

import java.util.List;

public class BeanDefinition {
    private String beanId;
    private Class<?> beanClass;
    private List<BeanConstructor> beanConstructorArgumentList;
    private String beanType;
}
