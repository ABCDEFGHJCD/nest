package com.jovezhao.nest.test;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.jovezhao.nest.dubbo.DubboResource;
import com.jovezhao.nest.dubbo.EnableDubbo;
import com.jovezhao.nest.test.api.UserService;
import com.jovezhao.nest.test.appservices.UserServiceImpl;
import org.dozer.DozerBeanMapper;
import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * Created by zhaofujun on 2017/6/17.
 */

@SpringBootApplication
@MapperScan("com.jovezhao.nest.test.repositories.mappers")
public class Application implements CommandLineRunner {


    @Bean
    public DozerBeanMapperFactoryBean dozer() {
        return new DozerBeanMapperFactoryBean();
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);

//        ApplicationConfig applicationConfig = applicationContext.getBean(ApplicationConfig.class);
//        RegistryConfig registryConfig = applicationContext.getBean(RegistryConfig.class);
//        ProtocolConfig protocolConfig = applicationContext.getBean(ProtocolConfig.class);
//

//        ServiceConfig serviceConfig = new ServiceConfig();
//        serviceConfig.setApplication(applicationConfig);
//        serviceConfig.setRegistry(registryConfig); // 多个注册中心可以用setRegistries()
//        serviceConfig.setProtocol(protocolConfig); // 多个协议可以用setProtocols()
//        serviceConfig.setInterface(UserServiceImpl.class);
//        serviceConfig.setRef(applicationContext.getBean(UserService.class));
//        serviceConfig.setVersion("1.0.0");
//
//        serviceConfig.export();
    }

    @Autowired
    private DozerBeanMapper beanMapper;

    @Override
    public void run(String... args) throws Exception {

    }
}
