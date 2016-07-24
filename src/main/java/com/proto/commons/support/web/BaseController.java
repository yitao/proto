package com.proto.commons.support.web;


import com.proto.commons.utils.CustomTimestampEditor;
import com.proto.commons.utils.JsonUtils;
import com.proto.commons.utils.URLUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

/**
 * Created by and on 2016/7/24.
 */
public class BaseController {
    private static final Logger log = LoggerFactory.getLogger(BaseController.class);
    public static final String X_FORWARDED_FOR = "X-Forwarded-For";
    public static final String USER_AGENT = "user-agent";
    public static final String REFERER = "referer";
    public static final String CHARSET = "UTF-8";
    public static final String RESPONSE_CONTENT_TYPE_JSON = "application/json";
    public static final String RESPONSE_CONTENT_TYPE_TXT = "text/plain";
    public static final String HEADER_KEY_ACAO = "Access-Control-Allow-Origin";
    public static final String HEADER_VAL_ACAO = "*";
    public static final String ERR_500 = "/error/500";
    public static final String ERR_404 = "/error/404";

    public BaseController() {
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
        binder.registerCustomEditor(Timestamp.class, new CustomTimestampEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
    }

    public static void response(HttpServletResponse res, String text, String charset) {
        res.addHeader("Access-Control-Allow-Origin", "*");
        res.setContentType("application/json");
        res.setCharacterEncoding(charset);

        try {
            res.getOutputStream().write(text.getBytes(charset));
        } catch (Exception var4) {
            log.error("Error", var4);
        }

    }

    public static void response(HttpServletResponse res, String text) {
        response(res, text, "UTF-8");
    }

    public static void responseJson(HttpServletResponse res, Object obj) {
        response(res, JsonUtils.toJson(obj, true));
    }

    public static void printRequestHeaders(HttpServletRequest req) {
        if(req != null) {
            Enumeration names = req.getHeaderNames();
            StringBuilder sb = new StringBuilder("\n=========Request Headers=========");

            while(names.hasMoreElements()) {
                String name = (String)names.nextElement();
                sb.append("\n\t").append(name + ": " + req.getHeader(name));
            }

            sb.append("\n=========Request Headers=========");
            log.info(sb.toString());
        }

    }

    public static void printRequestAttribute(HttpServletRequest req) {
        if(req != null) {
            Enumeration names = req.getAttributeNames();
            StringBuilder sb = new StringBuilder("\n=========Request Attribute=========");

            while(names.hasMoreElements()) {
                String name = (String)names.nextElement();
                sb.append("\n\t").append(name + ": " + req.getAttribute(name));
            }

            sb.append("\n=========Request Attribute=========");
            log.info(sb.toString());
        }

    }

    public static void printRequestCookie(HttpServletRequest req) {
        if(req != null) {
            Cookie[] cookies = req.getCookies();
            if(cookies != null) {
                StringBuilder sb = new StringBuilder("\nCookie: ");
                Cookie[] arr$ = cookies;
                int len$ = cookies.length;

                for(int i$ = 0; i$ < len$; ++i$) {
                    Cookie cookie = arr$[i$];
                    sb.append("\n\t").append(JsonUtils.toJson(cookie));
                }

                log.info(sb.toString());
            }

        }
    }

    public static void printRequestBrief(HttpServletRequest req) {
        if(log.isInfoEnabled() && req != null) {
            StringBuilder sb = new StringBuilder("\nInvoke: " + req.getRequestURL());
            Enumeration names = req.getHeaderNames();
            sb.append("\nParams:");
            names = req.getParameterNames();

            while(names.hasMoreElements()) {
                String cookies = (String)names.nextElement();
                sb.append("\n\t" + cookies + ": [" + req.getParameter(cookies) + "]");
            }

            Cookie[] var8 = req.getCookies();
            if(var8 != null) {
                sb.append("\nCookies: ");
                Cookie[] arr$ = var8;
                int len$ = var8.length;

                for(int i$ = 0; i$ < len$; ++i$) {
                    Cookie cookie = arr$[i$];
                    sb.append("\n\t").append(JsonUtils.toJson(cookie));
                }
            }

            sb.append("\nReq Ip:\t").append(req.getRemoteAddr());
            sb.append("\nFwd Ip:\t").append(getReqIp(req));
            log.info(sb.toString());
        }

    }

    public static String getReqIp(HttpServletRequest req) {
        String ip = req.getHeader("X-Forwarded-For");
        return StringUtils.isNotBlank(ip)?ip:req.getRemoteAddr();
    }

    public static String getUserAgent(HttpServletRequest req) {
        return req.getHeader("user-agent");
    }

    public static String getReferer(HttpServletRequest req) {
        return req.getHeader("referer");
    }

    public static void clearSession(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.invalidate();
    }

    public static String getCookieDomain(HttpServletRequest req) {
        String hostname = req.getServerName();
        String cookieDomain = null;
        if(!hostname.equals("localhost") && !URLUtils.isIpAddress(hostname)) {
            cookieDomain = "." + URLUtils.getDomainName(hostname);
        }

        return cookieDomain;
    }
}

