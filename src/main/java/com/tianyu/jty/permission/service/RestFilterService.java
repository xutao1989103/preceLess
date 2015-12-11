package com.tianyu.jty.permission.service;

import com.tianyu.jty.permission.entity.CheckResult;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


/**
 * Created by xtao on 2015/11/23.
 */

@Service("restFilterService")
public class RestFilterService extends AbstractFilterService {

    @Override
    public CheckResult checkParams() {
        if(subject.getPrincipal() == null){
            return new CheckResult(false, "not login");
        }
        if(restRequest == null){
            return new CheckResult(false, "bad request");
        }
        if(CollectionUtils.isEmpty(urlFilters)){
            return new CheckResult(false, "no filters");
        }
        if(CollectionUtils.isEmpty(getAllRoles(urlFilters)) && StringUtils.isEmpty(getAllPermissions(urlFilters))){
            return new CheckResult(false, "roles and permissions are empty");
        }
        return new CheckResult(true, "params ok");
    }

    @Override
    public CheckResult checkRequest() {
        if(hasAnyRole(subject, getAllRoles(urlFilters)) || subject.isPermitted(getAllPermissions(urlFilters))){
            return new CheckResult(true, "ok");
        }else {
            return new CheckResult(false, "no roles or permissions");
        }
    }
}
