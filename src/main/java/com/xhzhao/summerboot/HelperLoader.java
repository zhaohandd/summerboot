package com.xhzhao.summerboot;

import com.xhzhao.summerboot.helper.AopHelper;
import com.xhzhao.summerboot.helper.BeanHelper;
import com.xhzhao.summerboot.helper.ClassHelper;
import com.xhzhao.summerboot.helper.ControllerHelper;
import com.xhzhao.summerboot.helper.IocHelper;
import com.xhzhao.summerboot.util.ClassUtil;

/**
 * @author xhzhao
 */
public final class HelperLoader {

    public static void init() {
        Class<?>[] classList = {
                BeanHelper.class,
                ClassHelper.class,
                ControllerHelper.class,
                IocHelper.class,
                AopHelper.class
        };

        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName(), true);
        }
    }
}
