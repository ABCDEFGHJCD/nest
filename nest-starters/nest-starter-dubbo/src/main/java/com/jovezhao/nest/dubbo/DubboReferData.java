package com.jovezhao.nest.dubbo;

import java.lang.reflect.Field;

/**
 * Created by zhaofujun on 2017/8/6.
 */
public class DubboReferData {
    private Field field;
    private Object bean;

    public Field getField() {
        return field;
    }

    public Object getBean() {
        return bean;
    }

    public DubboReferData(Field field, Object bean) {


        this.field = field;
        this.bean = bean;
    }
}
