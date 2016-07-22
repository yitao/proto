package com.backstage.logging;

/**
 * Created by yitao on 2016/5/16.
 */
public class NoLoggingImpl implements Log {
    private int infoCount;
    private int errorCount;
    private int warnCount;
    private int debugCount;
    private String loggerName;

    public NoLoggingImpl(String loggerName) {
        this.loggerName = loggerName;
    }

    public String getLoggerName() {
        return this.loggerName;
    }

    public boolean isDebugEnabled() {
        return false;
    }

    public void error(String s, Throwable e) {
        this.error(s);
        if(e != null) {
            e.printStackTrace();
        }

    }

    public void error(String s) {
        ++this.errorCount;
        if(s != null) {
            System.err.println(this.loggerName + " : " + s);
        }

    }

    public void debug(String s) {
        ++this.debugCount;
    }

    public void debug(String s, Throwable e) {
        ++this.debugCount;
    }

    public void warn(String s) {
        ++this.warnCount;
    }

    public void warn(String s, Throwable e) {
        ++this.warnCount;
    }

    public int getErrorCount() {
        return this.errorCount;
    }

    public int getWarnCount() {
        return this.warnCount;
    }

    public void resetStat() {
        this.errorCount = 0;
        this.warnCount = 0;
        this.infoCount = 0;
        this.debugCount = 0;
    }

    public boolean isInfoEnabled() {
        return false;
    }

    public void info(String s) {
        ++this.infoCount;
    }

    public boolean isWarnEnabled() {
        return false;
    }

    public int getInfoCount() {
        return this.infoCount;
    }

    public int getDebugCount() {
        return this.debugCount;
    }
}
