package com.tianyu.jty.permission.filters;

import com.google.gson.Gson;
import com.tianyu.jty.permission.entity.*;
import com.tianyu.jty.permission.service.CheckerFactory;
import com.tianyu.jty.permission.service.FilterService;
import com.tianyu.jty.permission.service.LbsCheck;
import com.tianyu.jty.permission.service.UrlFilterService;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by xtao on 2015/11/19.
 */
public class LbsFilter extends AccessControlFilter {

    @Resource(name = "lbsFilterService")
    private FilterService filterService;

    private UrlFilterResult result = UrlFilterResult.getResult(200);

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Subject subject = getSubject(request, response);
        filterService.initFilter(request, subject);
        CheckResult checkResult = filterService.checkParams();
        if(!checkResult.isPass()){
            result = new UrlFilterResult(401, checkResult.getMsg());
            return false;
        }
        checkResult = filterService.checkRequest();
        if(!checkResult.isPass()) {
            result = new UrlFilterResult(401, checkResult.getMsg());
            return false;
        }
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        writeResponse(request,response);
        return false;
    }

    private void writeResponse(ServletRequest request, ServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter printWriter = response.getWriter();
        Gson gson = new Gson();
        printWriter.println(gson.toJson(result));
        printWriter.flush();
        printWriter.close();
    }
}
