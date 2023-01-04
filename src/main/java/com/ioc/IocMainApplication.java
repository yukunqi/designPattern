package com.ioc;

import com.ioc.bean.RateLimiter;
import com.ioc.context.ApplicationContext;
import com.ioc.context.ClassPathXmlApplicationContext;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2022-08-28 15:56
 **/
public class IocMainApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext();
        RateLimiter rateLimiter = applicationContext.getBean(RateLimiter.class);
        rateLimiter.test();
    }
}
