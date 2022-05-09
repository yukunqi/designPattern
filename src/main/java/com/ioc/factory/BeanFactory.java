package com.ioc.factory;

import com.ioc.model.BeanDefinition;

public interface BeanFactory {

    <T> T createBean(BeanDefinition beanDefinition);
}
