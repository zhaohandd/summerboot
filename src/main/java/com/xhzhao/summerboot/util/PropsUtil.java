package com.xhzhao.summerboot.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author xhzhao
 */
public final class PropsUtil {
    private static final Logger logger = LoggerFactory.getLogger(PropsUtil.class);

    /**
     * load properties file
     * @param propsPath path
     * @return properties
     */
    public static Properties loadProps(String propsPath) {
        Properties props = null;
        try (InputStream is = Thread.currentThread()
                .getContextClassLoader().getResourceAsStream(propsPath)){
            if (is == null) {
                throw new FileNotFoundException(propsPath + "does not exist");
            }
            props = new Properties();
            props.load(is);
        } catch (IOException e) {
            logger.error("properties file load fail", e);
        }
        return props;
    }

    /**
     * get String value, default value is ""
     * @param props Properties
     * @param key key-value
     * @return value
     */
    public static String getString(Properties props, String key) {
        return getString(props, key, "");
    }
    private static String getString(Properties props, String key, String defaultValue) {
        String value = defaultValue;
        if (props.containsKey(key)) {
            value = props.getProperty(key);
        }
        return value;
    }
}
