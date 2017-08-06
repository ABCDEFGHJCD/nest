package com.jovezhao.nest.dubbo;

import com.alibaba.dubbo.config.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2017/3/5/005.
 */
@Configuration
public class DubboConfig {
    @Bean
    @ConfigurationProperties(prefix = "nest.dubbo.application")
    public ApplicationConfig applicationConfig() {
        return new ApplicationConfig();
    }

    @Bean
    @ConfigurationProperties(prefix = "nest.dubbo.provider")
    public ProviderConfig ProviderConfig() {
        return new ProviderConfig();
    }

    @Bean
    @ConfigurationProperties(prefix = "nest.dubbo.monitor")
    public MonitorConfig monitorConfig() {
        return new MonitorConfig();
    }

    @Bean
    @ConfigurationProperties(prefix = "nest.dubbo.consumer")
    public ConsumerConfig consumerConfig() {
        return new ConsumerConfig();
    }

    @Bean
    @ConfigurationProperties(prefix = "nest.dubbo.registry")
    public RegistryConfig registryConfig() {
        return new RegistryConfig();
    }

    @Bean
    @ConfigurationProperties(prefix = "nest.dubbo.protocol")
    public ProtocolConfig protocolConfig() {
        return new ProtocolConfig();
    }
}
