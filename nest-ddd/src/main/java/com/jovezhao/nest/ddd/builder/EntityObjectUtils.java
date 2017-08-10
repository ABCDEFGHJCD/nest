package com.jovezhao.nest.ddd.builder;

import com.jovezhao.nest.ddd.BaseEntityObject;
import com.jovezhao.nest.exception.SystemException;
import com.jovezhao.nest.log.Log;
import com.jovezhao.nest.log.LogAdapter;
import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.Field;
import java.util.function.Consumer;

/**
 * 实体工具
 * Created by Jove on 2017/1/9.
 */
public class EntityObjectUtils {
    static Log log = new LogAdapter(EntityObjectUtils.class);

    /**
     * 使用cglib创建一个被代理的实体
     * 代理的主要作用用于监测自身属性变化时将自身提交到工作单元等待持久化
     *
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T extends BaseEntityObject> T create(Class<T> tClass) {
        Enhancer enhancer = new Enhancer();
        // 设置需要创建子类的类
        enhancer.setSuperclass(tClass);
        enhancer.setCallback(new EntityObjectMethodInterceptor());
        return (T) enhancer.create();
    }

    public static <T extends BaseEntityObject> T create(Class<T> tClass, Class[] constructorArgTypes, Object[] constructorArgs) {
        Enhancer enhancer = new Enhancer();
        // 设置需要创建子类的类
        enhancer.setSuperclass(tClass);
        enhancer.setCallback(new EntityObjectMethodInterceptor());

        if (constructorArgTypes == null || constructorArgs == null)
            return (T) enhancer.create();
        else
            return (T) enhancer.create(constructorArgTypes, constructorArgs);
    }

    public static void setValue(Class clazz, BaseEntityObject entityObject, String fieldName, Object value) {
        try {
            Field declaredField = clazz.getDeclaredField(fieldName);
            declaredField.setAccessible(true);
            declaredField.set(entityObject, value);
        } catch (Exception ex) {
            log.warn("设置属性失败", ex);
        }
    }


    /**
     * 修改实体为加载状态，加载状态的实体属性发生变化时不提交到工作单元
     *
     * @param entityObject
     */
    public static void beginLoad(BaseEntityObject entityObject) {

        try {
            Field field = BaseEntityObject.class.getDeclaredField("isLoad");
            field.setAccessible(true);
            field.set(entityObject, true);
        } catch (Exception e) {
            throw new SystemException("更新实体加载状态失败", e);
        }

    }

    /**
     * 修改实体为加载完成状态，完成状态的实体属性发生变化时自将提交到工作单元
     *
     * @param entityObject
     */
    public static void endLoad(BaseEntityObject entityObject) {

        try {
            Field field = BaseEntityObject.class.getDeclaredField("isLoad");
            field.setAccessible(true);
            field.set(entityObject, false);
        } catch (Exception e) {
            throw new SystemException("完成实体加载状态失败", e);
        }

    }

}
