package com.backstage.logging;

/**
 * Created by yitao on 2016/5/16.
 */
public interface Log {
    boolean isDebugEnabled();

    void error(String var1, Throwable var2);

    void error(String var1);

    boolean isInfoEnabled();

    void info(String var1);

    void debug(String var1);

    void debug(String var1, Throwable var2);

    boolean isWarnEnabled();

    void warn(String var1);

    void warn(String var1, Throwable var2);

    int getErrorCount();

    int getWarnCount();

    int getInfoCount();

    int getDebugCount();

    void resetStat();
}
