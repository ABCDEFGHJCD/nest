package com.jovezhao.nest.dubbo;


import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * Created by zhaofujun on 2017/8/5.
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
public @interface DubboService {
    String value() default "";
    String version() default "1.0.0";
}
