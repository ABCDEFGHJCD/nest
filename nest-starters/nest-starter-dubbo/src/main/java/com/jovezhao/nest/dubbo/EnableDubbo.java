package com.jovezhao.nest.dubbo;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created by zhaofujun on 2017/8/5.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({DubboConfig.class, DubboServiceConfiguration.class,DubboClientConfiguration.class})
public @interface EnableDubbo {
}
