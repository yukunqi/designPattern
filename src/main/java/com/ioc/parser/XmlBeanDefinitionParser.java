package com.ioc.parser;

import com.ioc.model.BeanDefinition;

import java.io.InputStream;
import java.util.List;

public class XmlBeanDefinitionParser implements BeanDefinitionParser{
    @Override
    public List<BeanDefinition> parseBean(InputStream inputStream) {
        return null;
    }

    @Override
    public List<BeanDefinition> parseBean(String beanConfigContent) {
        return null;
    }
}
