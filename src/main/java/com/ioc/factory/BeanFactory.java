package com.ioc.factory;

import com.ioc.model.BeanDefinition;

import java.util.List;

public interface BeanFactory {

    <T> T createBean(BeanDefinition beanDefinition);

    <T> T createBean(Class<T> beanClass);

    void addBeanDefinitions(List<BeanDefinition> beanDefinitionList);
}
