package com.ioc.parser;

import com.ioc.model.BeanDefinition;

import java.io.InputStream;
import java.util.List;

public interface BeanDefinitionParser {

    List<BeanDefinition> parseBean(InputStream inputStream);

    List<BeanDefinition> parseBean(String beanConfigContent);
}
