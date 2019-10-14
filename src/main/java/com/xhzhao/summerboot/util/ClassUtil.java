package com.xhzhao.summerboot.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * class operator util
 * @author xhzhao
 */
public final class ClassUtil {
    private static final Logger logger = LoggerFactory.getLogger(ClassUtil.class);

    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    public static Class<?> loadClass(String className, boolean isInitialized) {
        Class<?> cls;
        try {
            cls = Class.forName(className, isInitialized, getClassLoader());
        } catch (ClassNotFoundException ex) {
            logger.error("load class failure", ex);
            throw new RuntimeException(ex);
        }
        return cls;
    }

    public static Set<Class<?>> getClassSet(String packageName) {
        Set<Class<?>> set = new HashSet<>();
        try {
            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (url != null) {
                    String protocol = url.getProtocol();
                    if ("file".equals(protocol)) {
                        String packagePath = url.getPath().replace("%20", " ");
                        addClass(set, packagePath, packageName);
                    } else if ("jar".equals(protocol)) {
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                        if (jarURLConnection != null) {
                            JarFile jarFile = jarURLConnection.getJarFile();
                            if (jarFile != null) {
                                Enumeration<JarEntry> jarEntries = jarFile.entries();
                                while (jarEntries.hasMoreElements()) {
                                    JarEntry jarEntry = jarEntries.nextElement();
                                    String jarEntryName = jarEntry.getName();
                                    if (jarEntryName.endsWith(".class")) {
                                        String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
                                        doAddClass(set, className);
                                    }
                                }
                            }
                        }
                    }
                }

            }
        } catch (IOException e) {
            logger.error("get class set failure", e);
            throw new RuntimeException(e);
        }
        return set;
    }

    private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {
        File[] files = new File(packagePath).listFiles(pathname -> (pathname.isFile() && pathname.getName().endsWith(".class") ||
                pathname.isDirectory()));
        for (File file : files) {
            String name = file.getName();
            if (file.isFile()) {
                String className = name.substring(0, name.lastIndexOf("."));
                if (StringUtil.isNotEmpty(packageName)) {
                    className = packageName + "." + className;
                }
                doAddClass(classSet, className);
            } else {
                String subPackagePath = name;
                if (StringUtil.isNotEmpty(packagePath)) {
                    subPackagePath = packagePath + "/" + subPackagePath;
                }
                String subPackageName = name;
                if (StringUtil.isNotEmpty(packageName)) {
                    subPackageName = packageName + "." + subPackageName;
                }
                addClass(classSet, subPackagePath, subPackageName);
            }
        }
    }

    private static void doAddClass(Set<Class<?>> classSet, String className) {
        Class<?> cls = loadClass(className, false);
        classSet.add(cls);
    }

}
