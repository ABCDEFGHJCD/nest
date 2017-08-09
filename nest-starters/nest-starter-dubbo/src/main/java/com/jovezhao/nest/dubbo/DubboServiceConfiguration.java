package com.jovezhao.nest.dubbo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * Created by zhaofujun on 2017/8/5.
 */

public class DubboServiceConfiguration implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        BeanDefinitionRegistry beanDefinitionRegistry = (BeanDefinitionRegistry) applicationContext;

        ApplicationConfig applicationConfig = applicationContext.getBean(ApplicationConfig.class);
        RegistryConfig registryConfig = applicationContext.getBean(RegistryConfig.class);
        ProtocolConfig protocolConfig = applicationContext.getBean(ProtocolConfig.class);


        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(DubboService.class);
        for (Map.Entry<String, Object> entry : beansWithAnnotation.entrySet()) {
            ServiceConfig serviceConfig = new ServiceConfig();
            serviceConfig.setApplication(applicationConfig);
            serviceConfig.setRegistry(registryConfig); // 多个注册中心可以用setRegistries()
            serviceConfig.setProtocol(protocolConfig); // 多个协议可以用setProtocols()
            serviceConfig.setInterface(getBeanInterace(entry.getKey(), beanDefinitionRegistry));
            serviceConfig.setRef(entry.getValue());
            DubboService dubboService = getDubboService(entry.getKey(), beanDefinitionRegistry);
            serviceConfig.setVersion(dubboService.version());

            serviceConfig.export();
        }
    }

    private Class getBeanInterace(String beanName, BeanDefinitionRegistry beanDefinitionRegistry) {
        String beanClassName = beanDefinitionRegistry.getBeanDefinition(beanName).getBeanClassName();
        try {
            Class<?> aClass = Class.forName(beanClassName);
            return aClass.getInterfaces()[0];
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private DubboService getDubboService(String beanName, BeanDefinitionRegistry beanDefinitionRegistry) {
        String beanClassName = beanDefinitionRegistry.getBeanDefinition(beanName).getBeanClassName();
        try {
            Class<?> aClass = Class.forName(beanClassName);
            return aClass.getAnnotation(DubboService.class);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
