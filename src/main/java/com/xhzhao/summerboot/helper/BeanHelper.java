package com.xhzhao.summerboot.helper;

import com.xhzhao.summerboot.util.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author xhzhao
 */
public final class BeanHelper {

    private static final Map<Class<?>, Object> BASE_MAP = new HashMap<>();

    static {
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        for (Class<?> cls : beanClassSet) {
            Object obj = ReflectionUtil.newInstance(cls);
            BASE_MAP.put(cls, obj);
        }
    }

    public static Map<Class<?>, Object> getBaseMap() {
        return BASE_MAP;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> cls) {
        if (!BASE_MAP.containsKey(cls)) {
            throw new RuntimeException("can not get bean by class: " + cls);
        }
        return (T) BASE_MAP.get(cls);
    }
}
