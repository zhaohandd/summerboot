package com.xhzhao.summerboot;

import java.util.ArrayList;

/**
 * provides configuration constants
 * @author xhzhao
 */
public interface ConfigConstant {
    // configuration file
    String CONFIG_FILE = "summer.properties";

    // database connection configuration
    String JDBC_DRIVER = "summer.datasource.jdbc.driver";
    String JDBC_URL = "summer.datasource.jdbc.url";
    String JDBC_USERNAME = "summer.datasource.jdbc.username";
    String JDBC_PASSWORD = "summer.datasource.jdbc.password";

    /**
     * basic package
     * <pre>
     *     com.xhzhao.summerboot
     * </pre>
     */
    String APP_BASE_PACKAGE = "summer.app.base_package";
    // basic path of jsp
    String APP_JSP_PATH = "summer.app.jsp_path";
    /**
     * basic pah of static resource
     * <pre>
     *     JS CSS Image
     * </pre>
      */
    String APP_ASSET_PATH = "summer.app.asset_path";
}
