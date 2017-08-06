package com.jovezhao.nest.utils;

import com.jovezhao.nest.exception.SystemException;
import com.jovezhao.nest.log.Log;
import com.jovezhao.nest.log.LogAdapter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SpringUtils {
    private static Log log=new LogAdapter(SpringUtils.class);

    private volatile static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        SpringUtils.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        if (SpringUtils.applicationContext == null)
            throw new SystemException("SpringUtils未初始化，请调用SpringUtils.setApplicationContext()方法初始化");
        return SpringUtils.applicationContext;
    }

    public static <T> T getInstance(Class<T> beanType, String bean) {
        try {
            return getApplicationContext().getBean(bean, beanType);
        } catch (NoSuchBeanDefinitionException ex) {
            log.warn(ex);
            return null;
        }
    }

    public static <T> T getInstance(Class<T> beanType) {
        try {
            return getApplicationContext().getBean(beanType);
        } catch (NoSuchBeanDefinitionException ex){
            log.warn(ex);
            return null;
        }
    }


    public static <T> Set<T> getInstances(Class<T> beanType) {
        return new HashSet<T>(getApplicationContext().getBeansOfType(beanType).values());
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> beanType) throws BeansException {
        return getApplicationContext().getBeansOfType(beanType);
    }


}
