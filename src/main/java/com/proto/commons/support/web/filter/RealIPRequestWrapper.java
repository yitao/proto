package com.proto.commons.support.web.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by and on 2016/6/27.
 */

class RealIPRequestWrapper extends HttpServletRequestWrapper {
    private String remoteAddrParamName;

    public RealIPRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    public RealIPRequestWrapper(HttpServletRequest request, String remoteAddrParamName) {
        super(request);
        this.remoteAddrParamName = remoteAddrParamName;
    }

    public String getRemoteAddr() {
        if(this.remoteAddrParamName != null) {
            String realIP = super.getHeader(this.remoteAddrParamName);
            return realIP != null?realIP.split(",")[0]:super.getRemoteAddr();
        } else {
            return super.getRemoteAddr();
        }
    }

    public String getRemoteHost() {
        try {
            return InetAddress.getByName(this.getRemoteAddr()).getHostName();
        } catch (UnknownHostException var2) {
            return this.getRemoteAddr();
        }
    }
}
