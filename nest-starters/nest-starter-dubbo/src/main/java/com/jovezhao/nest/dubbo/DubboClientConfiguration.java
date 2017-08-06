package com.jovezhao.nest.dubbo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhaofujun on 2017/8/6.
 */

public class DubboClientConfiguration implements BeanPostProcessor {

    @Autowired
    ApplicationConfig applicationConfig;
    @Autowired
    RegistryConfig registryConfig;
    @Autowired
    ProtocolConfig protocolConfig;


    private Map<Class, Object> refValue = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().getSimpleName().contains("TestController")) {
            System.out.printf(bean.getClass().getName());
        }
        Arrays.stream(bean.getClass().getDeclaredFields()).forEach(f -> {
            DubboResource dubboResource = f.getAnnotation(DubboResource.class);
            if (dubboResource != null) {
                Object ref = refValue.get(f.getType());
                if (ref == null) {
                    //加入
                    ReferenceConfig reference = new ReferenceConfig(); // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
                    reference.setApplication(applicationConfig);
                    reference.setRegistry(registryConfig); // 多个注册中心可以用setRegistries()
                    reference.setInterface(f.getType());
                    reference.setVersion(dubboResource.version());
                    reference.setLazy(true);
                    reference.setCheck(false);

                    // 和本地bean一样使用xxxService
                    ref = reference.get();
                    refValue.put(f.getType(),ref);

                }
                try {
                    f.setAccessible(true);
                    f.set(bean, ref);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        });
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
