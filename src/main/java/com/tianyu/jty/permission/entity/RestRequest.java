package com.tianyu.jty.permission.entity;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import java.util.List;

/**
 * Created by xtao on 2015/11/22.
 */
public class RestRequest {
    private String host;
    private String path;
    private String method;

    public RestRequest() {
    }

    public RestRequest(String host, String path, String method) {
        this.host = host;
        this.path = path;
        this.method = method;
    }

    public static RestRequest newRestRequest(ServletRequest request){
        ShiroHttpServletRequest shiroHttpServletRequest = (ShiroHttpServletRequest) request;
        String host = shiroHttpServletRequest.getRemoteHost();
        String requestPath = WebUtils.getPathWithinApplication(WebUtils.toHttp(request));
        String requestMethod = shiroHttpServletRequest.getMethod();
        return new RestRequest(host, requestPath, requestMethod);
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
