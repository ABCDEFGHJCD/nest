package com.jovezhao.nest.starter;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created by zhaofujun on 2017/8/8.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({NestAutoConfiguration.class})
public @interface EnableNest {
}
