package com.ioc.parser;

import com.ioc.model.BeanConstructor;
import com.ioc.model.BeanDefinition;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XmlBeanDefinitionParser implements BeanDefinitionParser{


    @Override
    public List<BeanDefinition> parseBean(InputStream inputStream) {

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(XmlBeans.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            XmlBeans xmlBeans = (XmlBeans) unmarshaller.unmarshal(inputStream);

            List<BeanDefinition> beanDefinitionList = new ArrayList<>();
            List<XmlBean> xmlBeanList = xmlBeans.getXmlBeanList();
            for (XmlBean xmlBean : xmlBeanList){

                List<BeanConstructor> beanConstructorList = new ArrayList<>();
                List<XmlConstructArgument> xmlConstructArgumentList = xmlBean.getXmlConstructArgumentList();
                for (int i = 0; i < xmlConstructArgumentList.size(); i++) {
                    XmlConstructArgument xmlConstructArgument = xmlConstructArgumentList.get(i);
                    if (xmlConstructArgument.getIndex() == null){
                        xmlConstructArgument.setIndex(i);
                    }

                    BeanConstructor beanConstructor = xmlConstructArgument.createBeanConstructor();
                    beanConstructorList.add(beanConstructor);
                }

                BeanDefinition beanDefinition = new BeanDefinition.BeanDefinitionBuilder()
                        .setBeanId(xmlBean.getId())
                        .setBeanClass(xmlBean.getCls())
                        .setScope(xmlBean.getScope())
                        .setIsLazyInit(xmlBean.isLazyInit())
                        .setBeanConstructorArgumentList(beanConstructorList)
                        .build();

                beanDefinitionList.add(beanDefinition);
            }

            return beanDefinitionList;
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    @Override
    public List<BeanDefinition> parseBean(String beanConfigContent) {
        return null;
    }
}
