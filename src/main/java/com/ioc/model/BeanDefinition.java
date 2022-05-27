package com.ioc.model;

import java.util.List;

public class BeanDefinition {
    private String beanId;
    private Class<?> beanClass;
    private List<BeanConstructor> beanConstructorArgumentList;
    private String scope;

    private boolean isLazyInit;


    public BeanDefinition(String beanId, Class<?> beanClass, String scope, boolean isLazyInit) {
        this.beanId = beanId;
        this.beanClass = beanClass;
        this.scope = scope;
        this.isLazyInit = isLazyInit;
    }

    public boolean isSingleton(){
        return scope == null || "SINGLETON".equals(scope);
    }

    public String getBeanId() {
        return beanId;
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

    public List<BeanConstructor> getBeanConstructorArgumentList() {
        return beanConstructorArgumentList;
    }

    public String getScope() {
        return scope;
    }

    public boolean isLazyInit() {
        return isLazyInit;
    }

    public void setBeanConstructorArgumentList(List<BeanConstructor> beanConstructorArgumentList) {
        this.beanConstructorArgumentList = beanConstructorArgumentList;
    }

    public static class BeanDefinitionBuilder{
        private String beanId;
        private Class<?> beanClass;
        private List<BeanConstructor> beanConstructorArgumentList;
        private String scope;
        private Boolean isLazyInit;

        private static final String DEFAULT_SCOPE = "SING";
        private static final boolean DEFAULT_LAZY_INIT = false;

        public BeanDefinitionBuilder(){}

        public BeanDefinitionBuilder setBeanId(String beanId){
            if (beanId == null){
                throw new IllegalArgumentException();
            }

            this.beanId = beanId;

            return this;
        }

        public BeanDefinitionBuilder setBeanClass(String beanClassName){
            if (beanClassName == null){
                throw new IllegalArgumentException();
            }

            try {
                this.beanClass = Class.forName(beanClassName);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            return this;
        }

        public BeanDefinitionBuilder setScope(String scope){
            this.scope = scope;
            return this;
        }

        public BeanDefinitionBuilder setIsLazyInit(Boolean isLazyInit){
            this.isLazyInit = isLazyInit;
            return this;
        }

        public BeanDefinitionBuilder setBeanConstructorArgumentList(List<BeanConstructor> beanConstructorArgumentList){
            this.beanConstructorArgumentList = beanConstructorArgumentList;
            return this;
        }

        public BeanDefinition build(){
            if (this.scope == null){
                this.scope = DEFAULT_SCOPE;
            }

            if (this.isLazyInit == null){
                this.isLazyInit = DEFAULT_LAZY_INIT;
            }

            BeanDefinition beanDefinition = new BeanDefinition(this.beanId, this.beanClass, this.scope, this.isLazyInit);
            beanDefinition.setBeanConstructorArgumentList(this.beanConstructorArgumentList);

            return beanDefinition;
        }

    }
}
