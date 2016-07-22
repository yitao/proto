package com.backstage.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by weway on 2015/12/14.
 */
public abstract class PhantomUtils {
    private static final Logger log = LoggerFactory.getLogger(PhantomUtils.class);

    public static void render(String url, String fileName) throws Exception {
        if (StringUtils.isBlank(url) || StringUtils.isBlank(fileName)) {
            throw new IllegalArgumentException();
        }
        String phantom = ResourceUtil.getProperty("phantomjs.path", "D:/Home/phantomjs-2.0.0-windows/bin/phantomjs");
        String dir = ResourceUtil.getProperty("phantomjs.workhome", "D:/Home/");
        StringBuilder sb = new StringBuilder(phantom);
        sb.append(" loadpage.js ").append(url).append(" ").append(fileName);
        String cmd = sb.toString();
        log.info(cmd);
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec(cmd, null, new File(dir));
        InputStream in = process.getErrorStream();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            int result = process.waitFor();
            if (result != 0) {
                throw new IllegalStateException("phantomjs run error");
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

    }

    public static void render(String url, String fileName, int width, int height) throws Exception {
        if (StringUtils.isBlank(url) || StringUtils.isBlank(fileName)) {
            throw new IllegalArgumentException();
        }
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException();
        }
        String phantom = ResourceUtil.getProperty("phantomjs.path", "D:/Home/phantomjs-2.0.0-windows/bin/phantomjs");
        String dir = ResourceUtil.getProperty("phantomjs.workhome", "d:/Home/");
        StringBuilder sb = new StringBuilder(phantom);
        sb.append(" loadpagewithview.js ").append(url).append(" ").append(fileName).append(" ").append(width).append(" ").append(height);
        String cmd = sb.toString();
        log.info(cmd);
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec(cmd, null, new File(dir));
        int result = process.waitFor();
        if (result != 0) {
            throw new IllegalStateException("phantomjs run error");
        }
        InputStream in = process.getErrorStream();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    public static void newrender(String url, String fileName, int width, int height) throws Exception {
        if (StringUtils.isBlank(url) || StringUtils.isBlank(fileName)) {
            throw new IllegalArgumentException();
        }
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException();
        }
        String phantom = ResourceUtil.getProperty("phantomjs.path", "D:/Home/phantomjs-2.0.0-windows/bin/phantomjs");
        String dir = ResourceUtil.getProperty("phantomjs.workhome", "d:/Home/");
        StringBuilder sb = new StringBuilder(phantom);
        sb.append(" newloadpagewithview.js ").append(url).append(" ").append(fileName).append(" ").append(width).append(" ").append(height);
        String cmd = sb.toString();
        log.info("cmd>>: {}", cmd);
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec(cmd, null, new File(dir));
        InputStream in = process.getErrorStream();
        BufferedReader reader = null;
        try {
            log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while ((line = reader.readLine()) != null) {
                log.debug("【process】{}", line);
//				System.out.println(line);
            }
            log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            int result = process.waitFor();
            if (result != 0) {
                throw new IllegalStateException("phantomjs run error");
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

    }

    public static void newrenderBack(String url, String fileName, int width, int height) throws Exception {
        if (StringUtils.isBlank(url) || StringUtils.isBlank(fileName)) {
            throw new IllegalArgumentException();
        }
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException();
        }
        String phantom = ResourceUtil.getProperty("phantomjs.path", "D:/Home/phantomjs-2.0.0-windows/bin/phantomjs");
        String dir = ResourceUtil.getProperty("phantomjs.workhome", "d:/Home/");
        StringBuilder sb = new StringBuilder(phantom);
        sb.append(" newloadpagewithviewback.js ").append(url).append(" ").append(fileName).append(" ").append(width).append(" ").append(height);
        String cmd = sb.toString();
        log.info("cmd>>: {}", cmd);
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec(cmd, null, new File(dir));
        InputStream in = process.getErrorStream();
        BufferedReader reader = null;
        try {
            log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while ((line = reader.readLine()) != null) {
                log.debug("【process】{}", line);
//				System.out.println(line);
            }
            log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            int result = process.waitFor();
            if (result != 0) {
                throw new IllegalStateException("phantomjs run error");
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

    }

}
