package com.ioc.context;

import com.ioc.factory.BeanFactory;
import com.ioc.factory.DefaultBeanFactory;
import com.ioc.model.BeanDefinition;
import com.ioc.parser.BeanDefinitionParser;
import com.ioc.parser.XmlBeanDefinitionParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ClassPathXmlApplicationContext implements ApplicationContext{

    private BeanFactory beanFactory;
    private BeanDefinitionParser parser;

    @Override
    public <T> T getBean(Class<T> beanClass) {
        return null;
    }

    public ClassPathXmlApplicationContext() {
        this.beanFactory = new DefaultBeanFactory();
        this.parser = new XmlBeanDefinitionParser();
        this.load();
    }

    private void load(){
        try(InputStream resourceAsStream = this.getClass().getResourceAsStream("beans.xml");){
            List<BeanDefinition> beanDefinitionList = parser.parseBean(resourceAsStream);
            beanFactory.addBeanDefinitions(beanDefinitionList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
