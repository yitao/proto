package com.proto.commons.support.web.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by and on 2016/6/27.
 */
public class StartupContextListener implements ServletContextListener {


    private final Logger log = LoggerFactory.getLogger(getClass());

    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        ApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
        printEnvInfo(appContext);
        printLogLevel();
    }

    private void printEnvInfo(ApplicationContext appContext) {
        System.out.println("[JAVA VER] " + appContext.getEnvironment().getProperty("java.version"));
        System.out.println("[JAVA HOME] " + appContext.getEnvironment().getProperty("java.home"));
        System.out.println("[OS] " + appContext.getEnvironment().getProperty("os.name"));
    }

    private void printLogLevel() {
        System.out.print("[LOG LEVEL] ");
        if (log.isTraceEnabled()) {
            System.out.println("TRACE");
        } else if (log.isDebugEnabled()) {
            System.out.println("DEBUG");
        } else if (log.isInfoEnabled()) {
            System.out.println("INFO");
        } else if (log.isWarnEnabled()) {
            System.out.println("WARN");
        } else if (log.isErrorEnabled()) {
            System.out.println("ERROR");
        }
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }


}