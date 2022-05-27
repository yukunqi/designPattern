package com.ioc.parser;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "beans")
public class XmlBeans {

    @XmlElement(name = "bean")
    private List<XmlBean> xmlBeanList;

    public XmlBeans() {
    }

    public List<XmlBean> getXmlBeanList() {
        return xmlBeanList;
    }

    public void setXmlBeanList(List<XmlBean> xmlBeanList) {
        this.xmlBeanList = xmlBeanList;
    }
}
