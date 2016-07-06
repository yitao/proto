package com.proto.backstage.logging;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Created by yitao on 2016/5/16.
 */
public class Log4jImpl implements Log {
    private static final String callerFQCN = Log4jImpl.class.getName();
    private Logger log;
    private int errorCount;
    private int warnCount;
    private int infoCount;
    private int debugCount;

    public Log4jImpl(Logger log) {
        this.log = log;
    }

    public Log4jImpl(String loggerName) {
        this.log = Logger.getLogger(loggerName);
    }

    public Logger getLog() {
        return this.log;
    }

    public boolean isDebugEnabled() {
        return this.log.isDebugEnabled();
    }

    public void error(String s, Throwable e) {
        ++this.errorCount;
        this.log.log(callerFQCN, Level.ERROR, s, e);
    }

    public void error(String s) {
        ++this.errorCount;
        this.log.log(callerFQCN, Level.ERROR, s, (Throwable)null);
    }

    public void debug(String s) {
        ++this.debugCount;
        this.log.log(callerFQCN, Level.DEBUG, s, (Throwable)null);
    }

    public void debug(String s, Throwable e) {
        ++this.debugCount;
        this.log.log(callerFQCN, Level.DEBUG, s, e);
    }

    public void warn(String s) {
        this.log.log(callerFQCN, Level.WARN, s, (Throwable)null);
        ++this.warnCount;
    }

    public void warn(String s, Throwable e) {
        this.log.log(callerFQCN, Level.WARN, s, e);
        ++this.warnCount;
    }

    public int getWarnCount() {
        return this.warnCount;
    }

    public int getErrorCount() {
        return this.errorCount;
    }

    public void resetStat() {
        this.errorCount = 0;
        this.warnCount = 0;
        this.infoCount = 0;
        this.debugCount = 0;
    }

    public int getDebugCount() {
        return this.debugCount;
    }

    public boolean isInfoEnabled() {
        return this.log.isInfoEnabled();
    }

    public void info(String msg) {
        ++this.infoCount;
        this.log.log(callerFQCN, Level.INFO, msg, (Throwable)null);
    }

    public boolean isWarnEnabled() {
        return this.log.isEnabledFor(Level.WARN);
    }

    public int getInfoCount() {
        return this.infoCount;
    }

    public String toString() {
        return this.log.toString();
    }
}
