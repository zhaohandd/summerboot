package com.xhzhao.summerboot.helper;

import com.xhzhao.summerboot.annotation.Inject;
import com.xhzhao.summerboot.util.ArrayUtil;
import com.xhzhao.summerboot.util.CollectionUtil;
import com.xhzhao.summerboot.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author xhzhao
 */
public final class IocHelper {
    static {
        Map<Class<?>, Object> beanMap = BeanHelper.getBaseMap();
        if (CollectionUtil.isNotEmpty(beanMap)) {
            for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();
                Field[] beanFields = beanClass.getDeclaredFields();
                if (ArrayUtil.isNotEmpty(beanFields)) {
                    for (Field beanField : beanFields) {
                        if (beanField.isAnnotationPresent(Inject.class)) {
                            Class<?> beanFieldClass = beanField.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);
                            if (beanFieldInstance != null) {
                                ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }
}
