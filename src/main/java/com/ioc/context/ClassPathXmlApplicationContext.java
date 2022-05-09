package com.ioc.context;

import com.ioc.factory.BeanFactory;
import com.ioc.parser.BeanDefinitionParser;

public class ClassPathXmlApplicationContext implements ApplicationContext{

    private BeanFactory beanFactory;
    private BeanDefinitionParser parser;

    @Override
    public <T> T getBean(Class<T> beanClass) {
        return null;
    }

    private void load(){}
}
