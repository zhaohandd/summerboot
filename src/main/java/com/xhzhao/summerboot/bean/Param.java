package com.xhzhao.summerboot.bean;

import com.xhzhao.summerboot.util.CastUtil;

import java.util.Map;

/**
 * @author xhzhao
 */
public class Param {

    private Map<String, Object> paramMap;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public long getLong(String name) {
        return CastUtil.castLong(paramMap.get(name));
    }

    public Map<String, Object> getMap() {
        return paramMap;
    }
}
