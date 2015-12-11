package com.tianyu.jty.permission.filters;

import com.tianyu.jty.system.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by xtao on 2015/11/17.
 */
public class SysUserFilter extends PathMatchingFilter {

    @Autowired
    private UserService userService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Object principal =  SecurityUtils.getSubject().getPrincipal();
        String username ="";
        if(principal != null){
            username = principal.toString();
        }
        request.setAttribute("username",username);
        return true;
    }
}
