package com.proto.commons.support.web.filter;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import com.proto.commons.support.web.*;

/**
 * Created by and on 2016/6/27.
 */
public class RealIPFilter implements Filter {
    private String remoteAddrParamName = null;

    public RealIPFilter() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(request instanceof HttpServletRequest) {
            chain.doFilter(new RealIPRequestWrapper((HttpServletRequest)request, this.remoteAddrParamName), response);
        } else {
            chain.doFilter(request, response);
        }

    }

    public void destroy() {
    }

    public void init(FilterConfig arg0) throws ServletException {
        String str1 = arg0.getInitParameter("remote_addr_param_name");
        if(!StringUtils.isEmpty(str1)) {
            this.remoteAddrParamName = str1;
        }

    }
}