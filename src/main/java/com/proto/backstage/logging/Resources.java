package com.proto.backstage.logging;

/**
 * Created by yitao on 2016/5/16.
 */
public class Resources {
    private static ClassLoader defaultClassLoader;

    private Resources() {
    }

    public static ClassLoader getDefaultClassLoader() {
        return defaultClassLoader;
    }

    public static void setDefaultClassLoader(ClassLoader defaultClassLoader) {
        defaultClassLoader = defaultClassLoader;
    }

    public static Class<?> classForName(String className) throws ClassNotFoundException {
        Class clazz = null;

        try {
            clazz = getClassLoader().loadClass(className);
        } catch (Exception var3) {
            ;
        }

        if(clazz == null) {
            clazz = Class.forName(className);
        }

        return clazz;
    }

    private static ClassLoader getClassLoader() {
        return defaultClassLoader != null?defaultClassLoader:Thread.currentThread().getContextClassLoader();
    }
}
