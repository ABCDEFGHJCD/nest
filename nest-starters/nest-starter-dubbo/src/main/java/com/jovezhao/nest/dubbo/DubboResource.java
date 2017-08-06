package com.jovezhao.nest.dubbo;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * Created by zhaofujun on 2017/8/6.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DubboResource {
    String version() default "1.0.0";
}
