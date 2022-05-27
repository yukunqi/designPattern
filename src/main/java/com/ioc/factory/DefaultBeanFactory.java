package com.ioc.factory;

import com.ioc.model.BeanConstructor;
import com.ioc.model.BeanDefinition;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class DefaultBeanFactory implements BeanFactory{

    private Map<String,Object> beanObjectMap = new ConcurrentHashMap<>();
    private Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public DefaultBeanFactory() {
    }

    @Override
    public <T> T createBean(BeanDefinition beanDefinition) {
        if (beanDefinition.isSingleton()){
            Object bean = beanObjectMap.get(beanDefinition.getBeanId());
            if (bean != null){
                return (T) bean;
            }
        }

        List<BeanConstructor> beanConstructorArgumentList = beanDefinition.getBeanConstructorArgumentList();

        Class<?> [] classes = new Class[beanConstructorArgumentList.size()];
        Object [] objects = new Object[beanConstructorArgumentList.size()];

        //sort by construct index asc
        beanConstructorArgumentList = beanConstructorArgumentList
                .stream()
                .sorted(Comparator.comparingInt(BeanConstructor::getIndex))
                .collect(Collectors.toList());

        for (int i = 0; i < beanConstructorArgumentList.size(); i++) {
            BeanConstructor beanConstructor = beanConstructorArgumentList.get(i);

            if (beanConstructor.isRef()){
                BeanDefinition def = beanDefinitionMap.get(beanConstructor.getRefBeanId());
                if (def == null){
                    //todo throw BeanDefinition not found Exception
                    throw new RuntimeException();
                }

                //fixme bean create circle problem
                Object bean = createBean(def);
                classes[i] = def.getBeanClass();
                objects[i] = bean;
            }else {
                classes[i] = beanConstructor.getType();
                objects[i] = beanConstructor.getValue();
            }
        }

        Class<?> beanClass = beanDefinition.getBeanClass();

        try {
            Object newInstance = beanClass.getConstructor(classes).newInstance(objects);
            beanObjectMap.putIfAbsent(beanDefinition.getBeanId(),newInstance);
            return (T) newInstance;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void addBeanDefinitions(List<BeanDefinition> beanDefinitionList){
        if (beanDefinitionList == null){
            throw new IllegalArgumentException("beanDefinition can not be null");
        }

        for (BeanDefinition beanDefinition : beanDefinitionList){
            beanDefinitionMap.putIfAbsent(beanDefinition.getBeanId(),beanDefinition);
        }

        for (BeanDefinition beanDefinition : beanDefinitionList){
            if (!beanDefinition.isLazyInit() && beanDefinition.isSingleton()){
                createBean(beanDefinition);
            }
        }
    }


}
