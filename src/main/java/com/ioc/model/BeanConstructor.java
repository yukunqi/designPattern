package com.ioc.model;

public class BeanConstructor {
    private boolean isRef;
    private String refBeanId;
    private int index;
    private Class<?> type;
    private Object value;

    public BeanConstructor(String refBeanId, int index) {
        this.refBeanId = refBeanId;
        this.index = index;
        this.isRef = true;
    }

    public BeanConstructor(int index, Class<?> type, Object value) {
        this.index = index;
        this.type = type;
        this.value = value;
        this.isRef = false;
    }

    public boolean isRef() {
        return isRef;
    }

    public String getRefBeanId() {
        return refBeanId;
    }

    public int getIndex() {
        return index;
    }

    public Class<?> getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }
}
