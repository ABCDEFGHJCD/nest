package com.jovezhao.nest.starter;

import com.jovezhao.nest.ddd.repository.IUnitOfWork;
import com.jovezhao.nest.ddd.repository.NestAspect;
import com.jovezhao.nest.ddd.repository.NestUnitOfWork;
import com.jovezhao.nest.mapper.NestMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by zhaofujun on 2017/6/17.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan
@PropertySource("classpath:nest.properties")
public class NestAutoConfiguration {


    @Bean
    public IUnitOfWork getUnitOfWork() {
        return new NestUnitOfWork();
    }

    @Bean
    public NestAspect getNestAspect() {
        return new NestAspect();
    }

    @Bean
    public NestMapper getNestMapper(){
        NestMapper nestMapper = new NestMapper();
        nestMapper.setMappingFiles(new ClassPathResource("classpath:nest-mapper.xml"));
        return nestMapper;
    }
}
