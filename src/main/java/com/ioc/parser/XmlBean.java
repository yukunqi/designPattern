package com.ioc.parser;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

public class XmlBean {
    @XmlAttribute(name = "id")
    private String id;
    @XmlAttribute(name = "class")
    private String cls;
    @XmlAttribute(name = "scope")
    private String scope;
    @XmlAttribute(name = "lazy-init")
    private Boolean lazyInit;

    @XmlElement(name = "constructor-arg")
    private List<XmlConstructArgument> xmlConstructArgumentList;

    public XmlBean() {
    }

    @XmlTransient
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlTransient
    public String getCls() {
        return cls;
    }

    public void setCls(String cls) {
        this.cls = cls;
    }

    @XmlTransient
    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @XmlTransient
    public Boolean isLazyInit() {
        return this.lazyInit;
    }

    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }

    @XmlTransient
    public List<XmlConstructArgument> getXmlConstructArgumentList() {
        return xmlConstructArgumentList;
    }

    public void setXmlConstructArgumentList(List<XmlConstructArgument> xmlConstructArgumentList) {
        this.xmlConstructArgumentList = xmlConstructArgumentList;
    }
}
