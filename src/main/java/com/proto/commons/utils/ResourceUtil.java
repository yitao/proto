package com.proto.commons.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Properties;

/**
 * Created by and on 2016/6/27.
 */
public class ResourceUtil {
    private final static Logger log = LoggerFactory.getLogger(ResourceUtil.class);

    private final static Properties props = new Properties();
    private static String classPath;
    private static String appAbsolutePath; // for write local file purpose
    private static boolean isWindows;

    static {
        initialize();
    }

    private static void initialize() {
        String osType = System.getProperty("os.name");
        isWindows = osType == null || osType.toUpperCase().indexOf("WINDOWS") > -1;
        if (isWindows) {
            classPath = ResourceUtil.class.getResource("/").getPath();
            classPath = classPath.replace("file:", "");
            int pos = classPath.indexOf("/classes/");
            if (pos > -1)
                classPath = classPath.substring(1, pos + 9);
        } else {
            classPath = ResourceUtil.class.getResource("").getPath();
            classPath = classPath.replace("file:", "");
            int pos = classPath.indexOf("/classes/");
            if (pos == -1) {
                pos = classPath.indexOf("/lib/");
                if (pos > -1)
                    classPath = classPath.substring(0, pos + 5);
            } else {
                classPath = classPath.substring(0, pos + 9);
            }
        }
        classPath = classPath.replaceAll("/lib/", "/classes/");

        appAbsolutePath = classPath.replaceAll("/WEB-INF/classes/", "");
        // System.out.println("appAbsolutePath=" + appAbsolutePath);
        // appRoot = appAbsolutePath.substring(appAbsolutePath.lastIndexOf("/"));
        try {
            classPath = java.net.URLDecoder.decode(classPath, "UTF-8");
        } catch (Exception ex) {
            log.error("Error:", ex);
        }

        loadProperties(classPath + "application-prd.properties");
        loadProperties(classPath + "application-sit.properties");
        loadProperties(classPath + "application-dev.properties");
        loadProperties(classPath + "application-unit.properties");
    }

    private static void loadProperties(String propertyFile) {
        InputStream ins = null;
        try {
            ins = new FileInputStream(propertyFile);
            props.load(ins);
            ins.close();
        } catch (Exception ex) {
            // do nothing
            log.warn("Properties file not found and skip: " + propertyFile);
        } finally {
            FileUtils.closeIO(ins);
        }
    }

    /**
     * 获取文件扩展名
     *
     * @param filename
     * @return
     */
    public static String getExtName(String filename) {
        return filename.substring(filename.lastIndexOf("."));
    }

    /**
     * Whether specified URL exists
     *
     * @param url - URL
     * @return true is URL response OK
     */
    public static boolean exists(String url) {
        if (StringUtils.isBlank(url)) {
            return false;
        }
        url += "?t=" + new Date().getTime();
        HttpURLConnection.setFollowRedirects(false);
        // 到 URL 所引用的远程对象的连接
        HttpURLConnection con = null;

        try {
            URL urlObj = new URL(url);
            con = (HttpURLConnection) urlObj.openConnection();
            // 设置 URL 请求的方法， GET POST HEAD OPTIONS PUT DELETE TRACE
            // 以上方法之一是合法的，具体取决于协议的限制。
            con.setRequestMethod("HEAD");
            // con.setRequestProperty("Host", url.getHost());
            // 从 HTTP 响应消息获取状态码
            return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (Exception ex) {
            log.error("Error:", ex);
        }
        return false;
    }

    /**
     * 获取资源文件中的配置，如无则返回默认
     *
     * @param key
     * @param defaultProperty
     * @return
     */
    public static String getProperty(String key, String defaultProperty) {
        return props.getProperty(key, defaultProperty);
    }

    /**
     * 获取资源文件中的配置
     *
     * @param key
     * @return
     */
    public static String getProperty(String key) {
        return props.getProperty(key);
    }

    /**
     * @return the APP root path in OS format. Such as: /usr/local/tomcat/webapps/ROOT
     */
    public static String getAppAbsolutePath() {
        return appAbsolutePath;
    }

    /**
     * 获取应用的根url
     *
     * @return
     */
    public static String getAppUrl() {
        return getProperty("app.url");
    }

}