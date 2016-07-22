package com.backstage.logging;

import java.lang.reflect.Constructor;

/**
 * Created by yitao on 2016/5/16.
 */
public class LogFactory {
    private static Constructor logConstructor;

    public LogFactory() {
    }

    private static void tryImplementation(String testClassName, String implClassName) {
        if(logConstructor == null) {
            try {
                Resources.classForName(testClassName);
                Class t = Resources.classForName(implClassName);
                logConstructor = t.getConstructor(new Class[]{String.class});
                Class declareClass = logConstructor.getDeclaringClass();
                if(!Log.class.isAssignableFrom(declareClass)) {
                    logConstructor = null;
                }

                try {
                    if(null != logConstructor) {
                        logConstructor.newInstance(new Object[]{LogFactory.class.getName()});
                    }
                } catch (Throwable var5) {
                    logConstructor = null;
                }
            } catch (Throwable var6) {
                ;
            }

        }
    }

    public static Log getLog(Class clazz) {
        return getLog(clazz.getName());
    }

    public static Log getLog(String loggerName) {
        try {
            return (Log)logConstructor.newInstance(new Object[]{loggerName});
        } catch (Throwable var2) {
            throw new RuntimeException("Error creating logger for logger \'" + loggerName + "\'.  Cause: " + var2, var2);
        }
    }

    public static synchronized void selectLog4JLogging() {
        try {
            Resources.classForName("org.apache.log4j.Logger");
            Class t = Resources.classForName("com.alibaba.druid.support.logging.Log4jImpl");
            logConstructor = t.getConstructor(new Class[]{String.class});
        } catch (Throwable var1) {
            ;
        }

    }

    public static synchronized void selectJavaLogging() {
        try {
            Resources.classForName("java.util.logging.Logger");
            Class t = Resources.classForName("com.alibaba.druid.support.logging.Jdk14LoggingImpl");
            logConstructor = t.getConstructor(new Class[]{String.class});
        } catch (Throwable var1) {
            ;
        }

    }

    static {
        String logType = System.getProperty("druid.logType");
        if(logType != null) {
            if(logType.equalsIgnoreCase("slf4j")) {
                tryImplementation("org.slf4j.Logger", "com.alibaba.druid.support.logging.SLF4JImpl");
            } else if(logType.equalsIgnoreCase("log4j")) {
                tryImplementation("org.apache.log4j.Logger", "com.alibaba.druid.support.logging.Log4jImpl");
            } else if(logType.equalsIgnoreCase("log4j2")) {
                tryImplementation("org.apache.logging.log4j.Logger", "com.alibaba.druid.support.logging.Log4j2Impl");
            } else if(logType.equalsIgnoreCase("commonsLog")) {
                tryImplementation("org.apache.commons.logging.LogFactory", "com.alibaba.druid.support.logging.JakartaCommonsLoggingImpl");
            } else if(logType.equalsIgnoreCase("jdkLog")) {
                tryImplementation("java.util.logging.Logger", "com.alibaba.druid.support.logging.Jdk14LoggingImpl");
            }
        }

        tryImplementation("org.apache.log4j.Logger", "com.alibaba.druid.support.logging.Log4jImpl");
        tryImplementation("org.apache.logging.log4j.Logger", "com.alibaba.druid.support.logging.Log4j2Impl");
        tryImplementation("org.slf4j.Logger", "com.alibaba.druid.support.logging.SLF4JImpl");
        tryImplementation("org.apache.commons.logging.LogFactory", "com.alibaba.druid.support.logging.JakartaCommonsLoggingImpl");
        tryImplementation("java.util.logging.Logger", "com.alibaba.druid.support.logging.Jdk14LoggingImpl");
        if(logConstructor == null) {
            try {
                logConstructor = NoLoggingImpl.class.getConstructor(new Class[]{String.class});
            } catch (Exception var2) {
                throw new IllegalStateException(var2.getMessage(), var2);
            }
        }

    }
}
