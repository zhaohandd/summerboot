package com.xhzhao.summerboot.util;

import org.apache.commons.lang3.ArrayUtils;

/**
 * @author xhzhao
 */
public final class ArrayUtil {

    public static boolean isNotEmpty(Object[] array) {
        return !ArrayUtils.isEmpty(array);
    }

    public static boolean isEmpty(Object[] array) {
        return ArrayUtils.isEmpty(array);
    }
}
