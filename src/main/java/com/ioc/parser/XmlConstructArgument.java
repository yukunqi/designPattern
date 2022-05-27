package com.ioc.parser;

import com.ioc.model.BeanConstructor;

import javax.xml.bind.annotation.XmlAttribute;

public class XmlConstructArgument {

    @XmlAttribute(name = "type")
    private String typeOfClass;
    @XmlAttribute(name = "value")
    private String value;
    @XmlAttribute(name = "index")
    private Integer index;
    @XmlAttribute(name = "ref")
    private String ref;

    public XmlConstructArgument() {
    }


    public String getTypeOfClass() {
        return typeOfClass;
    }

    public void setTypeOfClass(String typeOfClass) {
        this.typeOfClass = typeOfClass;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public BeanConstructor createBeanConstructor(){
        if (this.ref != null){
            return new BeanConstructor(this.ref, this.index);
        }

        Class<?> classByName;
        try {
            classByName = Class.forName(this.typeOfClass);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Object objectByClass = createObjectByClass(classByName, this.value);
        return new BeanConstructor(this.index,classByName,objectByClass);
    }

    private Object createObjectByClass(Class<?> cls,String xmlValue){
        if (String.class == cls){
            return String.valueOf(xmlValue);
        } else if (Integer.class == cls) {
            return Integer.valueOf(xmlValue);
        }else {
            return xmlValue;
        }
    }
}
