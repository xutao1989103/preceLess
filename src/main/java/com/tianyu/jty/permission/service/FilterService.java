package com.tianyu.jty.permission.service;

import com.tianyu.jty.permission.entity.CheckResult;
import com.tianyu.jty.permission.entity.UrlFilter;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletRequest;
import java.util.List;

/**
 * Created by xtao on 2015/11/23.
 */
public interface FilterService {
    void initFilter(ServletRequest servletRequest, Subject subject);
    List<UrlFilter> getMatchedUrlFilter();
    CheckResult checkParams();
    CheckResult checkRequest();
}
